package com.mmendozar.shopAll.exceptions;

public class ProductoSinStockException extends RuntimeException{

    public ProductoSinStockException(String message) {
        super(message);
    }
}
