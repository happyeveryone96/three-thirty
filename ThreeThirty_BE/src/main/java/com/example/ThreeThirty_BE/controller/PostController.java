package com.example.ThreeThirty_BE.controller;

import com.example.ThreeThirty_BE.dto.PostDto;
import com.example.ThreeThirty_BE.dto.PostDto.Attachment;
import com.example.ThreeThirty_BE.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@Slf4j
public class PostController {
  private final PostService postService;
  @PostMapping
  public ResponseEntity<String> createPost(@RequestHeader("Authorization") String authorizationHeader, @RequestBody PostDto postDto) {
    postService.createPost(authorizationHeader, postDto);

    return ResponseEntity.status(HttpStatus.CREATED).body("게시물이 성공적으로 생성되었습니다.");
  }

//  @GetMapping("/{postId}")
//  public ResponseEntity<GetPostResponseDto> getPost(@PathVariable Long postId) {
//    GetPostResponseDto getPostResponseDto = postService.getPost(postId);
//    return new ResponseEntity<>(getPostResponseDto, HttpStatus.FOUND);
//  }
//
//  @PutMapping
//  public ResponseEntity<String> updatePost(@RequestBody PostDto postDto) {
//    postService.updatePost(postDto);
//    return ResponseEntity.ok("게시물이 성공적으로 수정되었습니다.");
//  }
//
//  @DeleteMapping("/{postId}")
//  public ResponseEntity<String> deletePost(@PathVariable Long postId) {
//    postService.deletePost(postId);
//    return ResponseEntity.ok("게시물이 성공적으로 삭제되었습니다.");
//  }
}
