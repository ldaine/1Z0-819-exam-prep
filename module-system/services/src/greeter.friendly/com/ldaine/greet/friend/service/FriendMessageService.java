package com.ldaine.greet.friend.service;

import com.ldaine.greet.api.MessageService;

public class FriendMessageService implements MessageService {
    public String getMessage() {
        return "Friendly greetings";
    }
}
