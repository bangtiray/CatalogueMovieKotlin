/*
 * *
 *  * Created by bangtiray on 5/25/19 2:17 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 5/16/19 9:53 AM
 *
 */

package com.bangtiray.tmdb.util.customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

public class Button_Roboto_Medium extends Button {

    public Button_Roboto_Medium(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public Button_Roboto_Medium(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Button_Roboto_Medium(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            if (!isInEditMode()) {
                Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Regular.ttf");
                setTypeface(tf);
            }
        }
    }
}
