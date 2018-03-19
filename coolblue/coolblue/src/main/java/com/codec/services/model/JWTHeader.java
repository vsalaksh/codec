package com.codec.services.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class JWTHeader {

	@JsonProperty("typ")
    private String type;
	
	@JsonProperty("alg")
    private String alg;
	
}
