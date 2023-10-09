package com.example.ThreeThirty_BE.mapper;

import com.example.ThreeThirty_BE.domain.Post;
import com.example.ThreeThirty_BE.domain.PostAttach;
import com.example.ThreeThirty_BE.domain.PostHashing;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostRepository {
  void createPost(Post post);
  List<Post> getPost(Long postId);
  void updatePost(Post post);
  void deletePost(Long postId);

  String findPostType(String post_type_title);
  String findCompanyCode(String company_title);

  void savePost(Post post);

  void saveAttachment(List<PostAttach> attachments);

  void saveHashing(List<PostHashing> postHashings);
}
