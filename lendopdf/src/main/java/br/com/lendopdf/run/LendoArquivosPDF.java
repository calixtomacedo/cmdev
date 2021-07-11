package br.com.lendopdf.run;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.exceptions.CryptographyException;
import org.apache.pdfbox.exceptions.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.TextPosition;

public class LendoArquivosPDF extends PDFTextStripper {
	
	public static StringBuilder palavraChave = new StringBuilder();
	public static List<String> palavraChaveList = new ArrayList<String>();
	public static boolean isPrimeiroCaracter = true;
	public static Integer pagina = 1;
	public static Integer coordenadaY;
	public List<String> marcacaoLista;

	public LendoArquivosPDF() throws IOException {
		super.setSortByPosition(true);
		this.marcacaoLista = new ArrayList<String>();
		this.marcacaoLista.add("Macedo");
		//this.marcacaoLista.add("10/05/2021");
		this.marcacaoLista.add("BANCO-BV");
		this.marcacaoLista.add("Calixto");
		this.marcacaoLista.add("Osasco");
		this.marcacaoLista.add("Rodrigues");
	}

	public static void main(String[] args) throws IOException, CryptographyException {
		File arquivoPdf;
		PDDocument pdDocument = null;
		try {
			arquivoPdf = new File("teste.pdf");
			pdDocument = PDDocument.load(arquivoPdf);
			if (pdDocument.isEncrypted()) {
				try {
					pdDocument.decrypt("");
				} catch (InvalidPasswordException e) {
					System.err.println("Error: Documento criptografado com uma senha.");
					System.exit(1);
				}
			}

			List<?> allPages = pdDocument.getDocumentCatalog().getAllPages();
			for (int i = 0; i < allPages.size(); i++) {
				PDPage page = (PDPage) allPages.get(i);
				PDStream contents = page.getContents();
				LendoArquivosPDF printer = new LendoArquivosPDF();
				if(contents != null) {
					printer.processStream(page, page.findResources(), page.getContents().getStream());
				}
				pagina += 1;
			}
		} finally {
			if (pdDocument != null) {
				System.out.println(palavraChaveList);
				pdDocument.close();
			}
		}
	}
		
	@Override
	protected void processTextPosition(TextPosition text) {
		String caracter = text.getCharacter();
		boolean isCoordenadaY;
		String REGEX = "[,.\\[\\](:;!?)/]";
		char charAt = caracter.charAt(0);
		isCoordenadaY = validaSeECoordenadaY(text);
		if ((!caracter.matches(REGEX)) && (!Character.isWhitespace(charAt))) {
			if ((!isPrimeiroCaracter) && (isCoordenadaY == true)) {
				appendCaracter(caracter);
			} else if (isPrimeiroCaracter == true) {
				setCoordenadaDaPalavraChave(text, caracter);
			}
		} else {
			finalDoArquivo();
		}
	}
	
	protected boolean validaSeECoordenadaY(TextPosition text) {
		Integer yDirAdj = Integer.valueOf((int) text.getYDirAdj());
		if (yDirAdj.equals(coordenadaY)) {
			return true;
		}
		coordenadaY = yDirAdj;
		finalDoArquivo();
		return false;
	}
	
	
	protected void appendCaracter(String caracter) {
		palavraChave.append(caracter);
		isPrimeiroCaracter = false;
	}
	
	protected void setCoordenadaDaPalavraChave(TextPosition text, String caracter) {
		palavraChave.append("Pagina: ").append(pagina).append(" ==> X:").append(Integer.valueOf((int) text.getXDirAdj())).append(", Y:").append(Integer.valueOf((int) text.getYDirAdj())).append(", Palavra: ").append(caracter);
		isPrimeiroCaracter = false;
	}
	
	protected void finalDoArquivo() {
		String novaPalavra = palavraChave.toString().replaceAll("[^\\x00-\\x7F]", "");
		String palavras = novaPalavra.substring(novaPalavra.lastIndexOf(' ') + 1);
		if (!"".equals(palavras)) {
			if (Arrays.asList(marcacaoLista.toArray()).contains(palavras)) {
				palavraChaveList.add(novaPalavra);
			} 
		}
		palavraChave.delete(0, palavraChave.length());
		isPrimeiroCaracter = true;
	}
	
	
	

}
