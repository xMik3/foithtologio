package client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static Retrofit client;
    private static String token;

    public static void setToken(String token) {
        ApiClient.token = token;
    }

    public static String getToken() {
        return token;
    }

    public static Retrofit getClient(){
        if(client == null){
            ApiClient.client = new Retrofit.Builder().baseUrl("http://localhost:3000").addConverterFactory(GsonConverterFactory.create()).build();
        }

        return client;
    }

}
