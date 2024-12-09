package org.example.management.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.management.model.request.CommentRequest;
import org.example.management.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Добавление комментария")
    @PostMapping
    public void addComment(@RequestBody CommentRequest commentRequest) {
        commentService.addComment(commentRequest);
    }
}
