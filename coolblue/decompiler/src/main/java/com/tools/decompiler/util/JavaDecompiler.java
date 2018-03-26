package com.tools.decompiler.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import com.strobel.decompiler.Decompiler;
import com.strobel.decompiler.PlainTextOutput;

public class JavaDecompiler {

	public static String decompile() throws ClassNotFoundException, IOException {
		PlainTextOutput output = new PlainTextOutput();
		File file = new File("d:\\");
		File aFile = new File("OAuthTokenizer.class");
		// convert the file to URL format
		URL url = file.toURI().toURL();
		URL[] urls = new URL[] { url };

		// load this folder into Class loader
		CustomURLClassLoader cl = new CustomURLClassLoader(urls);
		String directoryPath = "";
		try {
			Class cls = cl.loadClass("OAuthTokenizer", false);
		} catch (Error exc) {
			String msg = exc.getMessage();
			System.out.println(msg);
			directoryPath = msg.substring(msg.indexOf(':')+2, msg.lastIndexOf('/'));
		}
		System.out.println(directoryPath);
		File directory = createDirectory(directoryPath);
		String copyTo = directory.getAbsolutePath() + File.separatorChar + aFile.getName();
		System.out.println(copyTo);
		aFile.renameTo(new File(copyTo));
		Decompiler.decompile("OAuthTokenizer", output);
		File decompiled = new File("OAuthTokenizer.java");
		FileWriter fileWriter = new FileWriter(decompiled);
		String toPrint = output.toString();
		BufferedWriter bufWriter = new BufferedWriter(fileWriter);
		int bufSize = 1024;
		int startPos = 0;
		System.out.println(toPrint.length());
		try {
			while ((startPos < toPrint.length())) {
				System.out.println(toPrint.length());
				if ((startPos + bufSize) >= toPrint.length()) {
					bufSize = toPrint.length() - 1 - startPos;
				}
				bufWriter.write(toPrint, startPos, bufSize);
				startPos = startPos + bufSize + 1;

				bufWriter.flush();
				System.out.println("Flushed Buffer");
			}
			bufWriter.close();
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
			exc.printStackTrace();
		}
		return "Good";
	}

	private static File createDirectory(String path) {
		File directoryPath = new File(path);
		directoryPath.mkdirs();
		return directoryPath;
	}

	public static void main(String[] args) throws Exception {
		try {
			System.out.println(decompile());
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
