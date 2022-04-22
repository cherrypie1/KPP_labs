package com.example.demo.service;

import com.example.demo.counter.CounterService;
import com.example.demo.entity.Operations;
import com.example.demo.entity.ResultValue;
import com.example.demo.exception.NoNumberEnteredException;
import com.example.demo.repository.InputParams;
import com.example.demo.repository.NumberInMemoryCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Locale;

@Service
public class SimpleCalculationsService {

    @Autowired
    private NumberInMemoryCache numberInMemoryCache;

    public ResultValue calculate(@NotNull InputParams inputParams) {
        try {
            new Thread(CounterService::increment).start();
            if (numberInMemoryCache.containsInputParams(inputParams)) {
                return numberInMemoryCache.getParameters(inputParams);
            } else {
                Operations op = Operations.valueOf(inputParams.getOperation().toUpperCase(Locale.ROOT));
                ResultValue resultValue = new ResultValue(op.action(inputParams.getValue()));
                numberInMemoryCache.addToMap(inputParams, resultValue);
                return resultValue;
            }
        } catch (IllegalArgumentException ex) {
            throw new NoNumberEnteredException("Operation is incorrect!");
        }
    }

    public HashMap<InputParams, ResultValue> getNumberInMemoryCache() {
        return numberInMemoryCache.getCache();
    }


}
