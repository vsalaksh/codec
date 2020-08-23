package com.codec.services.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonProperty;



public class JWTToken {
	@JsonProperty("header")
	private JWTHeader jwtHeader;
	
	@JsonProperty("body")
	private JWTBody jwtBody;
    
    public JWTToken()
    {
    	
    }

   
}

