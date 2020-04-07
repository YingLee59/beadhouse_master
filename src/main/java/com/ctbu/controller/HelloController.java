package com.ctbu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 测试hellocontroller
 * @Author LIYING
 */
@RestController
public class HelloController {
    @RequestMapping(value="/hello",method= RequestMethod.GET)
   public String showHello(){
        return "hello";
    }
}
