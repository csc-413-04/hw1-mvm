package simpleserver;

import java.util.HashMap;

public class Data  {
    User[] users = null;
    Posts[] posts = null;
    private  HashMap <String, User> userMap = new HashMap<>();
    private  HashMap <String, Posts> postMap = new HashMap<>();

    boolean isLoaded = false;
    public static Data data = new Data();

    public Data () {

    }

    public static Data getData() {
        return data;
    }

    public User[] getAllUsers() {
        return this.users;
    }

    public Posts[] getAllPosts() {
        return this.posts;
    }

}