package com.codec.services.rest.coolblue;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/decoder/url")
public class CoolURLDecoder {
	
	private URLDecoder decoder = new URLDecoder();
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public String decode(@QueryParam("val") String input)
	{
		String decodedValue = "";
		try 
		{
			decodedValue = decoder.decode(input, "UTF-8");
		} 
		catch (UnsupportedEncodingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return decodedValue;
	}

}
