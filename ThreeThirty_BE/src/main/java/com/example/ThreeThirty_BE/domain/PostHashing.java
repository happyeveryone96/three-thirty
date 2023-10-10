package com.example.ThreeThirty_BE.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostHashing {
  private Long hashtag_id;
  private Long post_id;
  private String hashtag_content;
}
