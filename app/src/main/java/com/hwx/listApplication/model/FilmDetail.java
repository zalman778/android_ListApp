package com.hwx.listApplication.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

public class FilmDetail implements Serializable {

    @SerializedName("backdrop_path") private String backdropPath;

    @SerializedName("poster_path") private String posterPath;

    //in card:

    @SerializedName("title") private String title;

    @SerializedName("original_title") private String originalTitle;

    @SerializedName("overview") private String overview;

    @SerializedName("popularity") private Double popularity;

    @SerializedName("budget") private Long budget;

    @SerializedName("release_date") private String releaseDate;

    @SerializedName("vote_average") private Double voteAverage;

    @SerializedName("vote_count") private Integer voteCount;

    @SerializedName("homepage") private String homepage;

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

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
