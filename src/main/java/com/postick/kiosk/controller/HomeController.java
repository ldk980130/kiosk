package com.postick.kiosk.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.postick.kiosk.domain.Order;
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
	@PostMapping("/api-test")
	public String test(@RequestBody OrderItemDto orderItemDto) {
		log.info("itemName={}", orderItemDto.getItemName());
		log.info("option={}", orderItemDto.getSize());
		log.info("option={}", orderItemDto.getTemperature());

		List<OrderItemDto> list = new ArrayList<>();
		list.add(orderItemDto);
		orderRepository.save(list, true);

		return "ok";
	}
}
