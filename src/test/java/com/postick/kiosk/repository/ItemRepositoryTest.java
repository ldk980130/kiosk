package com.postick.kiosk.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.postick.kiosk.domain.Category;
import com.postick.kiosk.domain.Item;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
public class ItemRepositoryTest {

	@Autowired ItemRepository itemRepository;
	@Autowired CategoryRepository categoryRepository;

	@Test
	public void saveItem() throws Exception {
		//given
		Category category = Category.create("커피");
		categoryRepository.save(category);
		Item item = Item.create(category, "아메리카노", 1500, 30);

		//when
		itemRepository.save(item);

		//then
		List<Item> items = itemRepository.findAll();
		assertThat(items.get(0).getName()).isEqualTo("아메리카노");
	}

	@Test
	public void findByCategory() throws Exception {
		//given
		Category category1 = Category.create("커피");
		categoryRepository.save(category1);
		Item item1 = Item.create(category1, "아메리카노", 1500, 30);

		Category category2 = Category.create("차");
		categoryRepository.save(category2);
		Item item2 = Item.create(category2, "유자차", 1500, 30);

		//when
		List<Item> findItems1 = itemRepository.findAllByCategory(category1);
		List<Item> findItems2 = itemRepository.findAllByCategory(category2);

		//then
		assertThat(findItems1.get(0).getName()).isEqualTo("아메리카노");
		assertThat(findItems2.get(0).getName()).isEqualTo("유자차");
	}

}