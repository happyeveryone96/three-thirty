package com.example.ThreeThirty_BE.dto.Comment;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long comment_id;
    private String comment_content;
    private Long user_id;
    private Long post_id;
}
