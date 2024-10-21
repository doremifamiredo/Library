import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LibraryTest {
    Library library = new Library();

    @Test
    void sorted() {
        List<Book> sortedBooks = library.getBooks().stream()
                .sorted(Comparator.comparing(Book::getIssueYear))
                .collect(Collectors.toList());
        Map<Integer, List<Book>> grouped = sortedBooks.stream()
                .collect(Collectors.groupingBy(Book::getIssueYear));
        grouped.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(System.out::println);
    }

    @Test
    void map() {
        List<EmailAddress> addresses = library.getReaders().stream()
                .filter(reader -> reader.isSubscriber())
                .map(Reader::getEmail)
                .map(EmailAddress::new)
                .collect(Collectors.toList());
        List<String> authors = library.getBooks().stream()
                .filter(book -> book.getIssueYear() > 1990)
                .map(Book::getAuthor)
                .collect(Collectors.toList());
        authors.forEach(System.out::println);
    }

    @Test
    void filter() {
        List<EmailAddress> addresses = library.getReaders().stream()
                //  .filter(Reader::isSubscriber)
                .filter(reader -> reader.getBooks().size() == 3)
                .map(Reader::getEmail)
                .map(EmailAddress::new)
                .collect(Collectors.toList());
        addresses.stream()
                .map(EmailAddress::getEmail)
                .forEach(System.out::println);
    }

    @Test
    void flatMap() {
        List<Book> books = library.getReaders().stream()
                .flatMap(reader -> reader.getBooks().stream())
                .distinct()
                .collect(Collectors.toList());
        List<Book> books18thCentury = library.getReaders().stream()
                .flatMap(reader -> reader.getBooks().stream())
                .distinct()
                .filter(book -> book.getIssueYear() <= 1800)
                .collect(Collectors.toList());
        List<Book> books19thCentury = library.getReaders().stream()
                .flatMap(reader -> reader.getBooks().stream())
                .distinct()
                .filter(book -> book.getIssueYear() > 1800 && book.getIssueYear() <= 1900)
                .collect(Collectors.toList());
        List<Book> books20thCentury = books.stream()
                .filter(book -> book.getIssueYear() > 1900 && book.getIssueYear() <= 2000)
                .collect(Collectors.toList());
        books18thCentury.stream()
                .map(book -> book.getName())
                .forEach(System.out::println);
    }

    @Test
    void anyMatch() {
        boolean ans = library.getReaders().stream()
                .flatMap(reader -> reader.getBooks().stream())
                .anyMatch(book -> book.getName().equals("O Pioneers!"));
        if (library.getReaders().stream()
                .flatMap(reader -> reader.getBooks().stream())
                .anyMatch(book -> book.getName().equals("1984"))) System.out.println("Si");
        else System.out.println("No");
    }

    @Test
    void reduce() {
        int max = library.getReaders().stream()
                .map(reader -> reader.getBooks().size())
                .reduce(0, (a, b) -> a > b ? a : b);
        System.out.println(max);
    }
}
