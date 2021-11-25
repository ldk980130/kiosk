package com.postick.kiosk.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Getter
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Long id;

	@Column(nullable = false)
	private Integer totalPrice;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();
}
