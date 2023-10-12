package com.example.ThreeThirty_BE.mapper;

import com.example.ThreeThirty_BE.domain.Comment;
import com.example.ThreeThirty_BE.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentRepository {

    void saveComment(Comment comment);

    //show all comments
    List<Comment> findComments(Long comment_id);
    Comment findComment(Long comment_id);

    void updateComment(String comment_content, Long comment_id);
    void deleteComment(Long comment_id);
    boolean checkWriter(Long user_id, Long comment_id);
}
