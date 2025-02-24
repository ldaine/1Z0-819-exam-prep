package com.ldaine.javaservices;

import java.util.ServiceLoader;

import com.ldaine.greet.api.MessageService;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Greeter CLI");
        Iterable<MessageService> services = ServiceLoader.load(MessageService.class);
        for(MessageService service: services){
            String message = service.getMessage();
            System.out.println(message);
        }
    }
}