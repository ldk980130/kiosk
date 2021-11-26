package com.postick.kiosk.controller;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.postick.kiosk.domain.Category;
import com.postick.kiosk.domain.Item;
import com.postick.kiosk.repository.CategoryRepository;
import com.postick.kiosk.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	private final ItemRepository itemRepository;
	private final CategoryRepository categoryRepository;
	private final Scanner sc = new Scanner(System.in);

	@GetMapping("/item")
	@ResponseBody
	public String itemTest() {

		String item = sc.nextLine();

		Optional<Item> findItem = itemRepository.findByName(item);

		if (findItem.isPresent()) {
			return findItem.get().getName()
				+ findItem.get().getPrice()
				+ findItem.get().getCategory().getName();
		}

		return "상품이 없습니다";
	}

	@GetMapping("/category")
	@ResponseBody
	public String categoryTest() {

		String category = sc.nextLine();

		Optional<Category> findCategory = categoryRepository.findByName(category);

		if (findCategory.isPresent()) {
			return findCategory.get().getName();
		}

		return "상품이 없습니다";
	}
}
