package com.codec.services.rest.coolblue;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

@Path("/encoder/url")
public class CoolURLEncoder {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response encode(@Context HttpServletResponse response, @QueryParam("input") String str, CodecRequest req)
	{
		String encodedValue = "";
		System.out.println("Param String Value: " + str);
		System.out.println("Parsed From Codec Request " + (req!=null? req.getInput() : "NULL"));
		try 
		{
			if (str == null)
				str = req.getInput();
			encodedValue = URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
		} 
		catch (UnsupportedEncodingException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CodecResponse codecRes = new CodecResponse();
		codecRes.setOutput(encodedValue);
		
		
		ResponseBuilder resp = Response.ok("{\"output\":"+ "\""+encodedValue + "\"}");
		System.out.println(resp.build());
		return resp.build();
	}

}
