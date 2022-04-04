package com.example.demo.service;

import com.example.demo.entity.Operations;
import com.example.demo.entity.ResultValue;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class SimpleCalculationsService {

    public ResultValue calculate(String operation, int value){
        Operations op = Operations.valueOf(operation.toUpperCase(Locale.ROOT));
        return new ResultValue(op.action(value));
    }
}
