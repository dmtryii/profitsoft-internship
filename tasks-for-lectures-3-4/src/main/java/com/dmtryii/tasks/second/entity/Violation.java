package com.dmtryii.tasks.second.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Violation {
    private ViolationType type;
    private Double amount;
}
