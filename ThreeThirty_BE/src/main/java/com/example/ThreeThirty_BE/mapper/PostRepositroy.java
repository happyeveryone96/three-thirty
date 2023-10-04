package com.example.ThreeThirty_BE.mapper;

import com.example.ThreeThirty_BE.domain.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostRepositroy {
  void createPost(Post post);
  List<Post> getPost(Long postId);
  void updatePost(Post post);
  void deletePost(Long postId);
}
