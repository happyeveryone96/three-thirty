package com.example.ThreeThirty_BE.service;

import com.example.ThreeThirty_BE.domain.Post;
import com.example.ThreeThirty_BE.domain.PostAttach;
import com.example.ThreeThirty_BE.domain.PostHashing;
import com.example.ThreeThirty_BE.dto.PostCreateDto;
import com.example.ThreeThirty_BE.dto.PostCreateDto.Attachment;
import com.example.ThreeThirty_BE.dto.PostResponseDto.Posts;
import com.example.ThreeThirty_BE.dto.PostPatchDto;
import com.example.ThreeThirty_BE.exception.CustomException;
import com.example.ThreeThirty_BE.exception.ErrorCode;
import com.example.ThreeThirty_BE.mapper.PostRepository;
import com.example.ThreeThirty_BE.mapper.UpdatePostRepository;
import com.example.ThreeThirty_BE.security.jwt.util.JwtTokenizer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final JwtTokenizer jwtTokenizer;
  private final UpdatePostRepository updatePostRepository;

  // 게시물 작성
  public void createPost(String authorizationHeader, PostCreateDto postCreateDto) {
    //작성자 확인
    Long userId = jwtTokenizer.getUserIdFromToken(authorizationHeader);

    if (userId == null) {
      throw new CustomException(ErrorCode.ID_PASSWORD_NOT_MATCH);
    }
    //게시물 유형, 회사 코드 PK 받아오기
    String post_type = postRepository.findPostType(postCreateDto.getPost_type_title());
    String company_code = postRepository.findCompanyCode(postCreateDto.getCompany_title());

    //POST 객체 생성
    Post post = Post.builder()
        .post_type(post_type)
        .company_code(company_code)
        .post_content(postCreateDto.getPost_content())
        .post_writer(userId)
        .build();

    //POST 객체 DB에 저장하기 위해 호출
    postRepository.savePost(post);

    //attachments라는 PostAttach 객체들을 담을 리스트를 생성
    //새로운 PostAttach 객체를 생성하고 attachments 리스트에 추가
    List<PostAttach> attachments = new ArrayList<>();
    for (Attachment attachment : postCreateDto.getAttach_file()) {
      PostAttach postAttach = PostAttach.builder()
          .post_id(post.getPost_id())
          .attach_file_url(attachment.getAttach_file_url())
          .attach_file_type_code(attachment.getAttach_file_type())
          .build();
      attachments.add(postAttach);
    }
    //attachments 리스트 DB에 저장하기 위해 호출
    postRepository.saveAttachment(attachments);

    //postHashings라는 PostHashing 객체들을 담을 리스트를 생성
    //새로운 PostHashing 객체를 생성하고 postHashings 리스트에 추가
    List<PostHashing> postHashings = new ArrayList<>();
    for (String hashtag_content : postCreateDto.getHashtag_content()) {
      PostHashing postHashing = PostHashing.builder()
          .post_id(post.getPost_id())
          .hashtag_content(hashtag_content)
          .build();
      postHashings.add(postHashing);
    }

    //postHashings 리스트 DB에 저장하기 위해 호출
    postRepository.saveHashing(postHashings);

  }

  // 전체 게시물 조회
  public List<Posts> getPost(String authorizationHeader) {
    Long userId = jwtTokenizer.getUserIdFromToken(authorizationHeader);

    if (userId == null) {
      throw new CustomException(ErrorCode.ID_PASSWORD_NOT_MATCH);
    }
    List<Posts> postResponseDto = postRepository.findPost();

    return postResponseDto;

  }

  // 게시물 수정
  public void updatePost(String authorizationHeader, Long postId, PostPatchDto postPatchDto) {
    Long userId = jwtTokenizer.getUserIdFromToken(authorizationHeader);

    if (userId == null) {
      throw new CustomException(ErrorCode.ID_PASSWORD_NOT_MATCH);
    }

    if(postRepository.checkWriter(userId, postId)){
      String company_title = postPatchDto.getCompany_title();
      String post_content = postPatchDto.getPost_content();
      List<String> hashtag_content = postPatchDto.getHashtag_content();
      List<PostPatchDto.Attachment> attach_file = postPatchDto.getAttach_file();
      LocalDateTime localDateTime = LocalDateTime.now();

      //POST_LOG
      if(company_title != null || post_content != null){
        updatePostRepository.saveLog(postId, localDateTime);
      }
      if(company_title != null){
        updatePostRepository.updateCompanyCode(postId, company_title, localDateTime);
      }
      if(post_content != null){
        updatePostRepository.updatePostContent(postId, post_content, localDateTime);
      }
      //POST_HASHING
      if(hashtag_content != null){
        updatePostRepository.updateHashing(postId, hashtag_content);
      }

      //POST_ATTACH
    }else{
      // 작성자가 아닌 사용자가 게시물을 수정하는 경우..
    }


  }

  // 게시물 삭제
  public void deletePost(String authorizationHeader, Long postId) {
    Long userId = jwtTokenizer.getUserIdFromToken(authorizationHeader);

    if (userId == null) {
      throw new CustomException(ErrorCode.ID_PASSWORD_NOT_MATCH);
    }
    postRepository.deletePost(postId);
  }

  // 게시물 데이터 반환
  public List<Posts> getPostForEditing(String authorizationHeader, Long postId) {
    //작성자 확인
    Long userId = jwtTokenizer.getUserIdFromToken(authorizationHeader);

    if (userId == null) {
      throw new CustomException(ErrorCode.ID_PASSWORD_NOT_MATCH);
    }
    List<Posts> postResponseDto = postRepository.findByPostId(postId);

    return postResponseDto;

  }

//  public GetPostResponseDto getPost(Long postId) {
//    List<Post> postList = postRepository.getPost(postId);
//
//    List<GetPostResponseDto.GetPost> postPairs = new ArrayList<>();
//    for (Post post : postList) {
//       GetPostResponseDto.GetPost getPost = new GetPostResponseDto.GetPost(post.getContent(), post.getCreateDate(), post.getFileUrl(), post.getWriter());
//      postPairs.add(getPost);
//    }
//
//    return GetPostResponseDto.builder()
//        .getPosts(postPairs)
//        .build();
//  }
//


}
