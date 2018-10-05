package simpleserver;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

class SimpleServer {

    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("src/simpleserver/data.json"));
            //System.out.println("works for reading");
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

        try {
            //  http://localhost:8080
            ding = new ServerSocket(8080);
            System.out.println("Opened socket " + 8080);
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

                    //create URL object
                    //URL myURL = new URL ("http://localhost:8080/user");
                    URL myURL = new URL ("http://localhost:8080/user?userid=7");
                    //URL myURL = new URL ("http://localhost:8080/user?userid=7&username=7");

                    //URL myURL = new URL ("http://localhost:8080/posts");
                    //URL myURL = new URL ("http://localhost:8080/posts?postid=7");
                    //URL myURL = new URL ("http://localhost:8080/posts?postid=7&maxlength=12");

                    String endpoint = myURL.getPath();
                    String parameter = myURL.getQuery();

                    // Testing print the endpoint
                    System.out.println("\t\t\tENDPOINT: " + endpoint);

                    switch(endpoint){
                        case "/posts":
                            if (parameter == null) {
                                System.out.println("\t\t\tERROR! Need parameters!");
                            } else {
                                String param[] = myURL.getQuery().split("&");
                                if(param.length == 1){
                                    System.out.println("\t\t\tPARAM: " + param[0]);
                                }else if (param.length == 2) {
                                    // Testing print parameters
                                    System.out.println("\t\t\tPARAM1: " + param[0]);
                                    System.out.println("\t\t\tPARAM2: " + param[1]);
                                }
                            }
                            break;
                        case "/user":
                            if(parameter == null){
                                System.out.println("\t\t\tReturn all users");
                                for (int i = 0; i <= 50; i++) {
                                    System.out.println(gson.toJson(User.getUser(i)));
                                }
                            } else if (parameter.contains("&")) {
                                System.out.println("\t\t\tNot valid parameter!");
                            } else
                            {
                                System.out.println("\t\t\tPARAM: "+ parameter);
                                System.out.println("\t\t\tReturn user obj by ID");
                                String userIDval = parameter.substring(7);
                                int value = Integer.parseInt(userIDval);
                                System.out.println("\t\t\tUSERID NUM: " + value);
                                System.out.println("\t\t\t" + gson.toJson(User.getUser(value)));
                            }
                            break;
                        default:
                            System.out.println("\t\t\tError");
                    }

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
                writer.println("<h1>{ </h1>");
                writer.println("<h1>  Status: </h1>");
                writer.println("<h1>  Entries: </h1>");
                writer.println("<h1>  Data: [ </h1>");
                writer.println("<h1>} postid: </h1>");
                writer.println("<h1>} userid: </h1>");
                writer.println("<h1>} data </h1>");


                dong.close();
            }
        } catch (IOException e) {
            System.out.println("Error opening socket");
            System.exit(1);
        }
    }
}
