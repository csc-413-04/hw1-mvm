package simpleserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Posts extends Data{
    // Use Java generics to avoid linear searching for users by id, makes it O(1) instead of O(n)
    private  static Map<Integer, Posts> postidDict = new HashMap<>();
    private static ArrayList<Posts> allPosts = new ArrayList<>();

    public void setUserid(int userid) {
        this.userid = userid;
        System.out.println(userid);
    }

    private int userid;

    public void setPostid(int postid) {
        this.postid = postid;
        System.out.println(postid);
    }

    private int postid;

    public void setData(String data) {
        this.data = data;
        System.out.println(data);
    }

    private String data;

    public Posts(){
        allPosts.add(this);
    }

    public Posts(int userid, int postid, String data){
        this.userid = userid;
        this.postid = postid;
        this.data = data;
        allPosts.add(this);
        postidDict.put(postid, this);
    }

    public static Posts getUser(int userid){
        return postidDict.get(userid);
    }

    public static Posts getPost(int postid){
        return postidDict.get(postid);
    }

    public static Posts getData(String data){
        return postidDict.get(data);
    }

    public void register(){
        postidDict.put(postid, this);
    }

    public static void loadAll(){
        for(int i = 0 ; i < allPosts.size(); i++){
            allPosts.get(i).register();
        }
    }
}
