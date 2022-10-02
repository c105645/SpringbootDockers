package com.stackroute.newz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.newz.dao.News;



public interface NewsRepository extends JpaRepository<News, String> {

}
