package br.com.restassured.apiautomation.util;

import com.github.javafaker.Faker;

public class FakerUtil {

    private Faker faker;

    public FakerUtil(){

        this.faker = new Faker();
    }

    public String generateRandomName() {

        return faker.name().fullName();
    }

    public String generateRandomJob() {

        return faker.job().title();
    }

}
