package com.example.salary;

import java.math.BigDecimal;
import java.util.List;

public class AverageSalary {

    public static BigDecimal count(List<Integer> salaryList) {
        return BigDecimal.valueOf(salaryList.stream()
                .mapToInt(i -> i)
                .average()
                .orElse(0.0));
    }
}