package com.example.ThreeThirty_BE.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
  private Long post_id;
  private String post_type;
  private String company_code;
  private String post_content;
  private Long post_writer;            // 작성자(FK) user_id

}
