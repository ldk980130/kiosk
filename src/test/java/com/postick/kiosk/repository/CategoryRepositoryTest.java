package com.postick.kiosk.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.postick.kiosk.domain.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CategoryRepositoryTest {

	@Autowired private CategoryRepository categoryRepository;

	@Test
	public void findByName() throws Exception {
		//given
		categoryRepository.save(Category.create("A"));
		categoryRepository.save(Category.create("B"));
		categoryRepository.save(Category.create("A"));

		//when
		List<Category> category = categoryRepository.findByName("B");

		//then
		assertThat(category.get(0).getName()).isEqualTo("B");
	}

}