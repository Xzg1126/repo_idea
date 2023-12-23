package com.xzg.service.impl;

import com.xzg.dao.testMapper;
import com.xzg.domain.test;
import com.xzg.service.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class testServiceImpl implements testService {
    @Autowired
    private testMapper testMapper;

    @Override
    public List<test> findAlltest() {
        List<test> alltest = testMapper.findAlltest();
        return alltest;
    }
}
