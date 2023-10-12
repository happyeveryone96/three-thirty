package com.example.ThreeThirty_BE.dto.Comment;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long comment_id;
    private String comment_content;
    private long user_id;
    private long post_id;
}
