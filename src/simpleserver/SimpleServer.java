package simpleserver;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.*;
import java.net.*;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class SimpleServer {

  public static String getUrl(String urls){
      StringBuilder url = new StringBuilder();
      try {
      URL url1 = new URL(urls);
      URLConnection urlConnect = url1.openConnection();
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnect.getInputStream()));
      String line1;
      // read from the urlconnect via the bufferedreader
      while ((line1 = bufferedReader.readLine()) != null) {
        url.append(line1 + "\n");
      }
      bufferedReader.close();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(url);
    return url.toString();
  }

  public static void main(String[] args) throws IOException {
    Gson gson = new Gson();
    BufferedReader br;
    try {
      br = new BufferedReader(new FileReader("src/simpleserver/data.json"));
      System.out.println("works for reading");
      //parsing is reading data here
      JsonParser jsonParser = new JsonParser();
      JsonObject obj = jsonParser.parse(br).getAsJsonObject();
      //getting users from the file and saving them into an array
      User[] users = gson.fromJson(obj.get("users"), User[].class);
      User.loadAll();
      //getting posts from the file and saving them into an array
      Posts[] posts = gson.fromJson(obj.get("posts"), Posts[].class);
      Posts.loadAll();
      //System.out.println(obj.get("users"));

      Response response = new Response();

//      String jsonString1 = gson.toJson(User.getUser(0));
//      String jsonString2 = gson.toJson(Posts.getPost(0));
//      String jsonString3 = gson.toJson(User.getUser(2));
//      String jsonString4 = gson.toJson(Posts.getPost(3));
//
//      System.out.println(jsonString1);
//      System.out.println(jsonString2);
//      System.out.println(jsonString3);
//      System.out.println(jsonString4);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    ServerSocket ding;
    Socket dong = null;
    String resource = null;

    try {
      ding = new ServerSocket(1301);
      System.out.println("Opened socket " + 1301);
      while (true) {

        // keeps listening for new clients, one at a time
        try {
          dong = ding.accept(); // waits for client here
        } catch (IOException e) {
          System.out.println("Error opening socket");
          System.exit(1);
        }

        InputStream stream = dong.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        try {

          // read the first line to get the request method, URI and HTTP version
          String line = in.readLine();
          System.out.println("----------REQUEST START---------");
          System.out.println(line);
          //create an array to store the whole request into sections using space as a delimiter
          String[] lineArray = line.split(" ");
          //save the second section into string -- the url
          String requestUrl = lineArray[1];

          //java URL
          //String uRL  = getUrlContents("http://127.0.0.1:1301/");
          String uRL  = getUrl(requestUrl);
          System.out.println(uRL);

          // read only headers
          line = in.readLine();
          while (line != null && line.trim().length() > 0) {
            int index = line.indexOf(": ");
            if (index > 0) {
              System.out.println(line);
            } else {
              break;
            }
            line = in.readLine();
          }
          System.out.println("----------REQUEST END---------\n\n");
        } catch (IOException e) {
          System.out.println("Error reading");
          System.exit(1);
        }

        BufferedOutputStream out = new BufferedOutputStream(dong.getOutputStream());
        PrintWriter writer = new PrintWriter(out, true);  // char output to the client

        // every response will always have the status-line, date, and server name
        writer.println("HTTP/1.1 200 OK");
        writer.println("Server: TEST");
        writer.println("Connection: close");
        writer.println("Content-type: text/html");
        writer.println("");

        // Body of our response
        writer.println("<h1>Some response!</h1>");

        dong.close();
      }
    } catch (IOException e) {
      System.out.println("Error opening socket");
      System.exit(1);
    }
  }
}
