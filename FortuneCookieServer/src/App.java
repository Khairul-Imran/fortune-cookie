import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main(String[] args) {
        
        try (ServerSocket ServerSocket = new ServerSocket(5500)) {
            Socket socket = ServerSocket.accept();
            System.out.println("Client Connected.");

            Cookie fortuneCookie = new Cookie();
            fortuneCookie.openCookieJar();
            
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);


            while (true) {
                String clientString = input.readLine();
                if (clientString.equals("close")) {
                    System.out.println("Cookie jar is closed.");
                    break;
                }
                if (clientString.equals("get-cookie")) {
                    // Send the cookie.
                    output.println(fortuneCookie.getCookie());
                }
            }



        } catch(IOException e) {
            System.out.println("Server exception " + e.getMessage()); // Might change this.
        }

    }
}
