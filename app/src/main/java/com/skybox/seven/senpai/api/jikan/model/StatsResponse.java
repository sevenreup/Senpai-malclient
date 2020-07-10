package com.skybox.seven.senpai.api.jikan.model;

import java.util.ArrayList;
import java.util.List;

public class StatsResponse {

    private String requestHash;
    private Boolean requestCached;
    private Integer requestCacheExpiry;
    private Integer watching;
    private Integer completed;
    private Integer onHold;
    private Integer dropped;
    private Integer planToWatch;
    private Integer total;
    private List<Score> scoreList = new ArrayList<>();

    public String getRequestHash() {
        return requestHash;
    }

    public void setRequestHash(String requestHash) {
        this.requestHash = requestHash;
    }

    public Boolean getRequestCached() {
        return requestCached;
    }

    public void setRequestCached(Boolean requestCached) {
        this.requestCached = requestCached;
    }

    public Integer getRequestCacheExpiry() {
        return requestCacheExpiry;
    }

    public void setRequestCacheExpiry(Integer requestCacheExpiry) {
        this.requestCacheExpiry = requestCacheExpiry;
    }

    public Integer getWatching() {
        return watching;
    }

    public void setWatching(Integer watching) {
        this.watching = watching;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Integer getOnHold() {
        return onHold;
    }

    public void setOnHold(Integer onHold) {
        this.onHold = onHold;
    }

    public Integer getDropped() {
        return dropped;
    }

    public void setDropped(Integer dropped) {
        this.dropped = dropped;
    }

    public Integer getPlanToWatch() {
        return planToWatch;
    }

    public void setPlanToWatch(Integer planToWatch) {
        this.planToWatch = planToWatch;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Score> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Score> scoreList) {
        this.scoreList = scoreList;
    }

    public static class Score {
        private String score;
        private Integer votes;
        private Double percentage;

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public Integer getVotes() {
            return votes;
        }

        public void setVotes(Integer votes) {
            this.votes = votes;
        }

        public Double getPercentage() {
            return percentage;
        }

        public void setPercentage(Double percentage) {
            this.percentage = percentage;
        }

    }

}