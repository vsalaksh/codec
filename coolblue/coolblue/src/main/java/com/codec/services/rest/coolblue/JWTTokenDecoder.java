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

import org.codehaus.jackson.map.ObjectMapper;

import com.codec.services.exception.InvalidInputException;
import com.codec.services.model.CodecRequest;
import com.codec.services.model.JWTHeader;
import com.codec.services.model.JWTResponse;
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
	public JWTResponse decode(@Context HttpServletResponse response, @QueryParam("input") String str,
			CodecRequest req) {
		JWTResponse resp = null;
		String decodedValue = "";
		System.out.println("Param String Value: " + str);
		System.out.println("Parsed From Codec Request " + (req != null ? req.getInput() : "NULL"));

		if (str == null)
			str = req.getInput();
		RawJWTToken jwtToken;
		try {
			jwtToken = parser.parse(str);
			formatJWTToken(jwtToken);
			String signingAlg = "";
			try {
				JWTHeader headerJSON = new ObjectMapper().readValue(jwtToken.getHeader(), JWTHeader.class);
				signingAlg = headerJSON.getAlg();
			}
			catch (Exception exc)
			{
				//ignored
				System.out.println("ALERT : Address this issue");
				exc.printStackTrace();
			}
			resp = new JWTResponse(jwtToken.toString(), jwtToken.getHeader(), jwtToken.getBody(), signingAlg);			
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resp.setErrorMessage(e.getMessage());
			System.out.println(e.getMessage());
		}

		return resp;

	}

	private void formatJWTToken(RawJWTToken jwtToken) {
		try
		{
			jwtToken.setHeader(JSONStringFormatter.formatJSONString(jwtToken.getHeader()));
			jwtToken.setBody(JSONStringFormatter.formatJSONString(jwtToken.getBody()));
		}
		catch (Exception exc)
		{
			//ignore the exception during formatting
			exc.printStackTrace();
			System.out.println("JWT Formatting failed : " + exc.getMessage());
		}
	}

}
