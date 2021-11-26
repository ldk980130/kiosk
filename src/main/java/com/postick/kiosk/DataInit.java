package com.postick.kiosk;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.postick.kiosk.domain.Category;
import com.postick.kiosk.domain.Item;
import com.postick.kiosk.domain.OrderItem;
import com.postick.kiosk.repository.CategoryRepository;
import com.postick.kiosk.repository.ItemRepository;
import com.postick.kiosk.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInit {

	private final CategoryRepository categoryRepository;
	private final ItemRepository itemRepository;
	private final OrderRepository orderRepository;

	static String COFFEE = "커피";
	static String TEA = "차";
	static String SMOOTHIE = "스무디";
	static String DESSERT = "디저트";

	@PostConstruct
	public void dataInit() {

		Category coffee = categoryRepository.save(COFFEE);
		Category tea = categoryRepository.save(TEA);
		Category smoothie = categoryRepository.save(SMOOTHIE);
		Category dessert = categoryRepository.save(DESSERT);

		Item americano = itemRepository.save(coffee, "아메리카노", 3000);
		Item cafeLatte = itemRepository.save(coffee, "카페라떼", 3500);
		Item citron = itemRepository.save(tea, "유자차", 3300);
		Item greenTea = itemRepository.save(tea, "녹차", 3300);
		Item berrySmoothie = itemRepository.save(smoothie, "딸기 스무디", 4500);
		Item yogurtSmoothie = itemRepository.save(smoothie, "요거트 스무디", 4300);
		Item cake = itemRepository.save(dessert, "케이크", 5500);
		Item croffle = itemRepository.save(dessert, "크로플", 5000);

		List<OrderItem> list = new ArrayList<>();
		list.add(OrderItem.create(americano, 2));
		list.add(OrderItem.create(cafeLatte, 1));
		list.add(OrderItem.create(citron, 1));
		list.add(OrderItem.create(greenTea, 1));
		list.add(OrderItem.create(berrySmoothie, 2));
		list.add(OrderItem.create(yogurtSmoothie, 1));
		list.add(OrderItem.create(cake, 1));
		list.add(OrderItem.create(croffle, 1));

		orderRepository.save(list);
	}
}
