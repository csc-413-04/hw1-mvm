package simpleserver;
//interface iData {
//
//}
//
//class user implements iData {
//
//}
//
//class Post implements iData {
//
//}
//
//public class Response {
//    User[] users;
//    Posts[] posts;
//    public String status;
//    public int entries;
//    iData[] data;
//}



class Response {
    User[] users;
    Posts[] posts;

    public void setUsers(User[] users) {
        this.users = users;
    }

    public void setPosts(Posts[] posts) {
        this.posts = posts;
    }
}