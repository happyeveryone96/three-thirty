package com.example.ThreeThirty_BE.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
   private long comment_id;
   private long post_id;
   private long user_id;
   private String parent_num;
   private String comment_content;
   private long step;
}
