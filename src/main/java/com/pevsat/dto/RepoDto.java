package com.pevsat.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by pevsat on 05.10.2017.
 */
public class RepoDto {
    @JsonProperty("fullName")
    private final String fullName;
    @JsonProperty("description")
    private final String description;
    @JsonProperty("cloneUrl")
    private final String cloneUrl;
    @JsonProperty("stars")
    private final int stars;
    @JsonProperty("createdAt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private final Date createdAt;


    public RepoDto(String name, String cloneUrl, String description, int stars, Date createdAt) {
        this.fullName = name;
        this.cloneUrl = cloneUrl;
        this.description = description;
        this.stars = stars;
        this.createdAt = createdAt;
    }
}
