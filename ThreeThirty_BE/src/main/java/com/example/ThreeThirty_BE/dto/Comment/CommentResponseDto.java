package com.example.ThreeThirty_BE.dto.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {

    private long user_id;
    private long post_id;
    private long parent_num;


}
