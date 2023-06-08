package com.example.toycocktail.common.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Response<T> {
    private int code;
    private T data;
}
