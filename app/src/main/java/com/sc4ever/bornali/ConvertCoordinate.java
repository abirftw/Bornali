package com.sc4ever.bornali;

import android.content.res.Resources;

public class ConvertCoordinate {

    public static int dpToPx(float dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(float px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
