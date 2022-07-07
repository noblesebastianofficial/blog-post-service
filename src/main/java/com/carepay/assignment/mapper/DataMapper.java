package com.carepay.assignment.mapper;

import com.carepay.assignment.domain.dto.CreatePostRequest;
import com.carepay.assignment.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DataMapper {
    @Mapping(target = "id", ignore = true)
    Post mapToPost(CreatePostRequest postRequest);
}
