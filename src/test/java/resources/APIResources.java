package resources;
//Enum is a speciall class in java which has collection of constants and methods
public enum APIResources {
    AddPlaceAPI("/maps/api/place/add/json"),
    getAddPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");
    private String resource;

    APIResources(String resource){
        this.resource=resource;
    }

    public String getResource(){
        return resource;
    }
}
