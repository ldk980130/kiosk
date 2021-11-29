package com.postick.kiosk.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.postick.kiosk.domain.option.Size;
import com.postick.kiosk.domain.option.Temperature;

import lombok.Getter;

@Entity
@Getter
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_id")
	private Long id;

	private String content;

	@Enumerated(EnumType.STRING)
	private Size size;

	@Enumerated(EnumType.STRING)
	private Temperature temperature;

	public static Request create(Size size, Temperature temperature, String content) {
		Request request = new Request();
		request.size = size;
		request.temperature = temperature;
		request.content = content;
		return request;
	}
}
