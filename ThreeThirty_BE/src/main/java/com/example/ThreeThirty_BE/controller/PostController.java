package com.example.ThreeThirty_BE.controller;

import com.example.ThreeThirty_BE.dto.PostCreateDto;
import com.example.ThreeThirty_BE.dto.PostPatchDto;
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

  public PostCreateDto createPost(@RequestHeader("Authorization") String authorizationHeader, @RequestBody PostCreateDto postCreateDto) {
    postService.createPost(authorizationHeader, postCreateDto);

//    return ResponseEntity.status(HttpStatus.CREATED).body("게시물이 성공적으로 생성되었습니다.");
      return postCreateDto;
  }
  // 전체 게시물 조회
  @GetMapping
  public List<Posts> getPost(@RequestHeader("Authorization") String authorizationHeader){
    List<Posts> PostResponseDto = postService.getPost(authorizationHeader);
    return PostResponseDto;
  }

  // 게시물 수정
  @PatchMapping("/{postId}")
  public ResponseEntity<?> updatePost(@RequestHeader("Authorization") String authorizationHeader,@PathVariable Long postId, @RequestBody PostPatchDto postPatchDto) {
    postService.updatePost(authorizationHeader, postId, postPatchDto);
    return ResponseEntity.ok("게시물이 성공적으로 수정되었습니다.");
  }
  // 게시물 데이터 반환
  @GetMapping("/{postId}")
  public List<Posts> getPostForEditing(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long postId) {
    List<Posts> PostResponseDto = postService.getPostForEditing(authorizationHeader, postId);
    return PostResponseDto;
  }
  // 게시물 삭제
  @DeleteMapping("/{postId}")
  public ResponseEntity<String> deletePost(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long postId) {
    postService.deletePost(authorizationHeader, postId);
    return ResponseEntity.ok("게시물이 성공적으로 삭제되었습니다.");
  }

  //좋아요 클릭 시 로직
  @GetMapping("/like/{postId}")
  public ResponseEntity<String> clickLike(@RequestHeader("Authorization") String authorizationHeader,  @PathVariable Long postId){
    String checkLike = postService.clickLike(authorizationHeader, postId);
    return ResponseEntity.ok(checkLike);
  }

  //싫어요 클릭 시 로직
  @GetMapping("/hate/{postId}")
  public ResponseEntity<String> clickHate(@RequestHeader("Authorization") String authorizationHeader,  @PathVariable Long postId){
    String checkHate= postService.clickHate(authorizationHeader, postId);
    return ResponseEntity.ok(checkHate);
  }
}
