package com.spring.SpringSecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spring.SpringSecurity.entity.PostEntity;


@Repository
public interface PostRepo extends JpaRepository<PostEntity,Long> {

}
