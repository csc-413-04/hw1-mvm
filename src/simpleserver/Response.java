package simpleserver;

class Response {
    Data [] data;

    String status;
    int entries;

    public void setData(Data[] data) {

        this.data = data;
        this.entries = data.length;
    }

    public void setStatus(String status){
        this.status = status;
    }
}