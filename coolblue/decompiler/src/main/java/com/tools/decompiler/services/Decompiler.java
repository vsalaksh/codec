package com.tools.decompiler.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.tools.decompiler.config.DecompilerConfig;
import com.tools.decompiler.services.model.DecompilerResponse;
import com.tools.decompiler.util.JavaDecompiler;

@Path("/decompiler/java")
public class Decompiler {

	@POST
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response decompile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		//String fileLocation = "d:\\AgilePOD\\" + fileDetail.getFileName();
		//String fileLocation = "/home/ec2-user/temp/" + fileDetail.getFileName();
		// saving file
		String fileLocation = DecompilerConfig.getInstance().getTempClassPath() +  File.separatorChar + fileDetail.getFileName();
		int fileSizeLimit = DecompilerConfig.getInstance().getFileSizeLimit();
		File aFile = new File(fileLocation);
		boolean isBigFile = false;
		DecompilerResponse res = new DecompilerResponse();
		try {
			
			System.out.println("Class file creation in progress");
			FileOutputStream out = new FileOutputStream(aFile);
			int read = 0;
			int bufferSize = DecompilerConfig.getInstance().getBufferSize();
			byte[] bytes = new byte[bufferSize];
			int readSize = 0;
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
				readSize += bufferSize;
				if (readSize > fileSizeLimit)
				{
					isBigFile = true;
					System.out.println("File " + fileDetail.getFileName() + " is big file, Size : " + readSize);
					System.out.println("Class file creation stopped");
					break;
				}
			}
			out.flush();
			out.close();
			System.out.println("Class file creation completed");
			if (aFile.length() > fileSizeLimit)
			{
				 System.out.println("File " + fileDetail.getFileName() + " Size  : " + aFile.length());
				 isBigFile = true;
			}
			aFile = null;
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		File responseFile = null;
		try {
		String output = "File successfully uploaded to : " + fileLocation;
		String fullName = fileDetail.getFileName();
		
		if (!isBigFile)
		{
			
		String decompiledJavaFileAsString = null;
		try {
			responseFile = JavaDecompiler.decompile(fullName.substring(0, fullName.indexOf(".")));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		res.setDecompiledCode(decompiledJavaFileAsString);
		res.setFileName(fullName.substring(0, fullName.indexOf(".")) + ".java");
		}
		else
		{
			res.setDecompiledCode("ERROR : Unable to Decompile");
		   
		}
		}
		catch (Throwable throwable)
		{
			throwable.printStackTrace();
			System.out.println("Check this error : " + throwable.getMessage());
		}
		ResponseBuilder response = Response.ok((Object) responseFile);
	    response.header("Content-Disposition", "attachment; filename=" + responseFile.getName());
	    return response.build();
		//return Response.ok(responseFile, MediaType.APPLICATION_OCTET_STREAM)
	      //.header("Content-Disposition", "attachment; filename=\"" + responseFile.getName() + "\"" ) //optional
	      //.build();
		//return res;

	}

}
