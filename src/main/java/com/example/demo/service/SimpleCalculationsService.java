package com.example.demo.service;

import com.example.demo.exception.NoNumberEnteredException;
import com.example.demo.entity.Operations;
import com.example.demo.entity.ResultValue;
import com.example.demo.repository.InputParams;
import com.example.demo.repository.NumberInMemoryCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Locale;

@Service
public class SimpleCalculationsService {

    @Autowired
    private NumberInMemoryCache numberInMemoryCache;


    public ResultValue calculate(@NotNull InputParams inputParams){
        try {
            if(numberInMemoryCache.isContain(inputParams))
                return numberInMemoryCache.getParameters(inputParams);
            Operations op = Operations.valueOf(inputParams.getOperation().toUpperCase(Locale.ROOT));
            numberInMemoryCache.addToMap(inputParams, new ResultValue(op.action(inputParams.getValue())));
            return new ResultValue(op.action(inputParams.getValue()));
        } catch (IllegalArgumentException ex){
            throw new NoNumberEnteredException("Operation is incorrect!");
        }
    }
}
