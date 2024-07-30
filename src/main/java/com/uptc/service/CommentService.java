package com.uptc.service;

import com.uptc.entity.Comment;
import com.uptc.entity.Post;
import com.uptc.response.CommentResponse;

import java.util.List;

public interface CommentService {
    Comment getCommentById(Long commentId);
    Comment createNewComment(String content, Post post);
    Comment updateComment(Long commentId, String content);
    Comment likeComment(Long commentId);
    Comment unlikeComment(Long commentId);
    void deleteComment(Long commentId);
    List<CommentResponse> getPostCommentsPaginate(Post post, Integer page, Integer size);
}
