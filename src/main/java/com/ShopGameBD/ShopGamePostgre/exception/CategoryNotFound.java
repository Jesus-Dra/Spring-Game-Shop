package com.ShopGameBD.ShopGamePostgre.exception;

public class CategoryNotFound extends RuntimeException {
    public CategoryNotFound(String message) {
        super(message);
    }
}
