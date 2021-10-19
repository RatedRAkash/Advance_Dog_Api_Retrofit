package com.example.dog_advance_api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Breed_Api {

    String BASE_URL = "https://dog.ceo/api/breeds/list/";

    //Base_Url er porer ongsho jabe @GET("") er vitore jemon full url er seshe ee eikane "random" chilo... tai random dhukse
    @GET("all")
    Call <BreedClass> getBreed(); // getDogImage return korbe ekta "DogClass" object... majhe majhe eikane "<list<DogClass>>"  list of DogClass return korte pare...

}

