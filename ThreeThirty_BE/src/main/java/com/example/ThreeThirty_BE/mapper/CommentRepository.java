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
    List<Comment> findComments(long post_id);

    void updateComment(Long comment_id);
    void deleteComment(Long comment_id);
}
