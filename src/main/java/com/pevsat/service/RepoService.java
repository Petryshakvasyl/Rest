package com.pevsat.service;

import com.pevsat.dto.RepoDto;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * Created by pevsat on 05.10.2017.
 */
public interface RepoService {
    ListenableFuture<RepoDto> getRepo(String owner, String name);
 }
