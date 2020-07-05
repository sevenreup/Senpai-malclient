package com.skybox.seven.senpai.api.jikan.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Anime implements Serializable {

    @SerializedName("mal_id")
    @Expose
    private Integer malId;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("synopsis")
    @Expose
    private String synopsis;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("airing_start")
    @Expose
    private String airingStart;
    @SerializedName("episodes")
    @Expose
    private Integer episodes;
    @SerializedName("members")
    @Expose
    private Integer members;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("producers")
    @Expose
    private List<Producer> producers = null;
    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("licensors")
    @Expose
    private List<Object> licensors = null;
    @SerializedName("r18")
    @Expose
    private Boolean r18;
    @SerializedName("kids")
    @Expose
    private Boolean kids;
    @SerializedName("continuing")
    @Expose
    private Boolean continuing;

    public Integer getMalId() {
        return malId;
    }

    public void setMalId(Integer malId) {
        this.malId = malId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAiringStart() {
        return airingStart;
    }

    public void setAiringStart(String airingStart) {
        this.airingStart = airingStart;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Producer> getProducers() {
        return producers;
    }

    public void setProducers(List<Producer> producers) {
        this.producers = producers;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public List<Object> getLicensors() {
        return licensors;
    }

    public void setLicensors(List<Object> licensors) {
        this.licensors = licensors;
    }

    public Boolean getR18() {
        return r18;
    }

    public void setR18(Boolean r18) {
        this.r18 = r18;
    }

    public Boolean getKids() {
        return kids;
    }

    public void setKids(Boolean kids) {
        this.kids = kids;
    }

    public Boolean getContinuing() {
        return continuing;
    }

    public void setContinuing(Boolean continuing) {
        this.continuing = continuing;
    }

}

