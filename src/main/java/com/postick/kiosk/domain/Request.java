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

/**
 * 요청사항 테이블에 매핑되는 클래스
 */

@Entity
@Getter
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_id")
	private Long id; // 기본키

	private String content; // 구체적인 요청사항

	@Enumerated(EnumType.STRING)
	private Size size; // REGULAR, LARGE, NOTHING 중 하나인 사이즈 속성

	@Enumerated(EnumType.STRING)
	private Temperature temperature; // ICE, HOT, NOTHING 중 하나인 온도 속성

	/**
	 * 요청사항 생성 메소드
	 */
	public static Request create(Size size, Temperature temperature, String content) {
		Request request = new Request();
		request.size = size;
		request.temperature = temperature;
		request.content = content;
		return request;
	}
}
