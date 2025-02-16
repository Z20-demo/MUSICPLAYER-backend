package com.nju.song.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/songs")
public class songController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String test() {
        String url = "http://user/api/users/test";
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }
}
