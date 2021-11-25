package com.postick.kiosk.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.postick.kiosk.domain.Category;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

	private final EntityManager em;

	public Long save(Category category) {
		em.persist(category);
		return category.getId();
	}

	public List<Category> findAll() {
		return em.createQuery("select c from Category c", Category.class)
			.getResultList();
	}
}
