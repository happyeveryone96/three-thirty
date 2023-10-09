package com.example.ThreeThirty_BE.controller;

import com.example.ThreeThirty_BE.domain.Post;
import com.example.ThreeThirty_BE.dto.PostCreateDto;
import com.example.ThreeThirty_BE.dto.PostResponseDto;
import com.example.ThreeThirty_BE.dto.PostResponseDto.Posts;
import com.example.ThreeThirty_BE.service.PostService;
import java.util.List;
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
  // 게시물 작성
  @PostMapping("/create")
  public ResponseEntity<String> createPost(@RequestHeader("Authorization") String authorizationHeader, @RequestBody PostCreateDto postCreateDto) {
    postService.createPost(authorizationHeader, postCreateDto);

    return ResponseEntity.status(HttpStatus.CREATED).body("게시물이 성공적으로 생성되었습니다.");
  }
  // 전체 게시물 조회
  @GetMapping
  public List<Posts> getPost(@RequestHeader("Authorization") String authorizationHeader){
    List<Posts> PostResponseDto = postService.getPost(authorizationHeader);
    return PostResponseDto;
  }

  @PutMapping
  public ResponseEntity<String> updatePost(@RequestHeader("Authorization") String authorizationHeader, @RequestBody PostCreateDto postCreateDto) {
    postService.updatePost(authorizationHeader, postCreateDto);
    return ResponseEntity.ok("게시물이 성공적으로 수정되었습니다.");
  }
//  @GetMapping("/{postId}")
//  public ResponseEntity<GetPostResponseDto> getPost(@PathVariable Long postId) {
//    GetPostResponseDto getPostResponseDto = postService.getPost(postId);
//    return new ResponseEntity<>(getPostResponseDto, HttpStatus.FOUND);
//  }
//
//  @DeleteMapping("/{postId}")
//  public ResponseEntity<String> deletePost(@PathVariable Long postId) {
//    postService.deletePost(postId);
//    return ResponseEntity.ok("게시물이 성공적으로 삭제되었습니다.");
//  }
}
