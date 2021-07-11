package br.com.dev.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

public class LendoImagens {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
	    
	    try {
	    	//File file = new File("bv.jpg");
	    	File file = new File("pom.xml");
	    	String encodedfile = null;
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
            System.out.println(encodedfile);
            
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	    
	}
	
}
