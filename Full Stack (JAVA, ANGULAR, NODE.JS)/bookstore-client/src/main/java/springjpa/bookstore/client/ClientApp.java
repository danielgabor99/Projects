package springjpa.bookstore.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;


import springjpa.bookstore.client.ui.Console;
import springjpa.bookstore.core.domain.Book;
import springjpa.bookstore.web.dto.BookDto;
import springjpa.bookstore.web.dto.BooksDto;

public class ClientApp {
    public static final String URL = "http://localhost:8080/api/books";

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "springjpa.bookstore.client"
                );

        RestTemplate restTemplate = context.getBean(RestTemplate.class);
        Console console = context.getBean(Console.class);
        console.runConsole(restTemplate);

        /*BookDto savedbook = restTemplate.postForObject(
                URL,
                new BookDto("a1", "b1", 111),
                BookDto.class);;
        System.out.println("saved: "+savedbook);*/

        System.out.println("bye ");
    }
}
