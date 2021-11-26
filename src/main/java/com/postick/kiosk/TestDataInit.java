package com.postick.kiosk;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.postick.kiosk.domain.Category;
import com.postick.kiosk.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestDataInit {

	private final CategoryRepository categoryRepository;

	@PostConstruct
	public void dataInit() {

		categoryRepository.save(Category.create("커피"));
		categoryRepository.save(Category.create("차"));
		categoryRepository.save(Category.create("스무디"));
		categoryRepository.save(Category.create("디저트"));
	}
}
