package com.postick.kiosk.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.postick.kiosk.domain.Category;
import com.postick.kiosk.domain.Item;
import com.postick.kiosk.domain.Order;
import com.postick.kiosk.domain.OrderItem;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderRepositoryTest {

	@Autowired OrderRepository orderRepository;
	@Autowired CategoryRepository categoryRepository;
	@Autowired ItemRepository itemRepository;

	@Test
	public void saveOrder() throws Exception {
		//given
		Category coffeeCategory = Category.create("X");
		Category teaCategory = Category.create("Y");
		Category dessertCategory = Category.create("Z");
		categoryRepository.save(coffeeCategory);
		categoryRepository.save(teaCategory);
		categoryRepository.save(dessertCategory);

		Item americano = Item.create(coffeeCategory, "ame", 3000);
		Item cafeLatte = Item.create(coffeeCategory, "cafe", 3000);
		Item uza = Item.create(teaCategory, "uza", 3000);
		Item greenTea = Item.create(teaCategory, "green", 3000);
		Item cake = Item.create(dessertCategory, "cake", 3000);
		Item croffle = Item.create(dessertCategory, "croffle", 3000);

		//when
		List<OrderItem> list = new ArrayList<>();
		list.add(OrderItem.create(americano, 2));
		list.add(OrderItem.create(uza, 1));
		list.add(OrderItem.create(cake, 1));

		Order order = Order.create(list);
		orderRepository.save(order);

		//then
		Order findOrder = orderRepository.findAll().get(0);
		assertThat(findOrder.getId()).isEqualTo(order.getId());
	}
}