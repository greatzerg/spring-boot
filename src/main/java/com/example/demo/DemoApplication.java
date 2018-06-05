package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaServer
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
@RestController
public class DemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@RequestMapping("/hello")
	public Object hello(@RequestParam("name") String name, @RequestParam("resource") String resource) {
		
		return "Hello ^_^, my dear " + name + ", I'm from " + resource;
	}
	
	@Value("${spring.application.name}")
	String eurekaName;
	
	@Autowired
	RestTemplate restTemplate;
	@RequestMapping("/ribbonCallHello")
	public Object ribbonCallHello(@RequestParam("name") String name) {
		
		return restTemplate.getForObject("http://{0}/{1}?name={2}&&resource={3}", String.class, eurekaName, "hello", name, "ribbon");
	}
	
	@Autowired
	FeignService_interface feignService_interface;
	@RequestMapping("/feignCallHello")
	public Object feignCallHello(@RequestParam("name") String name) {
		
		return feignService_interface.feignCallHello(name, "feign");
	}
}
