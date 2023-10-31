import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 5500)) {
            BufferedReader cookieToReceive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter clientCommandToSend = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String cookieCommand;
            String cookieResponse;

            do {
                System.out.println("Enter a command for the fortune cookie jar: ");
                cookieCommand = scanner.nextLine();

                clientCommandToSend.println(cookieCommand); // Sends to the server.
                if (!cookieCommand.equals("close")) {
                    cookieResponse = cookieToReceive.readLine();

                    String strippedResponse = cookieResponse.substring("cookie-text ".length());
                    System.out.println(strippedResponse);
                }
            } while (!cookieCommand.equals("close"));

            scanner.close();

        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage()); // Might change this.
        }
    }
}
