package com.example.salary;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AverageSalary {

    public BigDecimal count(List<Integer> salaryList) {
        return BigDecimal.valueOf(salaryList.stream()
                .mapToInt(i -> i)
                .average()
                .orElse(0.0));
    }
}