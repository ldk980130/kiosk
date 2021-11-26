package com.postick.kiosk.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.postick.kiosk.domain.Category;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryRepository {

	private final EntityManager em;

	@Transactional
	public Long save(Category category) {
		em.persist(category);
		return category.getId();
	}

	public Optional<Category> findByName(String name) {

		return em.createQuery("select c from Category c where c.name =: name", Category.class)
			.setParameter("name", name)
			.getResultStream()
			.findFirst();
	}

	public List<Category> findAll() {
		return em.createQuery("select c from Category c", Category.class)
			.getResultList();
	}
}
