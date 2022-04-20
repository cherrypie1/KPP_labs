package com.example.demo.repository;

import com.example.demo.entity.ResultValue;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

@Repository
public class NumberInMemoryCache {

    private final HashMap<InputParams, ResultValue> hashMap = new HashMap<>();

    public boolean containsInputParams(InputParams key) {
        return hashMap.containsKey(key);
    }

    public void addToMap(InputParams key, ResultValue result) {
        hashMap.put(key, result);
    }

    public ResultValue getParameters(@NotNull InputParams key) {
        return hashMap.get(key);
    }

    public HashMap<InputParams, ResultValue> getCache() {
        return hashMap;
    }
}
