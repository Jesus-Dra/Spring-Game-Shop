package com.ShopGameBD.ShopGamePostgre.repository;

import com.ShopGameBD.ShopGamePostgre.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Integer> {
    Optional<Game> findByName(String name);
}
