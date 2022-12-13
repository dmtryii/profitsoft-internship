package com.dmtryii.tasks.second.models;

import com.dmtryii.tasks.second.annotations.Property;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@ToString
@EqualsAndHashCode
public class Model {
    @Property(name = "stringProperty")
    private String stringProperty;
    @Property(name = "numberProperty")
    private Integer integerProperty;
    @Property(name = "timeProperty", format="dd.MM.yyyy HH:mm")
    private LocalDateTime timeProperty;
}
