package com.example.ThreeThirty_BE.mapper;

import com.example.ThreeThirty_BE.domain.PostAttach;
import com.example.ThreeThirty_BE.dto.PostPatchDto.Attachment;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UpdatePostRepository {


  void updateCompanyCode(Long post_id, String company_title, LocalDateTime update_date);

  void updatePostContent(Long post_id, String post_content, LocalDateTime update_date);

  void updateHashing(Long post_id, List<String> hashtag_content);

  void updateAttachment(List<PostAttach> attachments);

  void saveLog(Long post_id, LocalDateTime update_date);

  void deleteHashTag(List<Long> hashtag_id);

  List<Long> findByHashtagId(Long post_id, List<String> hashtag_content);

  void insertHashTag(Long post_id, List<String> hashtag_content);

  List<Long> findByAttachId(Long post_id, List<Attachment> attach_file);

  void deleteAttachFile(List<Long> attach_file_id);

  void insertDeleteAttachFile(Long post_id, List<Attachment> attach_file);
}
