package com.emrevarol.demo.controller;

import com.emrevarol.demo.entity.Blog;
import com.emrevarol.demo.models.BlogDto;
import com.emrevarol.demo.models.BlogUpdateDto;
import com.emrevarol.demo.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @PostMapping
    public ResponseEntity<Blog> save(@RequestBody BlogDto blogDto){
        return ResponseEntity.ok(blogService.save(blogDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> update(@RequestBody BlogUpdateDto blogUpdateDto, Long id){
        return ResponseEntity.ok(blogService.update(blogUpdateDto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        blogService.deleteById(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> findById(@PathVariable Long id){
        return  ResponseEntity.ok(blogService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Blog>> findByPageable(@Param("page") int page, @Param("size") int size){
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(blogService.findByPagination(pageable));
    }
}
