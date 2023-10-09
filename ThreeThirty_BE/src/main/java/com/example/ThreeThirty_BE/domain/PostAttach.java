package com.example.ThreeThirty_BE.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostAttach {
  private Long attach_file_id;
  private Long post_id;
  private String attach_file_url;
  private String attach_file_type_code;
}
