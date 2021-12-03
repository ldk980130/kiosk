package com.postick.kiosk.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.postick.kiosk.domain.Category;
import com.postick.kiosk.domain.Item;

import lombok.RequiredArgsConstructor;

/**
 * 상품 테이블에 접근할 수 있는 Repository 클래스
 */

@Repository
@RequiredArgsConstructor
public class ItemRepository {

	private final EntityManager em; // DB에 접근할 수 있는 엔티티 매니저

	/**
	 * 상품을 DB에 저장하는 메소드
	 * 카테고리, 이름, 가격을 매개변수로 받아 Item을 생성
	 * 엔티티 매니저에 persist(영속)하면 DB의 Item 테이블에 저장된다.
	 * @Transactional 애노테이션 : 이 메소드가 한 트랜잭션 안에서 수행되어야 함을 의미
	 */
	@Transactional
	public Item save(Category category, String name, int price) {
		Item item = Item.create(category, name, price);
		em.persist(item);
		return item;
	}

	/**
	 * 상품을 이름으로 조회하는 메소드
	 * 매개변수로 받은 이름으로 JPQL을 통해 상품 테이블에서 원하는 객체를 가져올 수 있다.
	 */
	public Optional<Item> findByName(String name) {
		return em.createQuery("select i from Item i where i.name =: name")
			.setParameter("name", name)
			.getResultStream()
			.findFirst();
	}

	/**
	 * 모든 상품을 조회하는 메소드
	 * JPQL을 사용해 모든 Item 객체를 가져올 수 있다.
	 */
	public List<Item> findAll(){
		return em.createQuery("select i from Item i", Item.class)
			.getResultList();
	}
}
