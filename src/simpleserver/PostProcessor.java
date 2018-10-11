package simpleserver;

import com.google.gson.Gson;

public class PostProcessor implements ServerProcessor {
    String response;
    public PostProcessor(String[] args) {

    }
    @Override
    public String process(String url) {
        //System.out.println("HELLFIOHFWJHIUFKJEGIEKJ: " + url);
        String[] request = url.split("\\?");
        String endpoint = request[0];
        String[] parameters = request[1].split("&");
        //System.out.println("EMPORGHR: " + endpoint + "\nPARMKEHFURK: " + parameters[0]);
        if (parameters == null) {
            System.out.println("\t\t\tERROR! Need parameters!");
        } else {
            //String param[] = myURL.getQuery().split("&");
            if(parameters.length == 1){
                System.out.println("\t\t\tPARAM: " + parameters[0]);
                String postIDval = parameters[0].substring(7);
                int value = Integer.parseInt(postIDval);
                System.out.println("\t\t\tPOSTID NUM: " + value);
                //System.out.println("\t\t\t" + gson.toJson(Posts.getPost(value)));
            }else if (parameters.length == 2) {
                // Testing print parameters
                System.out.println("\t\t\tPARAM1: " + parameters[0]);
                System.out.println("\t\t\tPARAM2: " + parameters[1]);
                String postIDval = parameters[0].substring(7);
                String lengthVal = parameters[1].substring(10);
                int value1 = Integer.parseInt(postIDval);
                int value2 = Integer.parseInt(lengthVal);
                // int length = gson.toJson(Posts.getData());
                System.out.println("\t\t\tPOST ID: " + value1 + "\n\t\t\tLENGTH: " + value2);
                //System.out.println("\t\t\t" + gson.toJson(Posts.getPost(value1)));

            }
        }
        return response;
    }
}
