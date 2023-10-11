package com.example.ThreeThirty_BE.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeRepository {
  void saveLike(Long user_id, Long post_id);

  boolean findToLike(Long user_id, Long post_id);

  void deleteLike(Long user_id, Long post_id);

  boolean findToHate(Long user_id, Long post_id);

  void deleteHate(Long user_id, Long post_id);

  void saveHate(Long user_id, Long post_id);

  void downLikeCount(Long post_id);

  void upLikeCount(Long post_id);

  void downHateCount(Long post_id);

  void upHateCount(Long post_id);
}
