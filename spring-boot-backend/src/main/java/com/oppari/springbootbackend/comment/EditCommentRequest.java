package com.oppari.springbootbackend.comment;

import lombok.*;

@Data
@EqualsAndHashCode
@Getter
@Setter
@ToString
@AllArgsConstructor
public class EditCommentRequest {
    private final Long userId;
    private final String content;
}
