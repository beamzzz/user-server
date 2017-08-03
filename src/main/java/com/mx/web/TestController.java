package com.mx.web;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/get")
    public String testGet(){
        return "get success";
    }

    @PostMapping("/post")
    public String testPost(){
        return "post success";
    }

    @DeleteMapping("/delete")
    public String testDelete(){
        return "delete success";
    }
}
