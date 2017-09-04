package com.gogol.spring5webapp.bootstrap;

import com.gogol.spring5webapp.model.Author;
import com.gogol.spring5webapp.model.Book;
import com.gogol.spring5webapp.model.Publisher;
import com.gogol.spring5webapp.repositories.AuthorRepository;
import com.gogol.spring5webapp.repositories.BookRepository;
import com.gogol.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){
        Publisher publisherA = new Publisher("First Publosher", "First Address");
        publisherRepository.save(publisherA);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain driven design","123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(publisherA);

        authorRepository.save(eric);
        bookRepository.save(ddd);


        Publisher publisherB = new Publisher("Publisher Second", "Second Address");
        publisherRepository.save(publisherB);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE without EJB","23444");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(publisherB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

    }


}
