package kr.co.kpcard.webservice.controller;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ShortenUrlController {
    private static String REDIRECT_URL = "http://localhost:8080/sqids/decode/";

    // return string
    @GetMapping("/bitly/string/{id}")
    public String returnString(@PathVariable("id") String id) {
        String projectUrl = REDIRECT_URL + id;
        log.info("redirect url : {}", projectUrl);

        return "redirect:" + projectUrl;
    }

    // return ModelAndView
    @GetMapping("/bitly/modelview/{id}")
    public ModelAndView returnModelAndView(@PathVariable("id") String id) {
        String projectUrl = REDIRECT_URL + id;
        log.info("redirect url : {}", projectUrl);

        return new ModelAndView("redirect:" + projectUrl);
    }

    // httpServletResponse.sendRedirect
    @GetMapping("/bitly/sendredirect/{id}")
    public void sendRedirect(HttpServletResponse httpServletResponse, @PathVariable("id") String id)
            throws IOException {
        String projectUrl = REDIRECT_URL + id;
        log.info("redirect url : {}", projectUrl);

        httpServletResponse.sendRedirect(projectUrl);
    }

    // return redirectView
    @RequestMapping("/bitly/redirectview/{id}")
    public RedirectView returnRedirectView(@PathVariable("id") String id) {
        String projectUrl = REDIRECT_URL + id;
        log.info("redirect url : {}", projectUrl);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(projectUrl);
        return redirectView;
    }

    // httpHeaders
    @RequestMapping("/bitly/httpheaders/{id}")
    public ResponseEntity<Object> httpHeaders(@PathVariable("id") String id) throws URISyntaxException {
        String projectUrl = REDIRECT_URL + id;
        log.info("redirect url : {}", projectUrl);

        URI redirectUri = new URI(projectUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }
}
