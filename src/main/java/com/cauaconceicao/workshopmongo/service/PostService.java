package com.cauaconceicao.workshopmongo.service;

import com.cauaconceicao.workshopmongo.domain.Post;
import com.cauaconceicao.workshopmongo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id) {
        return postRepository.findById(id).get();
    }


}
