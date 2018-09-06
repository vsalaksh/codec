package com.codec.services.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class JWTHeader {

	@JsonProperty("typ")
    private String type;
	
	@JsonProperty("alg")
    private String alg;
	
	@JsonProperty("jku")
	private String jku;
	
	@JsonProperty("jwk")
	private String jwk;

	@JsonProperty("kid")
	private String kid;
	
	@JsonProperty("x5u")
	private String x5u;
	
	@JsonProperty("x5c")
	private String x5c;
	
	@JsonProperty("x5t")
	private String x5t;
	
	@JsonProperty("x5t#S256")
	private String x5tS256;
	
	@JsonProperty("cty")
	private String cty;
	
	@JsonProperty("crit")
	private String crit;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAlg() {
		return alg;
	}

	public void setAlg(String alg) {
		this.alg = alg;
	}

	public String getJku() {
		return jku;
	}

	public void setJku(String jku) {
		this.jku = jku;
	}

	public String getJwk() {
		return jwk;
	}

	public void setJwk(String jwk) {
		this.jwk = jwk;
	}

	public String getKid() {
		return kid;
	}

	public void setKid(String kid) {
		this.kid = kid;
	}

	public String getX5u() {
		return x5u;
	}

	public void setX5u(String x5u) {
		this.x5u = x5u;
	}

	public String getX5c() {
		return x5c;
	}

	public void setX5c(String x5c) {
		this.x5c = x5c;
	}

	public String getX5t() {
		return x5t;
	}

	public void setX5t(String x5t) {
		this.x5t = x5t;
	}

	public String getX5tS256() {
		return x5tS256;
	}

	public void setX5tS256(String x5tS256) {
		this.x5tS256 = x5tS256;
	}

	public String getCty() {
		return cty;
	}

	public void setCty(String cty) {
		this.cty = cty;
	}

	public String getCrit() {
		return crit;
	}

	public void setCrit(String crit) {
		this.crit = crit;
	}
	
	
	
}
