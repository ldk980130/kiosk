package com.postick.kiosk.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.postick.kiosk.domain.Order;
import com.postick.kiosk.web.dto.OrderDto;
import com.postick.kiosk.web.dto.OrderItemDto;
import com.postick.kiosk.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

	private final OrderRepository orderRepository;
	private static boolean takeOut = false;

	@GetMapping("/")
	public String home() {
		return "/main";
	}

	@GetMapping("/place")
	public String place() {
		return "/place";
	}

	@GetMapping("/index")
	public String index(@RequestParam(value = "take-out", required = false) boolean parameter) {
		log.info("테이크아웃={}", parameter);
		takeOut = parameter;
		return "/index";
	}

	@PostMapping("/index")
	public String order(@RequestBody Map<String, List<OrderItemDto>> data) {
		log.info("주문 컨트롤러 호출");

		List<OrderItemDto> orderItems = data.get("orderItems");

		orderRepository.save(orderItems, takeOut);

		return "redirect:/orders";
	}

	@GetMapping("/orders")
	public String showOrders(Model model) {
		List<Order> orderList = orderRepository.findAll();
		List<OrderDto> orderDtoList = new ArrayList<>();

		for (Order order : orderList) {
			orderDtoList.add(order.toOrderDto());
		}

		model.addAttribute("orders", orderDtoList);

		return "/orderList";
	}

	// @ResponseBody
	// @PostMapping("/")
	// public List<OrderDto> order(@RequestBody Map<String, List<OrderItemDto>> data,
	// 	@RequestParam(value = "take-out") boolean takeOut) {
	//
	// 	List<OrderItemDto> orderItems = data.get("orderItems");
	//
	// 	orderRepository.save(orderItems, takeOut);
	//
	// 	List<Order> orderList = orderRepository.findAll();
	// 	List<OrderDto> orderDtoList = new ArrayList<>();
	//
	// 	for (Order order : orderList) {
	// 		orderDtoList.add(order.toOrderDto());
	// 	}
	//
	// 	return orderDtoList;
	// }
}
