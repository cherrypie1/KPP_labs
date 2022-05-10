package com.example.demo.controller;

import com.example.demo.counter.CounterService;
import com.example.demo.entity.ResultDTO;
import com.example.demo.entity.ResultValue;
import com.example.demo.repository.InputParams;
import com.example.demo.service.SimpleCalculationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class SimpleCalculationsController {

    @Autowired
    SimpleCalculationsService service;

    @GetMapping("/calculate")
    public ResultValue calculateByOperation(@RequestParam(value = "operation") String operation,
                                            @RequestParam(value = "value") int value) {
        return service.calculate(new InputParams(operation, value));
    }

    @GetMapping("/cache")
    public HashMap<InputParams, ResultValue> getCache() {
        return service.getNumberInMemoryCache();
    }

    @GetMapping("/counter")
    public ResponseEntity<Integer> getCount(){
        return new ResponseEntity<>(CounterService.getCounter(), HttpStatus.OK);
    }

    @PostMapping("/calculate")
    public ResponseEntity<Object> calculateBulkOperation(@Valid @RequestBody List<InputParams> bodyList) {
        if (bodyList.isEmpty()) {
            return ResponseEntity.ok(new ResultDTO());
        }
        List<ResultValue> resultList = new LinkedList<>();
        bodyList.forEach((currentElem) -> {
            resultList.add(service.calculate(currentElem));
        });
        ResultDTO dto = new ResultDTO();
        dto.setList(resultList);

        OptionalDouble averageResult = resultList.stream().map(ResultValue::getValue).mapToInt(Integer::intValue).average();
        if (averageResult.isPresent()) {
            dto.setAverageResult(averageResult.getAsDouble());
        }

        OptionalInt maxResult = resultList.stream().map(ResultValue::getValue).mapToInt(Integer::intValue).max();
        if (maxResult.isPresent()) {
            dto.setMaxResult(maxResult.getAsInt());
        }

        OptionalInt minResult = resultList.stream().map(ResultValue::getValue).mapToInt(Integer::intValue).min();
        if (minResult.isPresent()) {
            dto.setMinResult(minResult.getAsInt());
        }

        return ResponseEntity.ok(dto);
    }
}
