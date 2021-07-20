package com.example.mvc;

import com.example.mvc.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MvcApplicationTests {

	@Test
	void contextLoads() throws JsonProcessingException {
		System.out.println("--------------------");
		var objectMapper = new ObjectMapper();

		var user = User.builder()
				.name("steve")
				.age(10).build();
		// object mapper 는 get method 사용
		var text = objectMapper.writeValueAsString(user);
		System.out.println(text);

		var objectUser = objectMapper.readValue(text, User.class);
		System.out.println(objectUser);

		// 없는 field를 넣으면 안됨...
		var serial = "{\"name\":\"steve\",\"field\":10}";
		objectUser = objectMapper.readValue(serial, User.class);
		System.out.println(objectUser);
	}

}
