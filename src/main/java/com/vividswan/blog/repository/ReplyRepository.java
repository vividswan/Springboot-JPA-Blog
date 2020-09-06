package com.vividswan.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vividswan.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

}
