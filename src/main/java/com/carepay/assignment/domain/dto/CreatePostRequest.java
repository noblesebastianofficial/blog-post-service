package com.carepay.assignment.domain.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {

    @NotNull
    @Size(max = 128)
    private String title;
    @NotNull
    @Size(max = 128)
    private String content;
}
