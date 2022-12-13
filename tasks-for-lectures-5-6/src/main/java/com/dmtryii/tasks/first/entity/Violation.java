package com.dmtryii.tasks.first.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Violation {
    private ViolationType type;
    private Double amount;
}
