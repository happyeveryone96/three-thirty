package com.example.ThreeThirty_BE.domain;

import lombok.*;

import java.math.BigInteger;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
   private long comment_id;
   private long post_id;
   private long user_id;
   private String comment_content;
}
