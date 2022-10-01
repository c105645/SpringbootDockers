package com.stackroute.newz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.newz.model.News;

/*
* This class is implementing the MongoRepository interface for Note.
* Annotate this class with @Repository annotation
* */

public interface NewsRepository extends JpaRepository<News, String> {

}
