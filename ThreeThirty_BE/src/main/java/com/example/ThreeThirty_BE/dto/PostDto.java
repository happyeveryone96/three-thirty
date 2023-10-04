package com.example.ThreeThirty_BE.dto;

import lombok.Getter;

@Getter
public class PostDto {

  private String content;           // 내용
  private String file;              // 첨부파일
  private String fileType;          // 첨부파일 유형
  private String category;          // 게시글 유형
  private String company;           // 기업 종목

}
