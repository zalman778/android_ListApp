package com.hwx.listApplication.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmDetail that = (FilmDetail) o;
        return Objects.equals(backdropPath, that.backdropPath) &&
                Objects.equals(posterPath, that.posterPath) &&
                Objects.equals(title, that.title) &&
                Objects.equals(originalTitle, that.originalTitle) &&
                Objects.equals(overview, that.overview) &&
                Objects.equals(popularity, that.popularity) &&
                Objects.equals(budget, that.budget) &&
                Objects.equals(releaseDate, that.releaseDate) &&
                Objects.equals(voteAverage, that.voteAverage) &&
                Objects.equals(voteCount, that.voteCount) &&
                Objects.equals(homepage, that.homepage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(backdropPath, posterPath, title, originalTitle, overview, popularity, budget, releaseDate, voteAverage, voteCount, homepage);
    }
}
