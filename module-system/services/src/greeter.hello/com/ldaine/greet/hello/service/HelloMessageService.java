package com.ldaine.greet.hello.service;

import com.ldaine.greet.api.MessageService;

import com.ldaine.greet.hello.util.StringUtils;

public class HelloMessageService implements MessageService {
    public String getMessage(){
        return StringUtils.capitalize("hello");
    }
}
