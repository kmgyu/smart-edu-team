package com.example.smart_edu_team.post;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    /**
     * mongoDB regex. 9차시에서 자세하게 나옵니다!
     * @param id
     * @return
     */
    @Query("{_id : ?0}")
    public Optional<Post> findByPostId(String id);
}
