package com.example.ThreeThirty_BE.controller;


import com.example.ThreeThirty_BE.domain.Comment;
import com.example.ThreeThirty_BE.dto.Comment.CommentCreateDto;
import com.example.ThreeThirty_BE.dto.Comment.CommentDto;
import com.example.ThreeThirty_BE.dto.PostPatchDto;
import com.example.ThreeThirty_BE.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    //댓글 달기
    @PostMapping("/post/{postId}/comments/create")
    // responseEntity는 HTTP응답을 생성하는 녀석으로 상태콛, 응답데이터, 헤더, 등을 반환할 수 있고 또, JSON,HTML,텍스트 또는 다른 커스텀 미디어 타입으로 응답을 할 수 있게해준다.
    // 아래의 경우 저장이 잘 일어나면 "댓글이 달려브렀어~"를 리턴하기에 ResponseEntity타입이 String 이다.
    public ResponseEntity<String> createComment(@RequestHeader("Authorization") String authorizationHeader,@PathVariable Long postId, @RequestBody CommentCreateDto commentCreateDto){
        commentService.createComment(authorizationHeader, commentCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("댓글이 달려브렀어~");
    }

    //해당 게시물에 대한 댓글들 보여쥐
    @GetMapping("/post/{postId}/comments")
    public List<Comment> findComments(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long postId){
        List<Comment> commentLists = commentService.findComments(authorizationHeader, postId);
            return commentLists;
    }

    // 해당 유저가 달았던 댓글들만 보여주기
    @GetMapping("/user/comment")
    public List<Comment> findUserComments(@RequestHeader("Authorization") String authorizationHeader){
        List<Comment> commentLists = commentService.findUserComment(authorizationHeader);
        return commentLists;
    }

    // 댓글 수정
    @PatchMapping("/post/{post_id}/comments/{comment_id}")
    public ResponseEntity<?> updatePost(@RequestHeader("Authorization") String authorizationHeader,@PathVariable Long comment_id, @RequestBody CommentDto commentDto) {
        String content= commentDto.getComment_content();
        commentService.updateComment(authorizationHeader, comment_id, commentDto);
        return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
    }
    @DeleteMapping("/post/{post_id}/comments/{comment_id}")
    public ResponseEntity<String> deletePost(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long comment_id) {
        commentService.deleteComment(authorizationHeader, comment_id);
        return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
    }


}
