package com.example.ThreeThirty_BE.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPostResponseDto {
  private List<GetPost> getPosts;

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class GetPost {

    private String content;           // 내용
    private LocalDateTime createDate; // 작성 시간
    private String file;              // 첨부파일
    private String writer;            // 작성자

  }
}
