package com.postick.kiosk.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;

/**
 * 상품 테이블에 매핑되는 클래스
 */

@Entity
@Getter
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long id; // 기본키

	@Column(nullable = false, unique = true)
	private String name; // 상품 이름

	@Column(nullable = false)
	private Integer price; // 상품 가격

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category; // 상품 카테고리

	/**
	 * Item 클래스 생성 메소드
	 */
	public static Item create(Category category, String name, int price) {

		Item item = new Item();

		item.name = name;
		item.price = price;

		item.category = category;
		category.getItems().add(item);

		return item;
	}

}
