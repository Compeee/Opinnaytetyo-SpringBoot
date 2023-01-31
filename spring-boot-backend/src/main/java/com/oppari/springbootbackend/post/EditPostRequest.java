package com.oppari.springbootbackend.post;

import lombok.*;

@Data
@EqualsAndHashCode
@Getter
@Setter
@ToString
@AllArgsConstructor
public class EditPostRequest {
    private final Long userId;
    private final String content;
}
