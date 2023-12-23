package com.xzg.controller;

import com.xzg.domain.test;
import com.xzg.service.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class testController {

    @Autowired
    private testService testService;

    @RequestMapping("/findAlltest")
    public List<test> findAlltest(){
        List<test> alltest = testService.findAlltest();


        return alltest;
    }

}
