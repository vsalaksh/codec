package com.codec.services.rest.coolblue;

import java.util.Base64;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.codec.services.exception.InvalidInputException;
import com.codec.services.model.CodecRequest;
import com.codec.services.model.CodecResponse;
import com.codec.services.model.RawJWTToken;
import com.codec.services.util.JSONStringFormatter;
import com.codec.services.util.JWTParser;

@Path("/decoder/jwt")
public class JWTTokenDecoder {

	private Base64.Decoder decoder = Base64.getDecoder();
	private JWTParser parser = new JWTParser();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CodecResponse decode(@Context HttpServletResponse response, @QueryParam("input") String str,
			CodecRequest req) {
		CodecResponse resp = new CodecResponse();
		String decodedValue = "";
		System.out.println("Param String Value: " + str);
		System.out.println("Parsed From Codec Request " + (req != null ? req.getInput() : "NULL"));

		if (str == null)
			str = req.getInput();
		RawJWTToken jwtToken;
		try {
			jwtToken = parser.parse(str);
			resp.setOutput(jwtToken.toString());
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resp.setErrorMessage(e.getMessage());
		}

		return resp;

	}

}
