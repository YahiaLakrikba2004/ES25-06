package YahiaLakrikba.Esercitazione25._6.services;


import YahiaLakrikba.Esercitazione25._6.entities.Blogpost;
import YahiaLakrikba.Esercitazione25._6.exeptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

@Service
public class BlogsService {
    private final List<Blogpost> blogposts = new ArrayList<>();

    public Blogpost save (Blogpost blogpost) {
        Random random= new Random();
        blogpost.setId(random.nextInt());
        blogpost.setCover("https://placedog.net/200/300");
        this.blogposts.add(blogpost);
        return blogpost;
    }
    public List<Blogpost> getBlogposts() {
        return this.blogposts;
    }

    public Blogpost findById(int id) {
        Blogpost found = null;

        for (Blogpost blogpost : blogposts) {
            if (blogpost.getId() == id)
                found = blogpost;
        }
        if (found == null)
            throw new NotFoundException(id);
        return found;
    }

    public void findByIdAndDelete(int id) {
        ListIterator<Blogpost> iterator = this.blogposts.listIterator();

        while (iterator.hasNext()) {
            Blogpost currentBlog = iterator.next();
            if (currentBlog.getId() == id) {
                iterator.remove();
            }
        }
    }

    public Blogpost findByIdAndUpdate(int id, Blogpost body) {
        Blogpost found = null;

        for (Blogpost currentBlog : blogposts) {
            if (currentBlog.getId() == id) {
                found = currentBlog;
                found.setCover(body.getCover());
                found.setCategory(body.getCategory());
                found.setContent(body.getCover());
                found.setReadingTime(body.getReadingTime());
                found.setId(id);
            }
        }
        if (found == null)
            throw new NotFoundException(id);
        return found;

    }
}
