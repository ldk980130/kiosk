package com.postick.kiosk.domain.option;

import java.util.stream.Stream;

public enum Size {

	REGULAR("Regular"), LARGE("Large"), NOTHING("NOTHING");

	private final String description;

	Size(String description) {
		this.description = description;
	}

	public static Size get(String description) {
		return Stream.of(Size.values())
			.filter(s -> s.description.equals(description))
			.findFirst()
			.orElse(null);
	}
}
