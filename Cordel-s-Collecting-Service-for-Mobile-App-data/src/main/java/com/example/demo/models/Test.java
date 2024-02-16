package com.example.demo.models;
import io.swagger.v3.oas.annotations.media.Schema;

public class Test {

    private String name;

    @Schema(description = "test")
    public String getName() {
        return name;
    }


}
