package simpleserver;

import javax.annotation.processing.Processor;

public class ProcessorFactory {
    public static Processor makeProcessor(String request, String[] args){
        switch(request) {
            case "/users":
                return (Processor) new UserProcessor(args);
            //break;
            case "/posts":
                return (Processor) new PostProcessor(args);
            //break;
        }
        return null;
    }
}
