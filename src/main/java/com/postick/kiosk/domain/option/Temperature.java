package com.postick.kiosk.domain.option;

import java.util.stream.Stream;

import lombok.Getter;

@Getter
public enum Temperature {

	ICE("ICE"), HOT("HOT"), NOTHING("NOTHING");

	private final String description;

	Temperature(String description) {
		this.description = description;
	}

	public static Temperature get(String description) {
		return Stream.of(Temperature.values())
			.filter(t -> t.description.equals(description))
			.findFirst()
			.orElse(null);
	}
}
