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
    List<Comment> findComments(Long post_id);
    List<Comment> findUserComment(Long user_id);

    void updateComment(String comment_content, Long comment_id);
    void deleteComment(Long commentId);
    boolean checkWriter(Long user_id, Long comment_id);

    void upCommentCount(Long post_id);
    void downCommentCount(Long post_id);
}
