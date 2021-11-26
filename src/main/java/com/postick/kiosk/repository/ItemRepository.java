package com.postick.kiosk.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.postick.kiosk.domain.Category;
import com.postick.kiosk.domain.Item;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

	private final EntityManager em;
	private final CategoryRepository categoryRepository;

	@Transactional
	public Item save(Category category, String name, int price) {
		Item item = Item.create(category, name, price);
		em.persist(item);
		return item;
	}

	public Optional<Item> findByName(String name) {

		return em.createQuery("select i from Item i where i.name =: name", Item.class)
			.setParameter("name", name)
			.getResultStream()
			.findFirst();
	}

	public List<Item> findByCategory(String name) {
		Optional<Category> categoryOptional = categoryRepository.findByName(name);

		if (categoryOptional.isEmpty()) {
			throw new IllegalStateException("카테고리가 없습니다.");
		}

		return em.createQuery("select i from Item i "
			+ "inner join Category "
			+ "where i.category.name =:name")
			.setParameter("name", name)
			.getResultList();
	}

	public Item findById(Long id) {
		return em.find(Item.class, id);
	}

	public List<Item> findAll(){
		return em.createQuery("select i from Item i", Item.class)
			.getResultList();
	}
}
