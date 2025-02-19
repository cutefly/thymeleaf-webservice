package kr.co.kpcard.webservice.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.sqids.Sqids;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HashService {

    private Sqids sqids = Sqids.builder()
            .minLength(8)
            .alphabet("vPQ0MdX1YSu7nkU5WJDzRwGEqFxcZAjB3KgNoiTV6lm294bCIpOthyHa8sLref")
            .build();

    public String encode(Long sequence) {
        String id = sqids.encode(Arrays.asList(sequence));

        log.info("sequence: {}, id: {}", sequence, id);
        return id;
    }

    public Long decode(String id) {
        List<Long> numbers = sqids.decode(id);
        Long sequence = numbers.get(0);

        log.info("id: {}, sequence: {}", id, sequence);
        return sequence;
    }
}
