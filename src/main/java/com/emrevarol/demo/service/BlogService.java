package com.emrevarol.demo.service;

import com.emrevarol.demo.entity.Author;
import com.emrevarol.demo.entity.Blog;
import com.emrevarol.demo.entity.Category;
import com.emrevarol.demo.models.BlogDto;
import com.emrevarol.demo.models.BlogUpdateDto;
import com.emrevarol.demo.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public Blog save(BlogDto blogDto){
        Blog blog = new Blog();
        Author author = authorService.findById(blogDto.getAuthorId());
        blog.setAuthor(author);
        if(blogDto.getCategoryIds() != null){
            for (Long id : blogDto.getCategoryIds()){
                Category category = categoryService.findById(id);
                blog.getCategory().add(category);
            }
        }
        if(blogDto.getTags() != null){
            for (String tag : blogDto.getTags()){
                blog.getTags().add(tag);
            }
        }

        blog.setTitle(blogDto.getTitle());
        blog.setImage(blogDto.getImage());
        blog.setContent(blogDto.getContent());
        authorService.increaseBlogCount(blogDto.getAuthorId());
        return blogRepository.save(blog);
    }

    public void deleteById(Long id){
        Blog blog = findById(id);
        blogRepository.deleteById(id);
        Long authorId = blog.getAuthor().getId();
        authorService.decreaseBlogCount(authorId);
    }

    public Blog findById(Long id){
        return blogRepository.findById(id).orElseThrow();
    }

    public Page<Blog> findByPagination(Pageable pageable){
        return blogRepository.findAll(pageable);
    }

    public Blog update(BlogUpdateDto blogUpdateDto, Long id){
        Blog blog = findById(id);
        for (Long categoryId : blogUpdateDto.getCategoryIds()){
            Category category = categoryService.findById(categoryId);
            blog.getCategory().add(category);
        }
        for (String tag : blogUpdateDto.getTags()){
            blog.getTags().add(tag);
        }
        blog.setTitle(blogUpdateDto.getTitle());
        blog.setImage(blogUpdateDto.getImage());
        blog.setContent(blogUpdateDto.getContent());
        return blogRepository.save(blog);
    }


}
