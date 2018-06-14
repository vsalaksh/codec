package com.tools.decompiler.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import com.strobel.decompiler.Decompiler;
import com.strobel.decompiler.PlainTextOutput;

public class JavaDecompiler {

	public static String decompile(String compiledClass) throws ClassNotFoundException, IOException {
		PlainTextOutput output = new PlainTextOutput();
		String directoryPath = getPackageName(compiledClass);
		File directory = createDirectory(directoryPath);
		//File aFile = new File("d:\\" + compiledClass + ".class");
		//String targetDirectory = "d:\\customclasspath\\" + directoryPath.replace('/','\\');
		//File directory = createDirectory(directoryPath);
		File aFile = new File("/home/ec2-user/temp/" + compiledClass + ".class");
		String targetDirectory = "/home/ec2-user/customclasspath/" + directoryPath;
		String copyTo = targetDirectory + File.separatorChar + aFile.getName();
		
		createDirectory(targetDirectory);
		System.out.println(copyTo);
		//moveTo(aFile, targetDirectory + "\\" + compiledClass + ".class");
		moveTo(aFile, targetDirectory + "/" + compiledClass + ".class");
		
		Decompiler.decompile(directoryPath.replace('/', '.') + "." + compiledClass, output);
		
		File decompiled = new File(compiledClass + ".java");
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
		return toPrint;
	}

	private static String getPackageName(String className) throws MalformedURLException 
	{
		File file = new File("d:\\");
		File aFile = new File("d:\\" + className);
		// convert the file to URL format
		URL url = file.toURI().toURL();
		URL[] urls = new URL[] { url };

		// load this folder into Class loader
		CustomURLClassLoader cl = new CustomURLClassLoader(urls);
		String packageName = "";
		try {
			Class cls = cl.loadClass(className, false);
		} catch (Error exc) {
			String msg = exc.getMessage();
			System.out.println(msg);
			packageName = msg.substring(msg.indexOf(':')+2, msg.lastIndexOf('/'));
			//packageName = msg.substring(0, msg.lastIndexOf('/'));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(packageName);
		
		
		return packageName;
		
	}
	private static File createDirectory(String path) {
		File directoryPath = new File(path);
		directoryPath.mkdirs();
		return directoryPath;
	}
	
	private static File moveTo(File aFile, String copyTo)
	{
		System.out.println(copyTo);
		aFile.renameTo(new File(copyTo));
		return aFile;
		
	}

	public static void main(String[] args) throws Exception {
		try {
			System.out.println(decompile("CodecRequest"));
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
