package com.skybox.seven.customviews.FadeImage;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.skybox.seven.customviews.R;

public class FadeEdgeImage extends androidx.appcompat.widget.AppCompatImageView {
    private int mFadeSide;

    private Context c;

        private static final int DOWN_SIDE = 0, UP_SIDE = 1;


    public FadeEdgeImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.c = context;

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FadeEdgeImage, 0, 0);
        mFadeSide = a.getInteger(R.styleable.FadeEdgeImage_edgeDirection, 0);
        int strength = a.getInteger(R.styleable.FadeEdgeImage_edge, 14);

        init();
        setEdgeLength(strength);
    }

    public FadeEdgeImage(Context context) {
        super(context);
        this.c = context;
        init();
        this.setFadeDirection(DOWN_SIDE);
        this.setEdgeLength(14);
    }

    private void init() {
        this.setVerticalFadingEdgeEnabled(true);
    }

    public void setFadeDirection(int side) {
        this.mFadeSide = side;
    }

    public void setEdgeLength(int length) {
        this.setFadingEdgeLength(getPixels(length));
    }

    @Override
    protected float getTopFadingEdgeStrength() {
        return mFadeSide == UP_SIDE ? 1.0f : 0.0f;
    }

    @Override
    protected float getBottomFadingEdgeStrength() {
        return mFadeSide == DOWN_SIDE ? 1.0f : 0.0f;
    }

    @Override
    public boolean hasOverlappingRendering() {
        return true;
    }

    @Override
    public boolean onSetAlpha(int alpha) {
        return false;
    }

    private int getPixels(int dipValue) {
        Resources r = c.getResources();

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dipValue, r.getDisplayMetrics());
    }

}
