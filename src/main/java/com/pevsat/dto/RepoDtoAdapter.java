package com.pevsat.dto;

import com.pevsat.domain.GitRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureAdapter;
import java.util.concurrent.ExecutionException;

/**
 * Created by pevsat on 05.10.2017.
 */
public class RepoDtoAdapter extends ListenableFutureAdapter<RepoDto, ResponseEntity<GitRepository>> {

    private final String owner;
    private final String name;

    public RepoDtoAdapter(String owner, String name, ListenableFuture<ResponseEntity<GitRepository>> gitRepository) {
        super(gitRepository);
        this.owner = owner;
        this.name = name;
    }

    @Override
    protected RepoDto adapt(ResponseEntity<GitRepository> responseEntity) throws ExecutionException {
        GitRepository gitRepository = responseEntity.getBody();
        return new RepoDto(gitRepository.getFullName(), gitRepository.getCloneUrl(), gitRepository.getDescription(), gitRepository.getStars(), gitRepository.getCreatedAt());
    }
}
