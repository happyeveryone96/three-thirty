package com.example.ThreeThirty_BE.service;

import com.example.ThreeThirty_BE.domain.Comment;
import com.example.ThreeThirty_BE.dto.Comment.CommentCreateDto;
import com.example.ThreeThirty_BE.exception.CustomException;
import com.example.ThreeThirty_BE.exception.ErrorCode;
import com.example.ThreeThirty_BE.mapper.CommentRepository;
import com.example.ThreeThirty_BE.security.jwt.util.JwtTokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // final 필드 녀석들을 대상으로 한 생성자가 자동 생성
public class CommentService {

    private final CommentRepository commentRepository;
    private final JwtTokenizer jwtTokenizer;
    public void createComment(String authorizationHeader, CommentCreateDto commentCreateDto){

        // 헤더의 토큰으로부터 유저 아이디 가져오기
        Long userId = jwtTokenizer.getUserIdFromToken(authorizationHeader);
        if (userId == null) {
            throw new CustomException(ErrorCode.ID_PASSWORD_NOT_MATCH);
        }

        Comment comment = Comment.builder()
                .user_id(commentCreateDto.getUser_id())
                .post_id(commentCreateDto.getPost_id())
                .comment_content(commentCreateDto.getComment_content())
                .parent_num(String.valueOf(commentCreateDto.getParent_num()))
                        .build();

        commentRepository.saveComment(comment);
    }


    public List<Comment> findComments(String authorizationHeader, long post_id){

        Long userId = jwtTokenizer.getUserIdFromToken(authorizationHeader);
        if (userId == null) {
            throw new CustomException(ErrorCode.ID_PASSWORD_NOT_MATCH);
        }

        return commentRepository.findComments(post_id);
    }




}
