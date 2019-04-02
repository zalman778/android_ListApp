package com.hwx.listApplication;

public class Configuration {
    public static String API_KEY = BuildConfig.ApiKey;
    public static String BASE_API_URL = "https://api.themoviedb.org/3/";

    public static String getBaseUrlList(Integer page) {
        if (page == null)
            page = 1;
        return "https://api.themoviedb.org/3/discover/movie?api_key="+API_KEY+"&language=ru-RU&sort_by=popularity.desc&include_adult=false&include_video=false&page="+page+"/";
    }

    public static String getMovieFullInfoUrl(Long id) {
        if (id == null)
            id = 1L;
        return "https://api.themoviedb.org/3/movie/"+id+"?api_key="+API_KEY+"&language=ru-RU/";
    }

    public static String getImageFullUrl(String imageHash) {
        return "https://image.tmdb.org/t/p/w600_and_h900_bestv2"+imageHash;
    }
}
