package com.example.ThreeThirty_BE.mapper;

import com.example.ThreeThirty_BE.domain.Post;
import com.example.ThreeThirty_BE.domain.PostAttach;
import com.example.ThreeThirty_BE.domain.PostHashing;
import com.example.ThreeThirty_BE.dto.PostResponseDto.Posts;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostRepository {
  // TB_POST
  void savePost(Post post);
  List<Posts> findPost();

  void deletePost(Long postId);

  // TB_POST_TYPE
  String findPostType(String post_type_title);

  //TB_COMPANY_TYPE
  String findCompanyCode(String company_title);

  //TB_POST_ATTACH
  void saveAttachment(List<PostAttach> attachments);

  //TB_POST_HASHING
  void saveHashing(List<PostHashing> postHashings);

  List<Posts> findByPostId(Long postId);

  boolean checkWriter(Long user_id, Long postId);
}
