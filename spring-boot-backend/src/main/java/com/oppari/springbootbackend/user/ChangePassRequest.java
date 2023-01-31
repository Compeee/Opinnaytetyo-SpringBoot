package com.oppari.springbootbackend.user;

import lombok.*;

@Data
@EqualsAndHashCode
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ChangePassRequest {
    private final Long userId;
    private final String password;
}
