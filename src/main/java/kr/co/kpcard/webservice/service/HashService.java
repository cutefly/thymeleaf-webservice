package kr.co.kpcard.webservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.sqids.Sqids;

import kr.co.kpcard.webservice.model.HashVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HashService {

    @Value("${custom.sqids.alphabet}")
    private String alphabet;

    @Value("${custom.sqids.min-length}")
    private int minLength;

    private static Sqids sqids;

    private Sqids getInstance() {
        if (sqids == null) {
            log.info("alphabet: {}, minLength: {}", alphabet, minLength);
            sqids = Sqids.builder()
                    .alphabet(alphabet)
                    .minLength(minLength)
                    .build();
        }
        return sqids;
    }

    public String encode(Long sequence) {
        String id = getInstance().encode(Arrays.asList(sequence));

        log.info("sequence: {}, id: {}", sequence, id);
        return id;
    }

    public Long decode(String id) {
        List<Long> numbers = getInstance().decode(id);
        Long sequence = numbers.get(0);

        log.info("id: {}, sequence: {}", id, sequence);
        return sequence;
    }

    public List<HashVO> batch(Long maxSequence) {
        Sqids sqids = getInstance();
        List<HashVO> list = new ArrayList<>();
        String id;

        for (Long i = 1L; i <= maxSequence; i++) {
            id = sqids.encode(Arrays.asList(i));
            list.add(new HashVO(i, id));
            log.info("sequence: {}, id: {}", i, id);
        }
        log.info("finish generated count: {}", list.size());
        return list;
    }
}
