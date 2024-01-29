package com.ecoleitniv4.postmicroservice.service;

import com.ecoleitniv4.postmicroservice.dto.CommentRequest;
import com.ecoleitniv4.postmicroservice.dto.CommentResponse;
import com.ecoleitniv4.postmicroservice.model.Comment;
import com.ecoleitniv4.postmicroservice.repository.CommentRepository;
import com.ecoleitniv4.postmicroservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;

    // Calling the post Repository to test if the post injected exists
    private final PostRepository postRepository;

    // Getting the profile of the person who makes the post
    private final ProfileServiceE profileServiceE;

    /****************************************************************************
     *                            Tests functions                               *
     ****************************************************************************/

// Testing if profile is not null and exists
// it shall be done with the code -> "profileServiceE.profileTest(data)"

    // Testing if id exist
    public boolean dataPostExists(Long id) {
        if (id == null){
            log.info("The Post ID CANNOT be NULL");
            return false;
        } else {
            if (postRepository.existsById(id)) {
                return true;
            } else {
                log.info("Post " + id + " does not exist");
                return false;
            }
        }
    }

    // Testing if id exist
    public boolean dataExists(Long id) {
        if (commentRepository.existsById(id)) {
            return true;
        } else {
            log.info("Comment " + id + " does not exist");
            return false;
        }
    }

    // retrieving comments
    private CommentResponse mapToCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .profile_id(comment.getProfile_id())
                .post_id(comment.getPost_id())
                .content(comment.getContent())
                .creation_date(comment.getCreation_date())
                .build();
    }

    /**************************************************************
     *                       CRUD Functions                       *
     **************************************************************/

    // Create
    public void createComment(CommentRequest commentRequest) {
        boolean status = true;

        // Testing if profile exists
        status = profileServiceE.profileTest(commentRequest.getProfile_id());

        // Testing if post exists
        if (status) {status = dataPostExists(commentRequest.getPost_id());}

        log.info("Creation status: " + status);

        if (status) {
            Comment comment = Comment.builder()
                    .profile_id(commentRequest.getProfile_id())
                    .post_id(commentRequest.getPost_id())
                    .content(commentRequest.getContent())
                    .creation_date(commentRequest.getCreation_date())
                    .build();

            commentRepository.save(comment);
            log.info("Comment created successfully");
        }
    }

    // Get All Comments
    public List<CommentResponse> readAllComments() {
        List<Comment> comments = commentRepository.findAll();

        return comments.stream().map(this::mapToCommentResponse).toList();
    }

    // Get single Comment
    public CommentResponse readSingleComment(Long id) {
        if (dataExists(id)) {
            Comment comment = commentRepository.findById(id).get();
            return mapToCommentResponse(comment);
        } else {
            return null;
        }
    }

    // Update
    public void updateComment(Long id, CommentRequest commentRequest) {
        boolean status = true;

        if (dataExists(id)) {
            Comment existComment = commentRepository.findById(id).get();

            // Testing if profile exists
            status = profileServiceE.profileTest(commentRequest.getProfile_id());

            // Testing if post exists
            if (status) {status = dataPostExists(commentRequest.getPost_id());}

            log.info("Creation status: " + status);

            if (status){
                existComment.setContent(commentRequest.getContent());
                existComment.setPost_id(commentRequest.getPost_id());
                existComment.setProfile_id(commentRequest.getProfile_id());
                existComment.setCreation_date(commentRequest.getCreation_date());

                commentRepository.save(existComment);
                log.info("Comment: " + id + " updated successfully");
            }
        }
    }

    // Deleting comment
    public void deleteComment(Long id) {
        if (dataExists(id)) {
            commentRepository.deleteById(id);
        }
    }
}
