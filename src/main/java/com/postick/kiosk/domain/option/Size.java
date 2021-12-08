package com.postick.kiosk.domain.option;

import java.util.stream.Stream;

public enum Size {

	REGULAR("REGULAR"), LARGE("LARGE"), NOTHING("NOTHING");

	private final String description;

	Size(String description) {
		this.description = description;
	}

	public static Size get(String description) {
		return Stream.of(Size.values())
			.filter(s -> s.description == description)
			.findFirst()
			.orElse(null);
	}
}
