package br.com.restassured.apiautomation.test.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:/features"},
        glue = {"br.com.restassured.apiautomation.test.step"},
        plugin = {"pretty", "json:target/cucumber/result.json"}
)
public class RunnerTest {
}
