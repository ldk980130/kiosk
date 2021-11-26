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
		Category x = categoryRepository.save("X");
		Category y = categoryRepository.save("Y");
		Category z = categoryRepository.save("Z");

		Item americano = Item.create(x, "ame", 3000);
		Item cafeLatte = Item.create(x, "cafe", 3000);
		Item uza = Item.create(y, "uza", 3000);
		Item greenTea = Item.create(y, "green", 3000);
		Item cake = Item.create(z, "cake", 3000);
		Item croffle = Item.create(z, "croffle", 3000);

		//when
		List<OrderItem> list = new ArrayList<>();
		list.add(OrderItem.create(americano, 2));
		list.add(OrderItem.create(uza, 1));
		list.add(OrderItem.create(cake, 1));

		Order order = orderRepository.save(list);

		//then
		Order findOrder = orderRepository.findAll().get(0);
		assertThat(findOrder.getId()).isEqualTo(order.getId());
	}
}