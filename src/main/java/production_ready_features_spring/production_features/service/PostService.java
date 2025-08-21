package production_ready_features_spring.production_features.service;

import org.springframework.stereotype.Service;
import production_ready_features_spring.production_features.dto.PostDTO;

import java.util.List;

@Service
public interface PostService {
    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);

    PostDTO updatePost(PostDTO inputPost, Long postId);
}
