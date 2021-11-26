package com.postick.kiosk.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.postick.kiosk.domain.Item;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

	private final EntityManager em;

	public Long save(Item item) {
		em.persist(item);
		return item.getId();
	}

	public Item findById(Long id) {
		return em.find(Item.class, id);
	}

	public List<Item> findAll(){
		return em.createQuery("select i from Item i", Item.class)
			.getResultList();
	}
}
