package com.emrevarol.demo.controller;

import com.emrevarol.demo.entity.Author;
import com.emrevarol.demo.models.AuthorDto;
import com.emrevarol.demo.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<Page<Author>> findByPageable(@Param("page") int page, @Param("size") int size){
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(authorService.findByPagination(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id){
        return ResponseEntity.ok(authorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorDto authorDto){
        return ResponseEntity.ok(authorService.save(authorDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@RequestBody AuthorDto authorDto, @PathVariable Long id){
        return ResponseEntity.ok(authorService.update(authorDto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id){
        authorService.deleteById(id);
        return ResponseEntity.ok(true);
    }



}
