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
    Data [] data;

    String status;
    int entries;

    public void setData(Data[] data) {
        this.data = data;
    }
}