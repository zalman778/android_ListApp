package com.hwx.listApplication.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FilmSimple implements Serializable {
    @SerializedName("id") public Long id;

    @SerializedName("vote_average") public Double voteAverage;

    @SerializedName("title") public String title;

    @SerializedName("popularity") public Double popularity;

    @SerializedName("poster_path") public String posterPath;

}
