package com.ldaine.javamodularity;

import com.ldaine.hellogreeter.service.HelloMessageService;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Greeter CLI");
        var service = new HelloMessageService();
        service.sayHello();
    }
}