package br.com.gsividal.stoomproject.controller;

import lombok.Data;

@Data
public class Violation {

    private final String fieldName;
    private final String message;
}