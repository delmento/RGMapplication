package com.bludots.app.rgm.password.registration.valueobjects;

import java.time.LocalDateTime;

public class RequestVO {
	
	private Long id;
	private String email;
	private String generateToken;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGenerateToken() {
		return generateToken;
	}
	public void setGenerateToken(String generateToken) {
		this.generateToken = generateToken;
	}
	
}
