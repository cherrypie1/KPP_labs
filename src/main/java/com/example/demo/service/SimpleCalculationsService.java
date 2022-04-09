package com.example.demo.service;

import com.example.demo.Exception.NoNumberEnteredException;
import com.example.demo.entity.Operations;
import com.example.demo.entity.ResultValue;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class SimpleCalculationsService {

    public ResultValue calculate(String operation, int value){
        if(operation.toUpperCase(Locale.ROOT)!="PLUS_ONE" && operation.toUpperCase(Locale.ROOT)!="MINUS_ONE" &&
                operation.toUpperCase(Locale.ROOT)!="PLUS_TEN" && operation.toUpperCase(Locale.ROOT)!="MINUS_TEN")
            throw new NoNumberEnteredException();
        Operations op = Operations.valueOf(operation.toUpperCase(Locale.ROOT));
        return new ResultValue(op.action(value));
    }
}
