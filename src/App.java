import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        Properties props = new Properties();
        props.load(new FileInputStream("config.properties"));
      
        String apiKey = props.getProperty("api.key");    
        String url = "https://imdb-api.com/en/API/Top250Movies/" + apiKey;
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

    }
}
