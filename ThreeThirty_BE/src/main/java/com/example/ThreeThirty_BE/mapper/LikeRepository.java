package com.example.ThreeThirty_BE.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeRepository {
  void saveLike(Long userId, Long postId);

  boolean findToLike(Long userId, Long postId);

  void deleteLike(Long userId, Long postId);

  List<Long> findLikePost(Long userId);

  List<Long> findHatePost(Long userId);

  boolean findToHate(Long userId, Long postId);

  void deleteHate(Long userId, Long postId);

  void saveHate(Long userId, Long postId);
}
