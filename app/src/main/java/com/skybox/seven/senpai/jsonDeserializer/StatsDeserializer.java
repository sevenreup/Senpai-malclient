package com.skybox.seven.senpai.jsonDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.skybox.seven.senpai.api.jikan.model.StatsResponse;

import java.lang.reflect.Type;

public class StatsDeserializer implements JsonDeserializer<StatsResponse> {
    @Override
    public StatsResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        StatsResponse response = new StatsResponse();

        response.setRequestHash(object.get("request_hash").getAsString());
        response.setRequestCached(object.get("request_cached").getAsBoolean());
        response.setRequestCacheExpiry(object.get("request_cache_expiry").getAsInt());
        response.setWatching(object.get("watching").getAsInt());
        response.setCompleted(object.get("completed").getAsInt());
        response.setOnHold(object.get("on_hold").getAsInt());
        response.setDropped(object.get("dropped").getAsInt());
        response.setPlanToWatch(object.get("plan_to_watch").getAsInt());
        response.setTotal(object.get("total").getAsInt());

        JsonObject scoresObj = object.getAsJsonObject("scores");
        for (String key: scoresObj.keySet()) {
            JsonObject object1 = scoresObj.getAsJsonObject(key);
            StatsResponse.Score score = new StatsResponse.Score();
            score.setVotes(object1.get("votes").getAsInt());
            score.setPercentage(object1.get("percentage").getAsDouble());
            score.setScore(key);
            response.getScoreList().add(score);
        }


        return response;
    }
}
