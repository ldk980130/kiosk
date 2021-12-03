package com.postick.kiosk.converter;

import javax.persistence.AttributeConverter;

/**
 * Order 클래스의 변수인 'takeOut'은 boolean 타입으로 DB에 저장되면 0, 1로 나타난다.
 * 테이크 아웃 여부를 알아보기 쉽게 하기 위해 'takeOut'이 true인 경우 'Y', false인 경우엔 'N'으로 변환해주는 클래스
 */

public class BooleanToYNConverter implements AttributeConverter<Boolean, String> {

	/**
	 * Boolean 값을 Y 또는 N으로 컨버트
	 *
	 * @param attribute boolean 값
	 * @return String true인 경우 Y, false인 경우 N
	 */
	@Override
	public String convertToDatabaseColumn(Boolean attribute) {
		return (attribute != null && attribute) ? "Y" : "N";
	}

	/**
	 * Y 또는 N을 Boolean으로 컨버트
	 * @param dbData Y 또는 N
	 * @return boolean 대소문자 상관 없이 Y인 경우 true, N인 경우 false
	 */
	@Override
	public Boolean convertToEntityAttribute(String dbData) {
		return "Y".equalsIgnoreCase(dbData);
	}
}
