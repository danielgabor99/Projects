package springjpa.bookstore.client.ui;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import springjpa.bookstore.core.domain.Book;
import springjpa.bookstore.core.domain.Client;
import springjpa.bookstore.core.domain.Orders;
import springjpa.bookstore.web.dto.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class Console {
    public static final String URL = "http://localhost:8080/api/books";
    public static final String URL2 = "http://localhost:8080/api/clients";
    public static final String URL3 = "http://localhost:8080/api/orders";

    public void runConsole(RestTemplate restTemplate) {

        String command = "1";
        while (!command.equals("0")) {
            printMenu();
            Scanner myscan = new Scanner(System.in);
            command = myscan.nextLine();
            if (command.equals("1")) {
                while (true) {
                    printBookMenu();
                    command = myscan.nextLine();
                    if (command.equals("1")) {
                        addBook(restTemplate);
                    }
                    if (command.equals("2")) {
                        printAllBooks(restTemplate);
                    }
                    if (command.equals("3")) {
                        updateBook(restTemplate);
                    }
                    if (command.equals("4")) {
                        deleteBook(restTemplate);
                    }
                    if (command.equals("5")) {
                        filterBook(restTemplate);
                    }
                    if (command.equals("0")) {
                        command = "100";
                        break;
                    }
                }
            }
            if (command.equals("2")) {
                while (true) {
                    printClientMenu();
                    command = myscan.nextLine();
                    if (command.equals("1")) {
                        addClient(restTemplate);
                    }
                    if (command.equals("2")) {
                        printAllClients(restTemplate);
                    }
                    if (command.equals("3")) {
                        updateClient(restTemplate);
                    }
                    if (command.equals("4")) {
                        deleteClient(restTemplate);
                    }
                    if (command.equals("5")) {
                        buyBooks(restTemplate);
                    }
                    if (command.equals("6")) {
                        clientReports(restTemplate);
                    }
                    if (command.equals("7")) {
                        filterClient(restTemplate);
                    }
                    if (command.equals("0")) {
                        command = "100";
                        break;
                    }
                }
            }
            if (command.equals("3")) {
                printAllOrders(restTemplate);
            }
        }
    }

    private void filterClient(RestTemplate restTemplate) {
        Set<ClientDto> clients = restTemplate.getForObject(URL2, ClientsDto.class).getClients();
        Set<ClientDto> clients2 = clients.stream().filter(x -> x.getAvailableAmount() > 50).collect(Collectors.toSet());
        clients2.forEach(System.out::println);
    }

    private void clientReports(RestTemplate restTemplate) {
        Set<ClientDto> clients = restTemplate.getForObject(URL2, ClientsDto.class).getClients();
        List<ClientDto> clients2 = clients.stream().sorted(Comparator.comparingInt(n -> amountOfBooksBuyedByClient(n.getId(), restTemplate))).collect(Collectors.toList());
        clients2.forEach(System.out::println);
    }

    private void buyBooks(RestTemplate restTemplate) {
        System.out.println("Client id=  ");
        Scanner myscan = new Scanner(System.in);
        String clientId = myscan.nextLine();


        ClientDto myClient = restTemplate.getForObject(URL2 + "/{id}", ClientDto.class, (long) Integer.parseInt(clientId));


        System.out.println("The Client " + "'" + myClient.getClientName() + "' has " + myClient.getAvailableAmount() + " money");
        System.out.println("Books: ");

        System.out.println("Choose a book= ");
        String bookId = myscan.nextLine();

        BookDto mybook = restTemplate.getForObject(URL + "/{id}", BookDto.class, (long) Integer.parseInt(bookId));
        System.out.println(mybook.getBookPrice());

        Client newClient = new Client(myClient.getClientName(), myClient.getAvailableAmount() - mybook.getBookPrice());
        newClient.setId(myClient.getId());
        restTemplate.put(URL2 + "/{id}", newClient, newClient.getId());
        Orders order = new Orders(myClient.getId(), mybook.getId());
        OrderDto savedorder = restTemplate.postForObject(
                URL3,
                order,
                OrderDto.class);
        System.out.println("saved: " + savedorder);
    }

    private void deleteClient(RestTemplate restTemplate) {
        System.out.println("Client id to delete: ");
        Scanner myscan = new Scanner(System.in);
        String clientID = myscan.nextLine();
        restTemplate.delete(URL2 + "/{id}", (long) Integer.parseInt(clientID));
        System.out.println("deleted");
    }

    private void updateClient(RestTemplate restTemplate) {
        System.out.println("Client id to update: ");
        Scanner myscan = new Scanner(System.in);
        String clientID = myscan.nextLine();

        System.out.println("New client Name=  ");
        String name = myscan.nextLine();
        System.out.println("New client amount=  ");
        String amount = myscan.nextLine();
        ClientDto c = new ClientDto(name, Integer.parseInt(amount));
        c.setId(Long.parseLong(clientID));

        restTemplate.put(URL2 + "/{id}", c, c.getId());
        System.out.println("UPDATED");
    }

    private void addClient(RestTemplate restTemplate) {
        Scanner myscan = new Scanner(System.in);
        System.out.println("Client Name=  ");
        String name = myscan.nextLine();
        System.out.println("Client amount=  ");
        String amount = myscan.nextLine();

        Client c = new Client(name, Integer.parseInt(amount));

        ClientDto savedclient = restTemplate.postForObject(
                URL2,
                c,
                ClientDto.class);
        System.out.println("saved: " + savedclient);

    }

    private int amountOfBooksBuyedByClient(Long id, RestTemplate restTemplate) {
        Set<OrderDto> orders1 = restTemplate.getForObject(URL3, OrdersDto.class).getOrders();
        return orders1.stream().filter(n -> n.getClientId() == id).mapToInt(o -> Math.toIntExact(1)).sum();
    }

    private void filterBook(RestTemplate restTemplate) {
        Set<BookDto> books = restTemplate.getForObject(URL, BooksDto.class).getBooks();
        Set<BookDto> books2 = books.stream().filter(x -> x.getBookPrice() > 50).collect(Collectors.toSet());
        books2.forEach(System.out::println);
    }

    private void deleteBook(RestTemplate restTemplate) {
        System.out.println("Book id to delete: ");
        Scanner myscan = new Scanner(System.in);
        String bookId = myscan.nextLine();

        restTemplate.delete(URL + "/{id}", (long) Integer.parseInt(bookId));
        System.out.println("deleted");

    }

    private void updateBook(RestTemplate restTemplate) {
        System.out.println("Book id to update: ");
        Scanner myscan = new Scanner(System.in);
        String bookId = myscan.nextLine();

        System.out.println("New book Name=  ");
        String name = myscan.nextLine();
        System.out.println("New book Author=  ");
        String author = myscan.nextLine();
        System.out.println("New book Price=  ");
        String price = myscan.nextLine();
        BookDto b = new BookDto(name, author, Integer.parseInt(price));
        b.setId(Long.parseLong(bookId));
        restTemplate.put(URL + "/{id}", b, b.getId());

    }

    private void addBook(RestTemplate restTemplate) {
        Scanner myscan = new Scanner(System.in);
        System.out.println("Book Name=  ");
        String name = myscan.nextLine();
        System.out.println("Book Author=  ");
        String author = myscan.nextLine();
        System.out.println("Book Price=  ");
        String price = myscan.nextLine();
        BookDto b = new BookDto(name, author, Integer.parseInt(price));
        BookDto savedbook = restTemplate.postForObject(
                URL,
                b,
                BookDto.class);
        System.out.println("saved: " + savedbook);
    }


    private void printAllBooks(RestTemplate restTemplate) {
        BooksDto allbooks = restTemplate.getForObject(URL, BooksDto.class);

        System.out.println(allbooks);
    }

    private void printAllClients(RestTemplate restTemplate) {
        ClientsDto allclients = restTemplate.getForObject(URL2, ClientsDto.class);

        System.out.println(allclients);
    }

    private void printAllOrders(RestTemplate restTemplate) {
        OrdersDto allorders = restTemplate.getForObject(URL3, OrdersDto.class);
        System.out.println(allorders);
    }

    private void printClientMenu() {
        System.out.println("1---Add Client");
        System.out.println("2---Print Clients");
        System.out.println("3---Update Client");
        System.out.println("4---Delete Client");
        System.out.println("5---Buy Books");
        System.out.println("6---Reports");
        System.out.println("7---Filter");
        System.out.println("0---Go Back!");
        System.out.println("Enter a number: ");
    }

    private void printBookMenu() {
        System.out.println("1---Add Book");
        System.out.println("2---Print Books");
        System.out.println("3---Update Book");
        System.out.println("4---Delete Book");
        System.out.println("5---Filter");
        System.out.println("0---Go Back!");
        System.out.println("Enter a number: ");
    }

    private void printMenu() {
        System.out.println("1---Work with Books");
        System.out.println("2---Work with Clients");
        System.out.println("3---Print Orders");
        System.out.println("0---For exit");
        System.out.println("Enter a number: ");
    }
}
