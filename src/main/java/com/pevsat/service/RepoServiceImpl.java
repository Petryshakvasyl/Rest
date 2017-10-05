package com.pevsat.service;

import com.pevsat.domain.GitRepository;
import com.pevsat.dto.RepoDto;
import com.pevsat.dto.RepoDtoAdapter;
import com.pevsat.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

/**
 * Created by pevsat on 03.10.2017.
 */
@Service
public class RepoServiceImpl implements RepoService{

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;

    @Autowired
    private HttpEntity<String> entity;

    @Override
    public ListenableFuture<RepoDto> getRepo(String owner, String name) {
        ListenableFuture<ResponseEntity<GitRepository>> repoEntity = asyncRestTemplate.exchange(Constants.QUESTIONS_URL, HttpMethod.GET, entity,
                GitRepository.class, owner, name);
        return new RepoDtoAdapter(owner, name, repoEntity);
    }
}
