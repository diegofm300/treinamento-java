package com.treinamentojava.treinamentojava;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class TreinamentoJavaApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void meuTeste(){
		System.out.println(new Date());
		System.out.println(new Date(System.currentTimeMillis()));
	}

}
