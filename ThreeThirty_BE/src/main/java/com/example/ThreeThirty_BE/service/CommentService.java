package com.example.ThreeThirty_BE.service;

import com.example.ThreeThirty_BE.domain.Comment;
import com.example.ThreeThirty_BE.dto.Comment.CommentCreateDto;
import com.example.ThreeThirty_BE.dto.Comment.CommentDto;
import com.example.ThreeThirty_BE.exception.CustomException;
import com.example.ThreeThirty_BE.exception.ErrorCode;
import com.example.ThreeThirty_BE.mapper.CommentRepository;
import com.example.ThreeThirty_BE.security.jwt.util.JwtTokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // final 필드 녀석들을 대상으로 한 생성자가 자동 생성
public class CommentService {

    private final CommentRepository commentRepository;
    private final JwtTokenizer jwtTokenizer;
    @Transactional
    public void createComment(String authorizationHeader, CommentCreateDto commentCreateDto){

        // 헤더의 토큰으로부터 유저 아이디 가져오기

        long user_id = checkToken(authorizationHeader);

        Comment comment = Comment.builder()
                .user_id(user_id)
                .post_id(commentCreateDto.getPost_id())
                .comment_content(commentCreateDto.getComment_content())
                .build();

        commentRepository.saveComment(comment);
    }


    public List<Comment> findComments(String authorizationHeader, long post_id){

        long user_id = checkToken(authorizationHeader);
        List<Comment> comment = commentRepository.findComments(post_id);

        return comment;
    }

    public List<Comment> findUserComment(String authorizationHeader){

        long user_id = checkToken(authorizationHeader);
        List<Comment> comment = commentRepository.findUserComment(user_id);
        return comment;
    }

    public void updateComment(String authorizationHeader, Long comment_id, CommentDto commentDto){

        long userId = checkToken(authorizationHeader);

        Comment comment = Comment.builder()
                .user_id(userId)
                .post_id(commentDto.getPost_id())
                .comment_content(commentDto.getComment_content())
                .build();

        commentRepository.updateComment(commentDto.getComment_content(), comment_id);
    }

    public void deleteComment(String authorizationHeader, Long comment_id) {
        Long user_id = checkToken(authorizationHeader);
        //해당 댓글을 작성한 작성자가 맞아? 그럼 delete
        if(commentRepository.checkWriter(user_id, comment_id)){
            commentRepository.deleteComment(comment_id);
        }

    }


private long checkToken(String authorizationHeader){

    Long user_id = jwtTokenizer.getUserIdFromToken(authorizationHeader);
    if (user_id == null) {
        throw new CustomException(ErrorCode.ID_PASSWORD_NOT_MATCH);
    }
  return user_id;
}

}
