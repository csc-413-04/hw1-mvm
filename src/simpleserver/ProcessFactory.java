package simpleserver;

import javax.annotation.processing.Processor;

public class ProcessFactory {

    static ServerProcessor getProcessor(String url) {
        String[] request = url.split("\\?");
        String endpoint = request[0];
        //String[] parameters = request[1].split("&");

        //System.out.println("ENDPOINT: "+endpoint + "\n" + parameters[0] + "\n" + parameters[1]);

        ServerProcessor processor = null;

        switch(endpoint) {
            case "/user":
                //processor = new UserProcessor();
                return new UserProcessor();
            case "/posts":
                processor = new PostProcessor();
                return new PostProcessor();
        }

        return processor;
        //return null;
    }
}
