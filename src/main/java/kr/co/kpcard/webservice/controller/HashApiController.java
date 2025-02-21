package kr.co.kpcard.webservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.kpcard.webservice.model.HashVO;
import kr.co.kpcard.webservice.service.HashService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class HashApiController {
    private final HashService hashService;

    public HashApiController(HashService hashService) {
        this.hashService = hashService;
    }

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/sqids/encode/{sequence}")
    public ResponseEntity<HashVO> encode(@PathVariable("sequence") Long sequence) {
        ResponseEntity<HashVO> entity = null;
        String id = hashService.encode(sequence);
        HashVO hashVO = new HashVO(sequence, id);

        entity = new ResponseEntity<HashVO>(hashVO, HttpStatus.OK);
        return entity;
    }

    @GetMapping("/sqids/decode/{id}")
    public ResponseEntity<HashVO> decode(@PathVariable("id") String id) {
        ResponseEntity<HashVO> entity = null;
        Long sequence = hashService.decode(id);
        HashVO hashVO = new HashVO(sequence, id);

        entity = new ResponseEntity<HashVO>(hashVO, HttpStatus.OK);
        return entity;
    }

    @GetMapping("/sqids/batch/{sequence}")
    public ResponseEntity<List<HashVO>> batch(@PathVariable("sequence") Long maxSequence) {
        ResponseEntity<List<HashVO>> entity = null;
        List<HashVO> list = hashService.batch(maxSequence);

        entity = new ResponseEntity<List<HashVO>>(list, HttpStatus.OK);
        return entity;
    }

}
