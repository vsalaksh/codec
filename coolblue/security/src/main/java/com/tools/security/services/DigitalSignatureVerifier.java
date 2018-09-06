package com.tools.security.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tools.security.ds.DigitalSignature;
import com.tools.security.ds.DigitalSignatureProcessor;
import com.tools.security.ds.SignatureNotSupportedException;
import com.tools.security.model.DigitalSignatureRequest;
import com.tools.security.model.DigitalSignatureResponse;
import com.tools.security.util.DigitalSignatureUtil;

@Path("/security/digitalsignature")
public class DigitalSignatureVerifier {
	
	private DigitalSignatureProcessor signatureProcessor = new DigitalSignatureProcessor();
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public DigitalSignatureResponse verifyDigitalSignature(DigitalSignatureRequest req)
	{
		DigitalSignatureResponse res = new DigitalSignatureResponse();
		boolean isValidSignature = false;
		
		String validationStatus = DigitalSignatureUtil.validateRequest(req);
		if (validationStatus.equals("SUCCESS"))
		{
		String signingKey = DigitalSignatureUtil.getSigningKey(req);
		 
		DigitalSignature signature = new DigitalSignature(req.getPayload(), req.getSignature(), signingKey, req.getAlg());
		try {
			isValidSignature = signatureProcessor.verify(signature);
			if (isValidSignature)
			{
				System.out.println("JWT : Signature Verified : Algorithm " + req.getAlg());
			}
			else
			{
				System.out.println("JWT : Signature Verification Failed : Algorithm " + req.getAlg());
			}
		} catch (SignatureNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		}
		else
		{
			res.setErrorMessage(validationStatus);
			System.out.println("JWT : Digital Signature - Invalid Input Parameters " + validationStatus);
		}
		res.setValid(isValidSignature);
		return res;
	}
	

}
