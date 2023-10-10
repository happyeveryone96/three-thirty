package com.example.ThreeThirty_BE.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
  List<Posts> posts;

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Posts {

    private Long post_id;
    private String post_writer;
    private String post_content;
    private LocalDateTime update_date;
    private int like_count;
    private int hate_count;
    private int comment_count;
    private String company_title;
    private List<String> hashtag_content;
    private List<String> attach_file_url;
  }
}
