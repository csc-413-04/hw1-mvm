package simpleserver;

import com.google.gson.Gson;

public class UserProcessor implements ServerProcessor{
    public UserProcessor() {

    }

    @Override
    public String process(String url) {
        Response response = new Response();
        //Data da = Data.getData();
        //response.setPost(Posts.getPost(da));
        //response.setStatus("OK");
        Gson gson = new Gson();

        String[] request = url.split("\\?");
        System.out.println("ENDPOINT: " + url);
        String endpoint = request[0];
        //System.out.println(request[0] + "Param: " + request[1]);
        String[] parameters = request[1].split("&");
        //String[] parameters = request[1].split("&");


        if(parameters == null){
            System.out.println("\t\t\tReturn all users");
            response.setStatus("OK");
            User.loadAll();

            //response.setData();
            //response.setData();
//          for (int i = 0; i <= 50; i++) {
//              System.out.println(gson.toJson(User.getUser(i)));
//          }

        } else if (parameters.length == 2) {
            System.out.println("\t\t\tNot valid parameter!");
            response.setStatus("Error");

        } else {
            System.out.println("\t\t\tPARAM: "+ parameters[0]);
            System.out.println("\t\t\tReturn user obj by ID");
            String userIDval = parameters[0].substring(7);
            int value = Integer.parseInt(userIDval);
            System.out.println("\t\t\tUSERID NUM: " + value);
            //System.out.println("\t\t\t" + gson.toJson(User.getUser(value)));
            response.setStatus("OK");
        }
        return gson.toJson(response);
        //return null;
    }
}
