package com.tools.formatter.services;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.tools.formatter.model.FormatterRequest;
import com.tools.formatter.model.FormatterResponse;
import com.tools.formatter.util.HTMLStringFormatter;

@Path("/formatter/html")
public class HTMLFormatter {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public FormatterResponse format(@Context HttpServletResponse response, @QueryParam("input") String str, FormatterRequest req)
	{
		String decodedValue = "";
		System.out.println("Param String Value: " + str);
		System.out.println("Parsed From Codec Request " + (req!=null? req.getInput() : "NULL"));
		FormatterResponse formatterRes = new FormatterResponse();
		try 
		{
			if (str == null)
				str = req.getInput();
			decodedValue = HTMLStringFormatter.formatHTMLString(str);
			formatterRes.setOutput(decodedValue);
			
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			formatterRes.setErrorMessage("Input is not valid");
		}
		
		
		return formatterRes;
	 
	}

}
