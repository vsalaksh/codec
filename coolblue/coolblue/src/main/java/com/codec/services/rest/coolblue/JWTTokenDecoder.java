package com.codec.services.rest.coolblue;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codec.services.model.CodecRequest;
import com.codec.services.model.CodecResponse;

@Path("/decoder/jwt")
public class JWTTokenDecoder {
	
	private Base64.Decoder decoder = Base64.getDecoder();
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response decode(@Context HttpServletResponse response, @QueryParam("input") String str, CodecRequest req) {
		CodecResponse resp = new CodecResponse();
		String decodedValue = "";
		System.out.println("Param String Value: " + str);
		System.out.println("Parsed From Codec Request " + (req != null ? req.getInput() : "NULL"));

		if (str == null)
			str = req.getInput();

		String[] split_string = str.split("\\.");
		String base64EncodedHeader = split_string[0];
		String base64EncodedBody = split_string[1];
		String base64EncodedSignature = split_string[2];
		String jwtHeader = decoder.decode(base64EncodedHeader);
		String jwtBody = decoder.decode(base64EncodedBody);
		
		
		return null;

	}

}
