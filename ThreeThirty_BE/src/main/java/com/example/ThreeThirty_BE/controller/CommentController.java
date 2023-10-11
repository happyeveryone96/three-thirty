package com.example.ThreeThirty_BE.controller;


import com.example.ThreeThirty_BE.domain.Comment;
import com.example.ThreeThirty_BE.dto.Comment.CommentCreateDto;
import com.example.ThreeThirty_BE.dto.Comment.CommentResponseDto;
import com.example.ThreeThirty_BE.dto.PostCreateDto;
import com.example.ThreeThirty_BE.dto.PostResponseDto;
import com.example.ThreeThirty_BE.security.jwt.util.JwtTokenizer;
import com.example.ThreeThirty_BE.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}/comment/create")
    // responseEntity는 HTTP응답을 생성하는 녀석으로 상태콛, 응답데이터, 헤더, 등을 반환할 수 있고 또, JSON,HTML,텍스트 또는 다른 커스텀 미디어 타입으로 응답을 할 수 있게해준다.
    // 아래의 경우 저장이 잘 일어나면 "댓글이 달려브렀어~"를 리턴하기에 ResponseEntity타입이 String 이다.
    public ResponseEntity<String> createComment(@RequestHeader("Authorization") String authorizationHeader,@PathVariable Long postId, @RequestBody CommentCreateDto commentCreateDto){
        commentService.createComment(authorizationHeader, commentCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("댓글이 달려브렀어~");
    }

    @GetMapping("/{postId}/comment")
    public List<Comment> findComments(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long postId){
        List<Comment> commentLists = commentService.findComments(authorizationHeader, postId);
        return commentLists;
    }

}
