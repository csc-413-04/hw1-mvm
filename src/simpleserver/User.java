package simpleserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User implements  Data{
    // Use Java generics to avoid linear searching for users by id, makes it O(1) instead of O(n)
    private  static Map<Integer, User> useridDict = new HashMap<>();
    private static ArrayList<User> allUsers = new ArrayList<>();

    public void setUsername(String username) {
        this.username = username;
        System.out.println(username);
    }

    private  String username;

    public void setUserid(int userid) {
        this.userid = userid;
        System.out.println(userid);
    }

    private  int userid;

    public User(){
        allUsers.add(this);
    }

    public User(String username, int userid){
        this.username = username;
        this.userid = userid;
        allUsers.add(this);
        useridDict.put(userid, this);
    }

    public static User getUser(int userid){
        return useridDict.get(userid);
    }

    public void register(){
        useridDict.put(userid, this);
    }

    public static void loadAll(){
        for(int i = 0 ; i < allUsers.size(); i++) {
            allUsers.get(i).register();
        }
    }
}
