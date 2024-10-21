import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Library {

    private List<Book> books;
    private List<Reader> readers;

    public Library() {
        init();
    }

    private void init() {
        Faker f = new Faker();
        Random r = new Random();
        books = new ArrayList<>();
        books.add(new Book("Оруэлл", "1984", 2021));
        for (int i = 0; i < 200; i++)
            books.add(new Book(f.book().author(), f.book().title(), 1700 + r.nextInt(324)));
        for (int i = 0; i < 50; i++)
            books.add(books.get(r.nextInt(200)));

        readers = new ArrayList<>();
        readers.add(new Reader("Иванов Иван Иванович", "ivanov.email@test.ru", true));
        for (int i = 0; i < 20; i++) {
            readers.add(new Reader(f.name().name(), f.internet().emailAddress(), f.bool().bool()));
        }

        readers.get(0).getBooks().add(books.get(1));
        for (int i = 0; i < 40; i++) {
            readers.get(r.nextInt(20)).getBooks().add(books.get(r.nextInt(200)));
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Reader> getReaders() {
        return readers;
    }
}