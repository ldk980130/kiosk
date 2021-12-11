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

	/**
	 * Get 요청으로 "/"이  들어오면 실행되는 메소드
	 * @return main.html을 보여준다.
	 */
	@GetMapping("/")
	public String home() {
		return "/main";
	}

	/**
	 * Get 요청으로 "/place"가 들어오면 실행되는 메소드
	 * @return place.html을 보여준다.
	 */
	@GetMapping("/place")
	public String place() {
		return "/place";
	}

	/**
	 * Get 요청으로 "/index"가 들어오면 실행되는 메소드
	 * @return index.html을 보여준다.
	 */
	@GetMapping("/index")
	public String index(@RequestParam(value = "take-out", required = false) boolean parameter) {
		log.info("테이크아웃={}", parameter);
		takeOut = parameter;
		return "/index";
	}

	/**
	 * Post 요청으로 "/index"가 들어오면 실행되는 메소드
	 * @param data : 주문 상품과 요청사항이 들어있는 OrderItemDto 클래스
	 * 주문을 저장한다.
	 * @return 웹 화면에서 alert()에 띄울 메세지
	 */
	@PostMapping("/index")
	@ResponseBody
	public String order(@RequestBody Map<String, List<OrderItemDto>> data) {
		log.info("주문 컨트롤러 호출");

		List<OrderItemDto> orderItems = data.get("orderItems");

		orderRepository.save(orderItems, takeOut);

		return "주문 완료!";
	}

	/**
	 * Get 요청으로 "/orders"가 들어오면 실행되는 메소드
	 * @param model : 웹 화면으로 보여줄 주문상품 Dto 클래스를 담을 Model 객체
	 * @return 주문 내역을 보여주는 orderList.html을 보여준다.
	 */
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
}
