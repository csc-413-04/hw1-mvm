package simpleserver;

import javax.annotation.processing.Processor;

public class ProcessFactory {

    static ServerProcessor getProcessor(String url) {
        String[] request = url.split("\\?");
        String endpoint = request[0];
        String[] parameters = request[1].split("&");

        //System.out.println("ENDPOINT: "+endpoint + "\n" + parameters[0] + "\n" + parameters[1]);


        ServerProcessor processor = null;


        switch(endpoint) {
            case "/users":
                return new UserProcessor(parameters);
            case "/posts":
                return new PostProcessor(parameters);
        }

       //processor = new UserProcessor(parameters);

       return processor;
       //return null;
    }
}
