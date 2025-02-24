module greeter.hello {
    requires greeter.api;
    provides com.ldaine.greet.api.MessageService with com.ldaine.greet.hello.service.HelloMessageService;
}
