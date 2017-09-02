package osedevops.group.springbootmongo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import osedevops.group.springbootmongo.model.Car;
import osedevops.group.springbootmongo.repository.CarMongoRepository;
import osedevops.group.springbootmongo.repository.CarSearchRepository;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import java.util.Collections;
import java.util.List;


@RestController
public class HelloController {
    
    
    @Autowired
    private RestTemplate restTemplate;
    
    @RequestMapping("/simple")
    public String home() {
        return "Hello Demo";
    }
    
    @RequestMapping("/chaining")
    public String chaining() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://springboot-mongo-gluster-demo/simple", String.class);
        return "Chaining + " + response.getBody();
    }
    
    
}