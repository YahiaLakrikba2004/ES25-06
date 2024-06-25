package YahiaLakrikba.Esercitazione25._6.controllers;


import YahiaLakrikba.Esercitazione25._6.entities.Blogpost;
import YahiaLakrikba.Esercitazione25._6.exeptions.NotFoundException;
import YahiaLakrikba.Esercitazione25._6.services.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogsController {

    @Autowired
    private BlogsService blogsService;

    // POST http://localhost:3001/blogs (+ req.body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Blogpost saveBlog(@RequestBody Blogpost blogpost) {
        try {
            return blogsService.save(blogpost);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error saving blog post", e);
        }
    }

    // GET http://localhost:3001/blogs
    @GetMapping("")
    public List<Blogpost> getBlogs() {
        return blogsService.getBlogposts();
    }

    // GET http://localhost:3001/blogs/{id}
    @GetMapping("/{blogId}")
    public Blogpost findById(@PathVariable int blogId) {
        try {
            return blogsService.findById(blogId);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog post not found", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving blog post", e);
        }
    }

    // PUT http://localhost:3001/blogs/{id} (+ req.body)
    @PutMapping("/{blogId}")
    public Blogpost updateBlog(@PathVariable int blogId, @RequestBody Blogpost blogpost) {
        try {
            return blogsService.findByIdAndUpdate(blogId, blogpost);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog post not found", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error updating blog post", e);
        }
    }

    // DELETE http://localhost:3001/blogs/{id}
    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void deleteBlog(@PathVariable int blogId) {
        try {
            blogsService.findByIdAndDelete(blogId);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog post not found", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting blog post", e);
        }
    }
}