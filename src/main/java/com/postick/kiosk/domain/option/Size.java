package com.postick.kiosk.domain.option;

import java.util.stream.Stream;

import lombok.Getter;

/**
 * 요청사항 테이블의 애트리뷰트인 '사이즈'에 해당하는 Enum 클래스
 */
@Getter
public enum Size {

	REGULAR("Regular"), LARGE("Large"), NOTHING("NOTHING");

	private final String description;

	Size(String description) {
		this.description = description;
	}

	/**
	 *
	 * @param description : 웹 화면에서 넘어온 사이즈 문자열
	 * @return : 문자열을 해당 Enum 타입으로 리턴
	 */
	public static Size get(String description) {
		return Stream.of(Size.values())
			.filter(s -> s.description.equals(description))
			.findFirst()
			.orElse(null);
	}
}
