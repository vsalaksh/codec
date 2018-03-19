package com.codec.services.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonProperty;

public class JWTBody {
	
	@JsonProperty("iss")
    private  String issuer;
	
	@JsonProperty("sub")
    private  String subject;
	
	@JsonProperty("aud")
	private  List<String> audience;
    
    @JsonProperty("exp")
    private  Date expiresAt;
    
    @JsonProperty("nbf")
    private  Date notBefore;
    
    @JsonProperty("iat")
    private  Date issuedAt;
    
    @JsonProperty("scopes")
    private List  scopes;
    
    @JsonProperty("jti")
    private String id;
    
    

}
