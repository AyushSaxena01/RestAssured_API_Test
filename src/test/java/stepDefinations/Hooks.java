package stepDefinations;

import io.cucumber.java.Before;

import java.io.IOException;


public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
//Run only when place id is null

        StepDefination stepDefination = new StepDefination();

        if(StepDefination.place_id==null ){
            stepDefination.add_place_payload("Bhopal", "Hindi", "New Market");
            stepDefination.user_calls_with_http_request("AddPlaceAPI", "POST");
            stepDefination.verify_place_id_created_maps_to_using_get_place_api("Bhopal", "getAddPlaceAPI");
        }
    }
}
