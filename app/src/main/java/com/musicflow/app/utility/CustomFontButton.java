package com.musicflow.app.utility;
import java.util.HashMap;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import com.musicflow.app.R;

public class CustomFontButton extends Button {
    public static final String BLACK = "fonts/NeuzeitGro-Bla.otf";
    public static final String BOLD = "fonts/NeuzeitGro-Bol.otf";
    public static final String LIGHT = "fonts/NeuzeitGro-Lig.otf";
    public static final String REGULAR = "fonts/NeuzeitGro-Reg.otf";
    public static final String CONDENSED_BLACK = "fonts/NeuzeitGro-Bla.otf";
    public static final String EXTRA_BLACK = "fonts/NeuzeitGroExt-Bla.otf";

    private static HashMap<String, Typeface> fonts = new HashMap<String, Typeface>();

    public CustomFontButton(Context context, String fontFileName) {
        super(context);
        setTypeface(getFont(fontFileName));
    }

    public CustomFontButton(Context context) {
        super(context);
        if (!isInEditMode()) {
            setTypeface(getFont(null));
        }
    }

    public CustomFontButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomFontButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            final TypedArray a =
                    context.obtainStyledAttributes(attrs, R.styleable.CustomFontButton, defStyle, 0);
            setTypeface(getFont(a.getString(R.styleable.CustomFontButton_fontName)));
            a.recycle();
        }
    }

    private Typeface getFont(String fontName) {
        if (fontName == null) fontName = REGULAR;
        if (fonts.containsKey(fontName)) return fonts.get(fontName);
        final Typeface font = Typeface.createFromAsset(getContext().getAssets(), fontName);
        fonts.put(fontName, font);
        return font;
    }
}