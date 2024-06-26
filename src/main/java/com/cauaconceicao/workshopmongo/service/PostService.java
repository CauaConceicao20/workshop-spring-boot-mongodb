package com.cauaconceicao.workshopmongo.service;

import com.cauaconceicao.workshopmongo.domain.Post;
import com.cauaconceicao.workshopmongo.domain.User;
import com.cauaconceicao.workshopmongo.repository.PostRepository;
import com.cauaconceicao.workshopmongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id) {
        Optional<Post> post = postRepository.findById(id);

        return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findyByTitle(String text) {
        return postRepository.findByTitleContainingIgnoreCase(text);
    }
}
