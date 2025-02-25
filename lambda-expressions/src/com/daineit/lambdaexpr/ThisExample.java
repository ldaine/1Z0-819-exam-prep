package com.daineit.lambdaexpr;

public class ThisExample {
    private String message = "Lambda expressions can access class fields of enclosing context/class using 'this.'. Same goes with 'super.'";

    public void printMessage(){
        Runnable runnable = () -> System.out.println(this.message);
        new Thread(runnable).start();
    }
}
