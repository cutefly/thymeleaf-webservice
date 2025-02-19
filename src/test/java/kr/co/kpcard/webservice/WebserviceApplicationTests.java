package kr.co.kpcard.webservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.sqids.Sqids;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class WebserviceApplicationTests {

	// @Test
	// void contextLoads() {
	// }

	@Test
	void testSqids() {

		Sqids sqids = Sqids.builder()
				.minLength(8)
				.alphabet("vPQ0MdX1YSu7nkU5WJDzRwGEqFxcZAjB3KgNoiTV6lm294bCIpOthyHa8sLref")
				.build();

		Long sequence = 64339296874L;
		String id = sqids.encode(Arrays.asList(sequence)); // "86Rf07xd4z"
		List<Long> numbers = sqids.decode(id); // [1, 2, 3]
		log.info("id : {}, numbers : {}", id, numbers);
		assertEquals(sequence, numbers.getFirst());
	}
}
