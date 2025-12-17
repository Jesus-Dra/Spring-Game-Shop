package com.ShopGameBD.ShopGamePostgre.controller;


import com.ShopGameBD.ShopGamePostgre.dto.GamePatchDTO;
import com.ShopGameBD.ShopGamePostgre.dto.GameRequestDTO;
import com.ShopGameBD.ShopGamePostgre.dto.GameResponseDTO;
import com.ShopGameBD.ShopGamePostgre.entity.Game;
import com.ShopGameBD.ShopGamePostgre.service.GameService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService){
         this.gameService = gameService;
    }

    @GetMapping
    public List<GameResponseDTO> allGames(){
        List<GameResponseDTO> getAllGames = gameService.getAllgame();

        return getAllGames;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFindByName(@PathVariable Integer id){
            GameResponseDTO game = gameService.getFindByName(id);

            return ResponseEntity.ok(game);
    }

    @PostMapping
    public ResponseEntity<GameResponseDTO> createGame(@Valid @RequestBody GameRequestDTO dto){

            GameResponseDTO created = gameService.createGame(dto);
            return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameResponseDTO> updateGame(@Valid @RequestBody GameRequestDTO game, @PathVariable Integer id){
            GameResponseDTO update = gameService.saveGame(id, game);
            return ResponseEntity.ok(update);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GameResponseDTO> PatchGame(@Valid @RequestBody GamePatchDTO game, @PathVariable Integer id){
        GameResponseDTO update = gameService.patchGame(id, game);
        return ResponseEntity.ok(update);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGame(@PathVariable Integer id){
            String nameGame = gameService.deleteGameByID(id);
            return ResponseEntity.ok("Juego eliminado: "+nameGame);

    }
}
