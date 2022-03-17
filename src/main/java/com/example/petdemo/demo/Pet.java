package com.example.petdemo.demo;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Pet {

    protected Long id;

    @NotNull
    private Category category;

    @NotNull
    private String name;

    @NotNull
    private Tag tag;

    @NotNull
    private String status;

    private List<String> photo=new ArrayList<>();
}
