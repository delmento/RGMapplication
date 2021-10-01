package com.bludots.app.rgm.password.registration.services.impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bludots.app.rgm.password.registration.repositories.RequestRepository;
import com.bludots.app.rgm.password.registration.repositories.entities.Request;
import com.bludots.app.rgm.password.registration.services.EmailService;
import com.bludots.app.rgm.password.registration.services.RequestService;
import com.bludots.app.rgm.password.registration.services.impl.token.WebToken;
import com.bludots.app.rgm.password.registration.valueobjects.RequestVO;

@Transactional
@Service
public class RequestServiceimpl implements RequestService {

	@Autowired
	private RequestRepository requestRepository;

	private WebToken token = new WebToken();

	private EmailService sender;

	public RequestServiceimpl(EmailService sender) {
		super();
		this.sender = sender;
	}

	@Override
	public Request persistRequest(RequestVO vo) {
		Request request = new Request();
		request.setEmail(vo.getEmail());
		request.setRequestDateTime(LocalDateTime.now());

		// SEND TOKEN
		String generateToken = token.generateToken();
		request.setToken(generateToken);

		// SEND MAIL
		String link = "http://localhost:9800/rgm.pwd.reg/pwd?token="+ generateToken;
		sender.send(request.getEmail(), buildEmail(request.getEmail(), link));

		return requestRepository.save(request);
	}

	public String buildEmail(String email, String link) {
		return "Email: " + email + " <a href=" + link + " target=\\\"_blank\\\">click this link</a> ";
	}
}
