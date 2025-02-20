package kr.co.kpcard.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.co.kpcard.webservice.service.HashService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HashController {

    HashService hashService;

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/sqids/encode/{sequence}")
    public String encode(@PathVariable("sequence") Long sequence, Model model) {
        String id = hashService.encode(sequence);
        model.addAttribute("action", "encode");
        model.addAttribute("sequence", sequence);
        model.addAttribute("id", id);

        return "sqids";
    }

    @GetMapping("/sqids/decode/{id}")
    public String decode(@PathVariable("id") String id, Model model) {
        Long sequence = hashService.decode(id);
        model.addAttribute("action", "decode");
        model.addAttribute("id", id);
        model.addAttribute("sequence", sequence);

        return "sqids";
    }
}
