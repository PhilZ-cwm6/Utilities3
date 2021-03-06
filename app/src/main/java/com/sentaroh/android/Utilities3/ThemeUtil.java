package com.sentaroh.android.Utilities3;
/*
The MIT License (MIT)
Copyright (c) 2016 Sentaroh

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
and to permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be included in all copies or
substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

*/

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;

public class ThemeUtil {
    public static final int THEME_BLACK=2;
    public static final int THEME_DEFAULT=0;
    public static final int THEME_LIGHT=1;

//	public static ThemeColorList getThemeColorList(Context a, boolean theme_is_light) {
//        ThemeColorList tcd=new ThemeColorList();
//
////        tcd.theme_is_light=theme_is_light;
//
//        if (theme_is_light) {
//            setLightTheme(tcd);
//        } else {
//            setDefaultTheme(tcd);
//        }
//        return tcd;
//	}
//
	private static void setLightTheme(ThemeColorList tcd) {
	    tcd.theme_is_light=true;
        tcd.text_color_primary=0xff000000;
        tcd.text_color_disabled= Color.LTGRAY;
//        tcd.text_color_dialog_title=0xffffffff;
        tcd.text_background_color=0xffffffff;//0xffe8e8e8;//0xffc0c0c0;
//        if (Build.VERSION.SDK_INT>=21) {
//            tcd.dialog_title_background_color=0xff303030;//515151;
//            tcd.dialog_msg_background_color=0xffc0c0c0;
//            tcd.window_background_color_content=0xffe0e0e0;
//        } else {
//            tcd.dialog_title_background_color=0xff303030;//515151;
//            tcd.dialog_msg_background_color=0xffc0c0c0;
//            tcd.window_background_color_content=0xffe0e0e0;
//        }
        tcd.text_color_warning= Color.argb(255, 192, 0, 255);//Color.argb(255, 192, 158, 0);
//
//        tcd.text_color_info=tcd.text_color_dialog_title;
        tcd.text_color_error= Color.RED;
        tcd.title_text_color=0xffffffff;
        tcd.title_background_color=0xff202020;
    }

    private static void setDefaultTheme(ThemeColorList tcd) {
        tcd.theme_is_light=false;
        tcd.text_color_primary=0xffcccccc;

        tcd.text_color_disabled= Color.GRAY;
//        tcd.text_color_primary=0xffffffff;
//        tcd.text_color_dialog_title=0xffffffff;
//        tcd.dialog_title_background_color=0xff303030;//515151;
//        tcd.dialog_msg_background_color=0xff303030;
        tcd.text_background_color=0xff444444;//0xff303030;
//        tcd.window_background_color_content=0xff303030;
        tcd.text_color_warning= Color.YELLOW;
//
//        tcd.text_color_info=tcd.text_color_dialog_title;
        tcd.text_color_error= Color.RED;
        tcd.title_text_color=0xffffffff;
//        tcd.title_background_color=0xff303030;
        tcd.title_background_color=0xff202020;
    }

    private static void setBlackTheme(ThemeColorList tcd) {
        tcd.theme_is_light=false;
        tcd.text_color_primary=0xff888888;

        tcd.text_color_disabled= Color.GRAY;
//        tcd.text_color_primary=0xffffffff;
//        tcd.text_color_dialog_title=0xffffffff;
//        tcd.dialog_title_background_color=0xff000000;//515151;
//        tcd.dialog_msg_background_color=0xff000000;
        tcd.text_background_color=0xff000000;
//        tcd.window_background_color_content=0xff000000;
        tcd.text_color_warning= Color.YELLOW;
//
//        tcd.text_color_info=tcd.text_color_dialog_title;
        tcd.text_color_error= Color.RED;
        tcd.title_text_color=0xffffffff;
        tcd.title_background_color=0xff000000;
    }


    public static ThemeColorList getThemeColorList(Context a) {
        ThemeColorList tcd=new ThemeColorList();

        TypedValue outValue = new TypedValue();
        boolean rc=a.getTheme().resolveAttribute(R.attr.AppUsedTheme, outValue, true);
        int theme=0;
        if (outValue.data==THEME_LIGHT) {
            setLightTheme(tcd);
        } else if (outValue.data==THEME_BLACK) {
            setBlackTheme(tcd);
        } else {
            setDefaultTheme(tcd);
        }

        return tcd;
    }

    public static int getAppTheme(Context a) {
        TypedValue outValue = new TypedValue();
        boolean rc=a.getTheme().resolveAttribute(R.attr.AppUsedTheme, outValue, true);
        int theme=0;
    	if (outValue.data==THEME_LIGHT) theme=R.style.MainLight;
    	else if (outValue.data==THEME_BLACK) theme=R.style.MainBlack;
    	else theme=R.style.Main;
    	return theme;
	}

    public static boolean isLightThemeUsed(Context a) {
        TypedValue outValue = new TypedValue();
        boolean rc=a.getTheme().resolveAttribute(R.attr.AppUsedTheme, outValue, true);
        boolean result=false;
        if (outValue.data==THEME_LIGHT) result=true;
        return result;
    }

}
