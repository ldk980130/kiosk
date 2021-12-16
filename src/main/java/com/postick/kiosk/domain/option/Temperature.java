package com.postick.kiosk.domain.option;

import java.util.stream.Stream;

import lombok.Getter;

/**
 * 요청사항 테이블의 애트리뷰트인 '온도'에 해당하는 Enum 클래스
 */
@Getter
public enum Temperature {

	ICE("ICE"), HOT("HOT"), NOTHING("NOTHING");

	private final String description;

	Temperature(String description) {
		this.description = description;
	}

	/**
	 * @param description : 웹 화면에서 넘어온 온도 문자열
	 * @return : 문자열을 해당 Enum 타입으로 리턴
	 */
	public static Temperature get(String description) {
		return Stream.of(Temperature.values())
			.filter(t -> t.description.equals(description))
			.findFirst()
			.orElse(null);
	}
}
