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
		categoryRepository.save("A");

		//when
		Category findCategory = categoryRepository.findByName("A").get();

		int beforeSize = itemRepository.findAll().size();
		Item item = itemRepository.save(findCategory, "a", 3000);
		int afterSize = itemRepository.findAll().size();

		//then
		assertThat(beforeSize + 1).isEqualTo(afterSize);
	}

	@Test
	public void findByCategory() throws Exception {
		//given
		int beforeSize = itemRepository.findAll().size();

		Category category1 = categoryRepository.save("A");

		Item.create(category1, "a", 1500);

		Category category2 = categoryRepository.save("B");

		Item.create(category2, "b", 1500);

		//when
		List<Item> findItems1 = category1.getItems();
		List<Item> findItems2 = category2.getItems();
		int afterSize = itemRepository.findAll().size();

		//then
		assertThat(findItems1.get(0).getName()).isEqualTo("a");
		assertThat(findItems2.get(0).getName()).isEqualTo("b");
		assertThat(beforeSize + 2).isEqualTo(afterSize);
	}

}