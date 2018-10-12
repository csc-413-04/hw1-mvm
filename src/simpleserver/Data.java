package simpleserver;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class Data {
    User[] users;
    //private HashMap<String, User> userMap = new HashMap<>();
    Posts[] posts = null;
    //private HashMap<String, Posts> postMap = new HashMap<>();
    boolean isLoaded = false;
    public static Data data = new Data();

    public Data(){
//        this.getAllUsers();
//        this.getAllPosts();
//        connect();
    }

    public static Data getData(){
        return data;
    }

    public User[] getAllUsers(){
        if (!data.isLoaded) data.connect();
        return this.users;
    }

    public static User getUser1(int userId){
        return User.getUser(userId);
    }

    public Posts[] getAllPosts(){
        if (!data.isLoaded) data.connect();
        return this.posts;
    }

    public static Posts getPost(int postId){
        return Posts.getPost(postId);
    }

    void connect() {
        isLoaded = true;
        Gson gson = new Gson();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("src/data.json"));
            JsonParser jsonParser = new JsonParser();
            JsonObject obj = jsonParser.parse(br).getAsJsonObject();

            users = gson.fromJson(obj.get("users"), User[].class);
            posts = gson.fromJson(obj.get("posts"), Posts[].class);
            //.loadAll();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}





