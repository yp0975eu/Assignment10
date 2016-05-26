package com.branden;

import com.google.gson.Gson;
import okhttp3.ResponseBody;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for interacting with Open Movie Database http://omdbapi.com/
 */
public class Omdb {
    private String baseURL="http://www.omdbapi.com";
    private Retrofit retrofit;
    private OmdbService service;
    Omdb(){
        // instantiate new retrofit class with our base url for omdbapi.com
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(OmdbService.class);

    }
    public interface OmdbService{
        @GET("/")
        Call<Gson> getMovie(@QueryMap Map<String, String> options);

    }
    public void getMovieByName(String movieName){
        // create map to pass to parameters to getMovie
        // hash map pairs aren't guaranteed to remain in order of placement
        HashMap<String, String> options = new HashMap<>();
        options.put("t",movieName);
        options.put("r","json");
        options.put("y","");
        options.put("plot","short");

        Call<Gson> responseBodyCall =  service.getMovie( options);
        try {

            Response<Gson> response = responseBodyCall.execute();
            if( !response.isSuccessful() ) {
                System.out.println(response.code());
                System.out.println(response.raw());
                System.out.println(response.message());
              } else {
                System.out.println(response.raw());
                // mystery output
                //Gson body = response.body();
            }
        } catch (IOException ex){
            System.out.println(ex); ex.printStackTrace();
        }

    }

}


