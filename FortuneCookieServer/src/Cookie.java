import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cookie {
  private String cookieFile = "cookie_file.txt";
  private List<String> cookieCollection;

  public Cookie() {
    cookieCollection = new ArrayList<>();
  }

  public void openCookieJar() {
    // Reads all the cookies in the cookie file and stores them in the ArrayList.
    try(BufferedReader cookieReader = new BufferedReader(new FileReader(cookieFile))) {
      String cookie;
      while ((cookie = cookieReader.readLine()) != null) { // How does it go to the next line?
        cookieCollection.add(cookie);
      }
      System.out.println("Fortune cookies are ready to be chosen.");
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (cookieCollection.isEmpty()) {
      System.out.println("No cookies were found in the jar.");
    }
  }

  public String getCookie() {
    // Generating random number.
    Random random = new Random();
    int randomNumber = random.nextInt(cookieCollection.size());

    // Gets the random cookie.
    String randomCookie = cookieCollection.get(randomNumber);
    System.out.println("cookie-text " + randomCookie); // Might change.
    return "cookie-text " + randomCookie;
  }
}
