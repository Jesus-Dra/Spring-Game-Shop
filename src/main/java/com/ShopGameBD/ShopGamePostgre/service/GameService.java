package com.ShopGameBD.ShopGamePostgre.service;

import com.ShopGameBD.ShopGamePostgre.dto.GamePatchDTO;
import com.ShopGameBD.ShopGamePostgre.dto.GameRequestDTO;
import com.ShopGameBD.ShopGamePostgre.dto.GameResponseDTO;
import com.ShopGameBD.ShopGamePostgre.dto.GameResponseIDTO;
import com.ShopGameBD.ShopGamePostgre.entity.Category;
import com.ShopGameBD.ShopGamePostgre.entity.Game;
import com.ShopGameBD.ShopGamePostgre.exception.CategoryNotFound;
import com.ShopGameBD.ShopGamePostgre.exception.GameAlreadyExistsException;
import com.ShopGameBD.ShopGamePostgre.exception.GameNotFound;
import com.ShopGameBD.ShopGamePostgre.repository.CategoryRepository;
import com.ShopGameBD.ShopGamePostgre.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;

    public GameService(GameRepository gameRepository,
                       CategoryRepository categoryRepository) {
        this.gameRepository = gameRepository;
        this.categoryRepository = categoryRepository;
    }


    //Convert DTO -> entity and entity -> DTO

    private Game dtoToEntity(GameRequestDTO dto) {
        Game convert = new Game();
        convert.setName(dto.getName());
        convert.setAge(dto.getAge());
        convert.setType(dto.getType());
        convert.setPrice(dto.getPrice());
        return convert;
    }

    private GameResponseDTO entityToDto(Game convert) {
        GameResponseDTO convertToDto = new GameResponseDTO();
        convertToDto.setName(convert.getName());
        convertToDto.setAge(convert.getAge());
        convertToDto.setType(convert.getType());
        convertToDto.setPrice(convert.getPrice());
        convertToDto.setCategory(convert.getCategory().getName());
        return convertToDto;
    }

    private GameResponseIDTO entityToDtoId(Game convert) {
        GameResponseIDTO convertToDto = new GameResponseIDTO();
        convertToDto.setId(convert.getId());
        convertToDto.setName(convert.getName());
        convertToDto.setAge(convert.getAge());
        convertToDto.setType(convert.getType());
        convertToDto.setPrice(convert.getPrice());
        return convertToDto;
    }


    public List<GameResponseDTO> getAllgame() {

        List<Game> getAllGamesBD = gameRepository.findAll();

        List<GameResponseDTO> convertListGame = new ArrayList<>();

        for (Game game : getAllGamesBD) {
            convertListGame.add(entityToDto(game));
        }

        return convertListGame;

    }

    public GameResponseDTO createGame(GameRequestDTO dto) {

        //Traer el juego si existe
        gameRepository.findByName(dto.getName()).
                ifPresent(game -> {
                    throw new GameAlreadyExistsException("Ya existe un juego con ese nombre: " + dto.getName());
                });

        //Buscar la categoria del juego si existe
        Category category = categoryRepository.findById(dto.getCategoryID())
                .orElseThrow(() -> new CategoryNotFound("no existe esa categoria: "+dto.getCategoryID()));

        //Convertir el juego en una entidad para guardarse en la BD
        Game newGame = dtoToEntity(dto);
        //Inyectarle la categoria al juego que se guardara en la BD
        newGame.setCategory(category);
        //Guardar el nuevo juego en la BD
        Game saveNewGame = gameRepository.save(newGame);

        //Retornar los datos convertidos nuevamente en DTO para que el usuario los vea
        return entityToDto(saveNewGame);
    }

    public GameResponseDTO getFindByName(Integer id) {

        return gameRepository.findById(id).map(this::entityToDto).
                orElseThrow(() -> new GameNotFound("No existe nombre de ese juego: " + id));
    }

    public GameResponseDTO saveGame(Integer id, GameRequestDTO newData) {
        Game existing = gameRepository.findById(id).
                orElseThrow(() -> new GameNotFound("Juego no encontrado"));

        Category category = categoryRepository.findById(newData.getCategoryID()).
                orElseThrow(() -> new CategoryNotFound("No existe esa categoria"));

        Game savedGame = dtoToEntity(newData);


        existing.setName(savedGame.getName());
        existing.setType(savedGame.getType());
        existing.setAge(savedGame.getAge());
        existing.setPrice(savedGame.getPrice());
        existing.setCategory(category);

        Game update = gameRepository.save(existing);

        return entityToDto(update);
    }

    public GameResponseDTO patchGame(Integer id, GamePatchDTO newData){
        Game existing = gameRepository.findById(id).
                orElseThrow(() -> new GameNotFound("Juego no encontrado"));

        if(newData.getName() != null){
            existing.setName(newData.getName());
        }

        if(newData.getAge() != null){
            existing.setAge(newData.getAge());
        }

        if(newData.getPrice() != null){
            existing.setPrice(newData.getPrice());
        }

        if(newData.getCategoryId() != null){
            Category category = categoryRepository.findById(newData.getCategoryId()).
                    orElseThrow(() -> new CategoryNotFound("Categoria no encontrada"));
            existing.setCategory(category);
        }

        Game update = gameRepository.save(existing);

        return entityToDto(update);

    }

    public String deleteGameByID(Integer id) {

        Game existing = gameRepository.findById(id).
                orElseThrow(() -> new GameNotFound("Juego no encontrado:"));

        String nameGame = existing.getName();
        Integer IDelete = existing.getId();

        gameRepository.deleteById(IDelete);

        return nameGame;

    }


}
