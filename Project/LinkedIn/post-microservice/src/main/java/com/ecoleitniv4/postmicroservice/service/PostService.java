package com.ecoleitniv4.postmicroservice.service;

import com.ecoleitniv4.postmicroservice.dto.PostRequest;
import com.ecoleitniv4.postmicroservice.dto.PostResponse;
import com.ecoleitniv4.postmicroservice.model.Post;
import com.ecoleitniv4.postmicroservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;

    // Getting the profile of the person who makes the post
    private final ProfileServiceE profileServiceE;

    /****************************************************************************
     *                            Tests functions                               *
     ****************************************************************************/

    // Testing if profile is not null and exists
// it shall be done with the code -> "profileServiceE.profileTest(data)"

    // Testing if id exist
    public boolean dataExists(Long id) {
        if (postRepository.existsById(id)) {
            return true;
        } else {
            log.info("Post " + id + " does not exist");
            return false;
        }
    }

    // Retrieving information
    private PostResponse mapToPostResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .profile_id(post.getProfile_id())
                .title(post.getTitle())
                .content(post.getContent())
                .creation_date(post.getCreation_date())
                .build();
    }

    /**************************************************************
     *                       CRUD Functions                       *
     **************************************************************/

    // Create a new education
    public void createPost(PostRequest postRequest) {
        boolean status = true;

        status = profileServiceE.profileTest(postRequest.getProfile_id());

        log.info("Creation status: " + status);

        if (status) {
            Post post = Post.builder()
                    .profile_id(postRequest.getProfile_id())
                    .title(postRequest.getTitle())
                    .content(postRequest.getContent())
                    .creation_date(postRequest.getCreation_date())
                    .build();

            postRepository.save(post);
            log.info("Post created successfully");
        }
    }

    // Read - All
    public List<PostResponse> readAllPosts() {
        List<Post> post = postRepository.findAll();

        return post.stream().map(this::mapToPostResponse).toList();
    }

    // Read - Single
    public PostResponse readSinglePost(Long id) {
        if (dataExists(id)) {
            Post post = postRepository.findById(id).get();
            return mapToPostResponse(post);
        } else {
            return null;
        }
    }

    // Put
    public void updatePost(Long id, PostRequest updatePostRequest) {
        boolean status = true;

        // Not null post id
        if (dataExists(id)) {
            Post existPost = postRepository.findById(id).get();

            status = profileServiceE.profileTest(updatePostRequest.getProfile_id());
            log.info("Creation status : " + status);

            if (status) {
                existPost.setProfile_id(updatePostRequest.getProfile_id());
                existPost.setTitle(updatePostRequest.getTitle());
                existPost.setContent(updatePostRequest.getContent());
                existPost.setCreation_date(updatePostRequest.getCreation_date());

                postRepository.save(existPost);
                log.info("Post : " + id + " Updated successfully");
            }
        }
    }

    // Delete Post
    public void deletePost(Long id) {
        if (dataExists(id)) {
            postRepository.deleteById(id);
        }
    }
}
