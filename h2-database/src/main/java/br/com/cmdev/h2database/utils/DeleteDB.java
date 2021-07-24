package br.com.cmdev.h2database.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DeleteDB {

	public static void main(String[] args) throws IOException {
		File dbFile = new File(new File("data").getAbsolutePath());
		if (dbFile.exists()) {
			delete(dbFile);
		}
	}

	static void delete(File file) {
		File[] contents = file.listFiles();
		if (contents != null) {
			for (File f : contents) {
				if (!Files.isSymbolicLink(f.toPath())) {
					delete(f);
				}
			}
		}
		file.delete();
	}

}
