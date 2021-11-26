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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.postick.kiosk.domain.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CategoryRepositoryTest {

	@Autowired private CategoryRepository categoryRepository;

	@Test(expected = DataIntegrityViolationException.class)
	public void nameUnique() throws Exception {
		//given
		categoryRepository.save("abc");
		//when
		categoryRepository.save("abc");

		//then
		fail();
	}

	@Test
	public void findByName() throws Exception {
		//given
		categoryRepository.save("A");
		categoryRepository.save("B");
		categoryRepository.save("C");

		//when
		Optional<Category> category = categoryRepository.findByName("B");

		//then
		assertThat(category.get().getName()).isEqualTo("B");
	}

}