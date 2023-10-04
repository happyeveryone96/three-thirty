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
  private Long postId;
  private String content;           // 내용
  private LocalDateTime createDate; // 작성 시간
  private String fileType;              // 첨부파일 유형 : 동영상? 이미지?
  private String fileUrl;
  private String writer;            // 작성자(FK)
  private String category;          // 게시글 유형
  private String company;
}
