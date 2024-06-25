package YahiaLakrikba.Esercitazione25._6.controllers;


import YahiaLakrikba.Esercitazione25._6.entities.Author;
import YahiaLakrikba.Esercitazione25._6.exeptions.NotFoundException;
import YahiaLakrikba.Esercitazione25._6.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorService authorsService;

    // POST http://localhost:3001/authors (+ req.body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) //
    public Author saveAuthor(@RequestBody Author author) {
        try {
            return authorsService.save(author);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error saving author", e);
        }
    }

    // GET http://localhost:3001/authors
    @GetMapping("")
    public List<Author> getAuthors() {
        return authorsService.getAuthors();
    }

    // GET http://localhost:3001/authors/{id}
    @GetMapping("/{authorId}")
    public Author findById(@PathVariable int authorId) {
        try {
            return authorsService.findById(authorId);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving author", e);
        }
    }

    // PUT http://localhost:3001/authors/{id} (+ req.body)
    @PutMapping("/{authorId}")
    public Author updateAuthor(@PathVariable int authorId, @RequestBody Author author) {
        try {
            return authorsService.findByIdAndUpdate(authorId, author);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error updating author", e);
        }
    }

    // DELETE http://localhost:3001/authors/{id}
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void deleteAuthor(@PathVariable int authorId) {
        try {
            authorsService.findByIdAndDelete(authorId);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting author", e);
        }
    }
}

