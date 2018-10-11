package simpleserver;

import com.google.gson.Gson;

public class UserProcessor implements ServerProcessor{
    public UserProcessor(String[] args) {

    }

    @Override
    public String process(String url) {
        Response response = new Response();
        //Data da = Data.getData();
        //response.setData(da.get);
        response.setStatus("OK");
        Gson gson = new Gson();


        String[] request = url.split("\\?");
        String endpoint = request[0];
        String[] parameters = request[1].split("&");
        if(parameters == null){
            System.out.println("\t\t\tReturn all users");
//          for (int i = 0; i <= 50; i++) {
//              System.out.println(gson.toJson(User.getUser(i)));
//          }
        } else if (parameters.length == 2) {
            System.out.println("\t\t\tNot valid parameter!");
        } else {
            System.out.println("\t\t\tPARAM: "+ parameters[0]);
            System.out.println("\t\t\tReturn user obj by ID");
            String userIDval = parameters[0].substring(7);
            int value = Integer.parseInt(userIDval);
            System.out.println("\t\t\tUSERID NUM: " + value);
            //System.out.println("\t\t\t" + gson.toJson(User.getUser(value)));
        }
        return gson.toJson(response);
        //return null;
    }
}
