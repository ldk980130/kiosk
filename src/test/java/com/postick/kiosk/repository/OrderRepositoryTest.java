package com.postick.kiosk.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.postick.kiosk.domain.Category;
import com.postick.kiosk.domain.Item;
import com.postick.kiosk.domain.Order;
import com.postick.kiosk.web.dto.OrderItemDto;
import com.postick.kiosk.domain.option.Size;
import com.postick.kiosk.domain.option.Temperature;

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
		List<OrderItemDto> list = new ArrayList<>();
		list.add(OrderItemDto.builder()
			.itemName("ame")
			.count(2)
			.size(Size.REGULAR)
			.temperature(Temperature.ICE)
			.content("")
			.build());

		list.add(OrderItemDto.builder()
			.itemName("cafe")
			.count(1)
			.size(Size.REGULAR)
			.temperature(Temperature.ICE)
			.content("")
			.build());

		int beforeSize = orderRepository.findAll().size();
		Order order = orderRepository.save(list, true);
		int afterSize = orderRepository.findAll().size();

		//then
		assertThat(beforeSize + 1).isEqualTo(afterSize);
		assertThat(order.getOrderItems().size()).isEqualTo(2);
		assertThat(order.getTotalPrice()).isEqualTo(9000);
	}
}