package com.spring.SpringSecurity.service;

import org.springframework.stereotype.Service;
import com.spring.SpringSecurity.dto.PostDTO;

import java.util.List;

@Service
public interface PostService {
    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);

    PostDTO updatePost(PostDTO inputPost, Long postId);
}
