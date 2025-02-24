module greeter.friendly {
    requires greeter.api;
    provides com.ldaine.greet.api.MessageService with com.ldaine.greet.friend.service.FriendMessageService;
}
