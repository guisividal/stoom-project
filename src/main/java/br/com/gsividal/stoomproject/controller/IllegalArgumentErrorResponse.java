package br.com.gsividal.stoomproject.controller;

import lombok.Data;

@Data
public class IllegalArgumentErrorResponse {

    private final String error;
    private final String timeStamp;
    private final String message;
}