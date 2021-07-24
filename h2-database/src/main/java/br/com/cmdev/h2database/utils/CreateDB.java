package br.com.cmdev.h2database.utils;

import java.io.File;

public class CreateDB {

	public static void main(String[] args) {
		
		String pathDB = new File("data".concat(File.separator).concat("h2db")).getAbsolutePath();
		File h2dbPath = new File(pathDB);
		if(!h2dbPath.exists()) {
			h2dbPath.mkdirs();
		}
	}

}
