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

@Entity
@Getter
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer price;

	@Column(nullable = false)
	private Integer stockQuantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	public static Item create(Category category, String name, int price, int stockQuantity) {

		Item item = new Item();

		item.name = name;
		item.price = price;
		item.stockQuantity = stockQuantity;

		item.category = category;
		category.getItems().add(item);

		return item;
	}

	/**
	 * stock 추가
	 */
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}

	/**
	 * stock 감소
	 */
	public void removeStock(int quantity) {

		int restStock = this.stockQuantity - quantity;

		if (restStock < 0) {
			throw new IllegalStateException("need more stock");
		}

		this.stockQuantity = restStock;
	}
}
