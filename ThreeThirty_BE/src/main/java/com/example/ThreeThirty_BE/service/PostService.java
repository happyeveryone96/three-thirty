package com.example.ThreeThirty_BE.service;

import com.example.ThreeThirty_BE.domain.Post;
import com.example.ThreeThirty_BE.dto.GetPostResponseDto;
import com.example.ThreeThirty_BE.dto.PostDto;
import com.example.ThreeThirty_BE.mapper.PostRepositroy;
import com.example.ThreeThirty_BE.security.jwt.util.JwtTokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepositroy postRepository;
  private final JwtTokenizer jwtTokenizer;

  public void createPost(String authorizationHeader, PostDto postDto) {
    Long id = jwtTokenizer.getUserIdFromToken(authorizationHeader);
    // 게시물 유형, 기업 종목, 첨부파일 유형 -> 식별자
    String cateogoryId = postDto.getCategory();
    String companyId = postDto.getCompany();
    String fileTypeID = postDto.getFileType();


    if(id != null){
      LocalDateTime localDateTime = LocalDateTime.now();

      // PostDto에서 필요한 정보를 추출하여 Post 객체로 변환
      Post post = Post.builder()
          .content(postDto.getContent())
          .createDate(localDateTime)
          .writer(authorizationHeader)
          .category(postDto.getCategory())
          .build();

      postRepository.createPost(post);
    }

  }

  public GetPostResponseDto getPost(Long postId) {
    List<Post> postList = postRepository.getPost(postId);

    List<GetPostResponseDto.GetPost> postPairs = new ArrayList<>();
    for (Post post : postList) {
       GetPostResponseDto.GetPost getPost = new GetPostResponseDto.GetPost(post.getContent(), post.getCreateDate(), post.getFileUrl(), post.getWriter());
      postPairs.add(getPost);
    }

    return GetPostResponseDto.builder()
        .getPosts(postPairs)
        .build();
  }

  public void updatePost(PostDto postDto) {
    // PostDto에서 필요한 정보를 추출하여 Post 객체로 변환
    Post post = Post.builder()
        .content(postDto.getContent())
        .category(postDto.getCategory())
        .build();

    postRepository.updatePost(post);
  }

  public void deletePost(Long postId) {
    postRepository.deletePost(postId);
  }
}
