package com.example.kata.repository;

import com.example.kata.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameplayRepository extends JpaRepository<Game, Integer> {
}
