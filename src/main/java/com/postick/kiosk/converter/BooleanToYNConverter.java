package com.postick.kiosk.converter;

import javax.persistence.AttributeConverter;

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
