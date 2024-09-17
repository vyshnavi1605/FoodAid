package com.FoodAid.Group35.controller;

import com.FoodAid.Group35.entity.Notes;
import com.FoodAid.Group35.repository.NoteRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class NoteController {
    @Autowired
    NoteRepository textRepository;
    @PostMapping("auth/note")
    public Notes work(@RequestBody Notes notes) {
        return textRepository.save(notes);
    }
    @GetMapping("auth/notes")
    public List<Notes> getAllTexts() {
        return textRepository.findAll();
    }
}