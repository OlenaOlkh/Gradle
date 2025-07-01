package stepdefs;

import static com.codeborne.selenide.Selenide.sleep;

import io.cucumber.java.en.When;

public class BaseStepDefs {

    @When("^wait for ([\\d]+) sec(?:s|)$")
    public void wait(int sec) {
        sleep(sec * 1000L);
    }

}