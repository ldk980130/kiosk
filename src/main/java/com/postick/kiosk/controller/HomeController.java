package com.postick.kiosk.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.postick.kiosk.domain.Order;
import com.postick.kiosk.domain.dto.OrderDto;
import com.postick.kiosk.domain.dto.OrderItemDto;
import com.postick.kiosk.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

	private final OrderRepository orderRepository;

	@ResponseBody
	@PostMapping("/")
	public List<OrderDto> test(@RequestBody Map<String, List<OrderItemDto>> data,
		@RequestParam(value = "take-out") boolean takeOut) {

		List<OrderItemDto> orderItems = data.get("orderItems");

		orderRepository.save(orderItems, takeOut);

		List<Order> orderList = orderRepository.findAll();
		List<OrderDto> orderDtoList = new ArrayList<>();

		for (Order order : orderList) {
			orderDtoList.add(order.toOrderForm());
		}

		return orderDtoList;
	}
}
