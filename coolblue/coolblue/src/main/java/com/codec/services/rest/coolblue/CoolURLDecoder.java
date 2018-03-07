package com.codec.services.rest.coolblue;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.codec.services.model.CodecRequest;
import com.codec.services.model.CodecResponse;


@Path("/decoder/url")
public class CoolURLDecoder {
	
	private URLDecoder decoder = new URLDecoder();
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response decode(@Context HttpServletResponse response, @QueryParam("input") String str, CodecRequest req)
	{
		String decodedValue = "";
		System.out.println("Param String Value: " + str);
		System.out.println("Parsed From Codec Request " + (req!=null? req.getInput() : "NULL"));
		try 
		{
			if (str == null)
				str = req.getInput();
			decodedValue = decoder.decode(str, "UTF-8");
		} 
		catch (UnsupportedEncodingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CodecResponse codecRes = new CodecResponse();
		codecRes.setOutput(decodedValue);
		
		
		ResponseBuilder resp = Response.ok("{\"output\":"+ "\""+decodedValue + "\"}");
		System.out.println(resp.build());
		return resp.build();
	}

}
