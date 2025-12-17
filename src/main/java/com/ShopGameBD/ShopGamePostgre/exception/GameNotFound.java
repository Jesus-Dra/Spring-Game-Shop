package com.ShopGameBD.ShopGamePostgre.exception;

public class GameNotFound extends RuntimeException {
    public GameNotFound(String message){
        super(message);
    }
}
