package com.vividswan.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vividswan.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
}
