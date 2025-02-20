package kr.co.kpcard.webservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> encode(@PathVariable("sequence") Long sequence) {
        ResponseEntity<String> entity = null;
        String id = hashService.encode(sequence);

        entity = new ResponseEntity<String>(id, HttpStatus.OK);
        return entity;
    }

    @GetMapping("/sqids/decode/{id}")
    public ResponseEntity<Long> decode(@PathVariable("id") String id) {
        ResponseEntity<Long> entity = null;
        Long sequence = hashService.decode(id);

        entity = new ResponseEntity<Long>(sequence, HttpStatus.OK);
        return entity;
    }

    @GetMapping("/sqids/batch/{sequence}")
    public ResponseEntity<Long> batch(@PathVariable("sequence") Long maxSequence) {
        ResponseEntity<Long> entity = null;
        Long sequence = hashService.batch(maxSequence);

        entity = new ResponseEntity<Long>(sequence, HttpStatus.OK);
        return entity;
    }

}
