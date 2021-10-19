package com.example.dog_advance_api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Dog_Api {
    String BASE_URL = "https://dog.ceo/api/breed/";

    //Base_Url er porer ongsho jabe @GET("") er vitore jemon full url er seshe ee eikane "random" chilo... tai random dhukse
    @GET("images/random")
    Call<DogClass> getDogImage(); // getDogImage return korbe ekta "DogClass" object... majhe majhe eikane "<list<DogClass>>"  list
}
