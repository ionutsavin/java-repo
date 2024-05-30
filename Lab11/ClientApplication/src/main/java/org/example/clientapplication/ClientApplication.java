package org.example.clientapplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {

        String baseUrl = "http://localhost:8080";

        WebClient.Builder builder = WebClient.builder();

        testGetAllBooks(builder, baseUrl);

        testAddBook(builder, baseUrl);

        testUpdateBook(builder, baseUrl);

        testDeleteBook(builder, baseUrl);
    }

    private static void testGetAllBooks(WebClient.Builder builder, String baseUrl) {
        String url = baseUrl + "/api/books";
        String response = makeRequest(builder, url);
        System.out.println("GET All Books Response:");
        System.out.println(response);
    }

    private static void testAddBook(WebClient.Builder builder, String baseUrl) {
        String url = baseUrl + "/api/books/add_book";
        Book book = new Book("White Noises", 1985);
        String response = makePostRequest(builder, url, book);
        System.out.println("POST Add Book Response:");
        System.out.println(response);
    }

    private static void testUpdateBook(WebClient.Builder builder, String baseUrl) {
        String url = baseUrl + "/api/books/update_book/137";
        Book book = new Book("Lucky");
        String response = makePutRequest(builder, url, book);
        System.out.println("PUT Update Book Response:");
        System.out.println(response);
    }

    private static void testDeleteBook(WebClient.Builder builder, String baseUrl) {
        String url = baseUrl + "/api/books/delete_book/137";
        String response = makeDeleteRequest(builder, url);
        System.out.println("DELETE Book Response:");
        System.out.println(response);
    }

    private static String makeRequest(WebClient.Builder builder, String url) {
        return builder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private static String makePostRequest(WebClient.Builder builder, String url, Book requestBody) {
        return builder.build()
                .post()
                .uri(url)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private static String makePutRequest(WebClient.Builder builder, String url, Book requestBody) {
        return builder.build()
                .put()
                .uri(url)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private static String makeDeleteRequest(WebClient.Builder builder, String url) {
        return builder.build()
                .delete()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
