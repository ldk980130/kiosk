package com.postick.kiosk;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.postick.kiosk.domain.Category;
import com.postick.kiosk.domain.Item;
import com.postick.kiosk.domain.OrderItem;
import com.postick.kiosk.domain.Request;
import com.postick.kiosk.domain.option.Size;
import com.postick.kiosk.domain.option.Temperature;
import com.postick.kiosk.repository.CategoryRepository;
import com.postick.kiosk.repository.ItemRepository;
import com.postick.kiosk.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestDataInit {

	private final CategoryRepository categoryRepository;
	private final ItemRepository itemRepository;
	private final OrderRepository orderRepository;

	static String COFFEE = "커피";
	static String TEA = "차";
	static String JUICE = "주스";
	static String BAKERY = "빵";

	@PostConstruct
	public void dataInit() {

		Category coffee = categoryRepository.save(COFFEE);
		Category tea = categoryRepository.save(TEA);
		Category juice = categoryRepository.save(JUICE);
		Category bakery = categoryRepository.save(BAKERY);

		Item americano = itemRepository.save(coffee, "아메리카노", 3700);
		Item cafeLatte = itemRepository.save(coffee, "카페라떼", 4000);
		Item cafeMocha = itemRepository.save(coffee, "카페모카", 4300);
		Item hazelnut = itemRepository.save(coffee, "헤이즐넛 라떼", 4300);

		Item citron = itemRepository.save(tea, "유자차", 3300);
		Item greenTea = itemRepository.save(tea, "녹차", 3300);

		Item berrySmoothie = itemRepository.save(juice, "딸기 스무디", 4500);
		Item yogurtSmoothie = itemRepository.save(juice, "요거트 스무디", 4300);

		Item cake = itemRepository.save(bakery, "케이크", 5500);
		Item croffle = itemRepository.save(bakery, "크로플", 5000);

	}
}
