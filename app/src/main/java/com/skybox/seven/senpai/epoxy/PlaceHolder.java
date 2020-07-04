package com.skybox.seven.senpai.epoxy;

import android.content.Context;

import com.airbnb.epoxy.CarouselModel_;
import com.airbnb.epoxy.Typed2EpoxyController;
import com.skybox.seven.senpai.api.jikan.model.Anime;
import com.skybox.seven.senpai.epoxy.anime.AnimeOneModel_;
import com.skybox.seven.senpai.epoxy.home.CarouselOneHomeModelModel_;

import java.util.ArrayList;
import java.util.List;

public class PlaceHolder extends Typed2EpoxyController<Boolean, List<Anime>> {
    private Context context;

    public PlaceHolder(Context context) {
        this.context = context;
    }

    @Override
    protected void buildModels(Boolean data1, List<Anime> data2) {
        List<AnimeOneModel_> model_s = new ArrayList<>();

        for (Anime anime:
             data2) {
            model_s.add(
                  new AnimeOneModel_().id(anime.getMalId()).image(anime.getImageUrl()).context(context)
            );
        }

        new CarouselModel_().id("st").models(model_s).addTo(this);
    }
}
