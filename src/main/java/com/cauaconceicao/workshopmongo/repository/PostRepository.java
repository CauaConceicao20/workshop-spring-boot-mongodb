package com.cauaconceicao.workshopmongo.repository;

import com.cauaconceicao.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
