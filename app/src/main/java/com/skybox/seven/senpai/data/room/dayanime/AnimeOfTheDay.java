package com.skybox.seven.senpai.data.room.dayanime;

import androidx.room.PrimaryKey;

public class AnimeOfTheDay {
    @PrimaryKey
     public Integer malId;
     public String url;
     public String title;
     public String imageUrl;
     public String synopsis;
     public String type;
     public String airingStart;
     public Integer episodes;
     public String source;
     public Double score;
     public Boolean r18;
     public Boolean kids;
     public Boolean continuing;


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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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
