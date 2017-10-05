package com.pevsat.controllers;

import com.pevsat.dto.RepoDto;
import com.pevsat.service.RepoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;


/**
 * Created by pevsat on 30.09.2017.
 */
@RestController
public class GitRepoController {

    private static final Logger log = LoggerFactory.getLogger(GitRepoController.class);

    @Autowired
    RepoServiceImpl service;


    @RequestMapping("/repositories/{owner}/{repositoryName}")
    public DeferredResult<ResponseEntity<?>> getRepoInformation(@PathVariable("owner") String owner,
                                                                @PathVariable("repositoryName") String repositoryName) {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
        ListenableFuture<RepoDto> repo = service.getRepo(owner,repositoryName);
        repo.addCallback(
                new ListenableFutureCallback<RepoDto>() {

                    @Override
                    public void onSuccess(RepoDto result) {
                        ResponseEntity<RepoDto> responseEntity = new ResponseEntity<RepoDto>(result, HttpStatus.OK);
                        deferredResult.setResult(responseEntity);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        log.error("Failed to fetch result from remote service", throwable);
                        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
                        deferredResult.setResult(responseEntity);
                    }
                }
        );
        return deferredResult;
    }
}
