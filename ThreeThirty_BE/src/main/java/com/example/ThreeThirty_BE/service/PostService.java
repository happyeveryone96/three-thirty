package com.example.ThreeThirty_BE.service;

import com.example.ThreeThirty_BE.domain.Post;
import com.example.ThreeThirty_BE.domain.PostAttach;
import com.example.ThreeThirty_BE.domain.PostHashing;
import com.example.ThreeThirty_BE.dto.PostDto;
import com.example.ThreeThirty_BE.dto.PostDto.Attachment;
import com.example.ThreeThirty_BE.exception.CustomException;
import com.example.ThreeThirty_BE.exception.ErrorCode;
import com.example.ThreeThirty_BE.mapper.PostRepository;
import com.example.ThreeThirty_BE.security.jwt.util.JwtTokenizer;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final JwtTokenizer jwtTokenizer;

  public void createPost(String authorizationHeader, PostDto postDto) {
    //작성자 확인
    Long userId = jwtTokenizer.getUserIdFromToken(authorizationHeader);

    if (userId == null) {
      throw new CustomException(ErrorCode.ID_PASSWORD_NOT_MATCH);
    }
    //게시물 유형, 회사 코드 PK 받아오기
    String post_type = postRepository.findPostType(postDto.getPost_type_title());
    String company_code = postRepository.findCompanyCode(postDto.getCompany_title());

    //POST 객체 생성
    Post post = Post.builder()
        .post_type(post_type)
        .company_code(company_code)
        .post_content(postDto.getPost_content())
        .post_writer(userId)
        .build();

    //POST 객체 DB에 저장하기 위해 호출
    postRepository.savePost(post);

    //attachments라는 PostAttach 객체들을 담을 리스트를 생성
    //새로운 PostAttach 객체를 생성하고 attachments 리스트에 추가
    List<PostAttach> attachments = new ArrayList<>();
    for (Attachment attachment : postDto.getAttach_file()) {
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
    for (String hashtag_content : postDto.getHashtag_content()) {
      PostHashing postHashing = PostHashing.builder()
          .post_id(post.getPost_id())
          .hashtag_content(hashtag_content)
          .build();
      postHashings.add(postHashing);
    }

    //postHashings 리스트 DB에 저장하기 위해 호출
    postRepository.saveHashing(postHashings);


    LocalDateTime localDateTime = LocalDateTime.now();
  }

//      // PostDto에서 필요한 정보를 추출하여 Post 객체로 변환
//    Post post = Post.builder()
//          .content(postDto.getContent())
//          .createDate(localDateTime)
//          .writer(authorizationHeader)
//          .category(postDto.getCategory())
//          .build();
//
//      postRepository.createPost(post);
//    }


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
//  public void updatePost(PostDto postDto) {
//    // PostDto에서 필요한 정보를 추출하여 Post 객체로 변환
//    Post post = Post.builder()
//        .content(postDto.getContent())
//        .category(postDto.getCategory())
//        .build();
//
//    postRepository.updatePost(post);
//  }
//
//  public void deletePost(Long postId) {
//    postRepository.deletePost(postId);
//  }
}
