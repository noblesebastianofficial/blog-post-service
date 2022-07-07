package com.carepay.assignment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDetails {

    private Long id;
    private Long postId;
    private String comment;
}
