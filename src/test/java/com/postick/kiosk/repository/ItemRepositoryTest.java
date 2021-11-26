package com.postick.kiosk.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.postick.kiosk.domain.Category;
import com.postick.kiosk.domain.Item;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemRepositoryTest {

	@Autowired ItemRepository itemRepository;
	@Autowired CategoryRepository categoryRepository;

	@Test
	public void saveItem() throws Exception {
		//given
		Category category = Category.create("커피");
		categoryRepository.save(category);

		//when
		Item item = Item.create(category, "아메리카노", 1500);

		//then
		List<Item> items = itemRepository.findAll();
		assertThat(items.get(0).getName()).isEqualTo("아메리카노");
	}

	@Test
	public void findByCategory() throws Exception {
		//given
		int beforeSize = itemRepository.findAll().size();

		Category category1 = Category.create("커피");
		categoryRepository.save(category1);

		Item.create(category1, "아메리카노", 1500);

		Category category2 = Category.create("차");
		categoryRepository.save(category2);

		Item.create(category2, "유자차", 1500);

		//when
		List<Item> findItems1 = category1.getItems();
		List<Item> findItems2 = category2.getItems();
		int afterSize = itemRepository.findAll().size();

		//then
		assertThat(findItems1.get(0).getName()).isEqualTo("아메리카노");
		assertThat(findItems2.get(0).getName()).isEqualTo("유자차");
		assertThat(beforeSize + 2).isEqualTo(afterSize);
	}

}