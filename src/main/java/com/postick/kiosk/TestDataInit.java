package com.postick.kiosk;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.postick.kiosk.domain.Category;
import com.postick.kiosk.domain.Item;
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

	static String COFFEE = "COFFEE";
	static String TEA = "TEA";
	static String JUICE = "JUICE";
	static String BAKERY = "BAKERY";

	@PostConstruct
	public void dataInit() {

		Category coffee = categoryRepository.save(COFFEE);
		Category tea = categoryRepository.save(TEA);
		Category juice = categoryRepository.save(JUICE);
		Category bakery = categoryRepository.save(BAKERY);

		itemRepository.save(coffee, "아메리카노", 3700);
		itemRepository.save(coffee, "에스프레소", 2500);
		itemRepository.save(coffee, "카페라떼", 4000);
		itemRepository.save(coffee, "카푸치노", 4000);
		itemRepository.save(coffee, "카페모카", 4300);
		itemRepository.save(coffee, "헤이즐넛 라떼", 4300);
		itemRepository.save(coffee, "카라멜 마끼아또", 4300);
		itemRepository.save(coffee, "바닐라 라떼", 4300);

		itemRepository.save(tea, "유자차", 4500);
		itemRepository.save(tea, "허니 레몬티", 4500);
		itemRepository.save(tea, "허니 자몽티", 4500);
		itemRepository.save(tea, "캐모마일티", 4000);
		itemRepository.save(tea, "루이보스티", 4000);
		itemRepository.save(tea, "페퍼민트티", 4000);
		itemRepository.save(tea, "얼그레이티", 4000);
		itemRepository.save(tea, "녹차", 4000);

		itemRepository.save(juice, "바나나 주스", 5500);
		itemRepository.save(juice, "딸기 주스", 6000);
		itemRepository.save(juice, "망고 주스", 6000);
		itemRepository.save(juice, "토마토 주스", 6000);
		itemRepository.save(juice, "레몬 에이드", 4500);
		itemRepository.save(juice, "자몽 에이드", 4500);
		itemRepository.save(juice, "블루베리 에이드", 4500);
		itemRepository.save(juice, "망고 에이드", 4500);

		itemRepository.save(bakery, "에그 치즈 토스트", 3500);
		itemRepository.save(bakery, "클럽 샌드위치", 5000);
		itemRepository.save(bakery, "로투스 찹쌀떡", 4000);
		itemRepository.save(bakery, "공룡알고구마빵", 2500);
		itemRepository.save(bakery, "말렌카 케이크(허니)", 4800);
		itemRepository.save(bakery, "말렌카 케이크(코코아)", 4800);
		itemRepository.save(bakery, "감자빵", 2500);

	}
}
