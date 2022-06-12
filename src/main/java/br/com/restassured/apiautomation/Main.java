package br.com.restassured.apiautomation;

import br.com.restassured.apiautomation.model.request.user.UserRequest;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<User> list = new ArrayList<>();

        User user1 = new User();
        user1.setJob("Job1");
        user1.setName("Name1");

        User user2 = new User();
        user2.setJob("Job2");
        user2.setName("Name2");

        list.add(user1);
        list.add(user2);

        User test = getByName(list, "Name2");
        if(test != null)
            System.out.println(test.getJob());
        else
            System.out.println("NAO TEM");

    }

    public static User getByName(List<User> list, String name) {
        return list.stream().filter(user -> user.getName().equals(name)).findFirst().orElse(null);
    }
}
