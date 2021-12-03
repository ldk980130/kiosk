package com.postick.kiosk.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;

/**
 * 카테고리 테이블에 매핑되는 클래스
 */

@Entity
@Getter
public class Category {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long id; // 기본키

	@Column(nullable = false, unique = true)
	private String name; // 카테고리 이름

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Item> items = new ArrayList<>(); // 카테고리의 Item들

	/**
	 * Category 클래스 생성 메소드
	 */
	public static Category create(String name) {

		Category category = new Category();
		category.name = name;

		return category;
	}

}
