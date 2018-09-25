
package com.studiomediatech.example.api.rest;

import com.studiomediatech.example.domain.Stuff;
import com.studiomediatech.example.repo.StuffRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class StuffController {

    private final StuffRepository repo;

    public StuffController(StuffRepository repo) {

        this.repo = repo;
    }

    @GetMapping
    public List<Stuff> stuff() {

        return repo.retriveAllStuff();
    }
}
