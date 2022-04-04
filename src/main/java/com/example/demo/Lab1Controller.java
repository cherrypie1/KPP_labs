package com.example.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Lab1Controller {
    @GetMapping("/plus_one")
    public int plus_one(@RequestParam(value = "value") int value) {
        return (int)(value+1);
    }
    @GetMapping("/minus_one")
    public int minus_one(@RequestParam(value = "value") int value) {
        return (int)(value-1);
    }
    @GetMapping("/plus_ten")
    public int plus_ten(@RequestParam(value = "value") int value) {
        return (int)(value+10);
    }
    @GetMapping("/minus_ten")
    public int minus_ten(@RequestParam(value = "value") int value) {
        return (int)(value-10);
    }
}
