package br.com.dev.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;

public class UploadPope {
	
public static void main(String[] args) throws IOException {
		
		String url = "https://apigatewayuat.bvnet.bv/v1/varejo/propostas/formalizacao/upload-documentos";
		String charset = "UTF-8";
		File pdfFile = new File("C:\\teste.pdf");
		String boundary = Long.toHexString(System.currentTimeMillis()); 
		String CRLF = "\r\n"; 

		URLConnection connection = new URL(url).openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		connection.setRequestProperty(
				"Authorization",
				"Bearer f70bcf33-6174-48ba-a75d-1c34539b4134");
		connection.setRequestProperty("trilhaAuditoria.loginUsuarioFinal", "99999999");

		try (
		    OutputStream output = connection.getOutputStream();
		    PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);
		) {
		    // idProposta
		    writer.append("--" + boundary).append(CRLF);
		    writer.append("Content-Disposition: form-data; name=\"idProposta\"").append(CRLF);
		    writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
		    writer.append(CRLF).append("730272667").append(CRLF).flush();
		    
		    // idChecklist
		    writer.append("--" + boundary).append(CRLF);
		    writer.append("Content-Disposition: form-data; name=\"idChecklist\"").append(CRLF);
		    writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
		    writer.append(CRLF).append("470098").append(CRLF).flush();
		    
		    // siglaCanal
		    writer.append("--" + boundary).append(CRLF);
		    writer.append("Content-Disposition: form-data; name=\"siglaCanal\"").append(CRLF);
		    writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
		    writer.append(CRLF).append("APRO").append(CRLF).flush();

		    // Arquivo
		    writer.append("--" + boundary).append(CRLF);
		    writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + pdfFile.getName() + "\"").append(CRLF);
		    writer.append("Content-Type: application/pdf; charset=" + charset).append(CRLF); // Text file itself must be saved in this charset!
		    writer.append(CRLF).flush();
		    Files.copy(pdfFile.toPath(), output);
		    output.flush(); // Important before continuing with writer!
		    writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.


		    // End of multipart/form-data.
		    writer.append("--" + boundary + "--").append(CRLF).flush();
		}

		HttpURLConnection response =  ((HttpURLConnection) connection);
		
		BufferedReader br = new BufferedReader(new InputStreamReader((response.getInputStream())));
		StringBuilder sb = new StringBuilder();
		String output;
		while ((output = br.readLine()) != null) {
		  sb.append(output);
		}
		System.out.println("status: "+response.getResponseCode()+"\n"+sb.toString()); 
		
	}

}
