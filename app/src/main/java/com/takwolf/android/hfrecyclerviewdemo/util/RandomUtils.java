package com.takwolf.android.hfrecyclerviewdemo.util;

import android.graphics.Color;

import java.util.Random;

import androidx.annotation.ColorInt;

public final class RandomUtils {

    private RandomUtils() {}

    public static final Random random = new Random();

    @ColorInt
    public static int randomColor() {
        int red = (int) Math.round(random.nextDouble() * 255);
        int green = (int) Math.round(random.nextDouble() * 255);
        int blue = (int) Math.round(random.nextDouble() * 255);
        return Color.rgb(red, green, blue);
    }

}
