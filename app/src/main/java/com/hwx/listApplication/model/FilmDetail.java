package com.hwx.listApplication.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FilmDetail implements Serializable {

    @SerializedName("backdrop_path") public String backdropPath;

    @SerializedName("poster_path") public String posterPath;

    //in card:

    @SerializedName("title") public String title;

    @SerializedName("original_title") public String originalTitle;

    @SerializedName("overview") public String overview;

    @SerializedName("popularity") public Double popularity;

    @SerializedName("budget") public Long budget;

    @SerializedName("release_date") public String releaseDate;

    @SerializedName("vote_average") public Double voteAverage;

    @SerializedName("vote_count") public Integer voteCount;

    @SerializedName("homepage") public String homepage;
}
