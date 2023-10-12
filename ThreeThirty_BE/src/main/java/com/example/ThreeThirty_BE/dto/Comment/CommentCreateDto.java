package com.example.ThreeThirty_BE.dto.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateDto {
    private String comment_content;
    private long user_id;
    private long post_id;
}
