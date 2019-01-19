package com.richard.akka.springboot.daos;

import com.richard.akka.springboot.models.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDao extends CrudRepository<Movie, Long> {
}
