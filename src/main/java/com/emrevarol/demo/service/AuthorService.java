package com.emrevarol.demo.service;

import com.emrevarol.demo.entity.Author;
import com.emrevarol.demo.models.AuthorDto;
import com.emrevarol.demo.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public Author save(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        return authorRepository.save(author);
    }

    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    public Author update(AuthorDto authorDto, Long id) {
        Optional<Author> byId = authorRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setFirstName(authorDto.getFirstName());
            byId.get().setLastName(authorDto.getLastName());
            return authorRepository.save(byId.get());
        }
        throw new IllegalArgumentException("Author not found");
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow();
    }

    public Page<Author> findByPagination(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    public void increaseBlogCount(Long id) {
        Author author = findById(id);
        Long blogAmount = author.getBlogAmount();
        author.setBlogAmount(blogAmount + 1);
        authorRepository.save(author);
    }

    public void decreaseBlogCount(Long id) {
        Author author = findById(id);
        Long blogAmount = author.getBlogAmount();
        author.setBlogAmount(blogAmount - 1);
        authorRepository.save(author);
    }
}
