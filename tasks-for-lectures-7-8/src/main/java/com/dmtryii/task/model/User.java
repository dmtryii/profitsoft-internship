package com.dmtryii.task.model;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class User {
    private String username;
    private String email;
    private String password;
}
