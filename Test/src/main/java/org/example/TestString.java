package org.example;

public class TestString {
    public static void main(String[] args) {
        String head = "hello.txt";
        String i = head.substring(head.lastIndexOf("."), head.length());
        System.out.println (i);
    }
}
