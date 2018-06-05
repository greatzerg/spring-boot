package com.example.demo;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("greatzerg")
public interface FeignService_interface {

	@RequestMapping("/hello")
	public String feignCallHello(@RequestParam("name") String name, @RequestParam("resource") String resource);
}
