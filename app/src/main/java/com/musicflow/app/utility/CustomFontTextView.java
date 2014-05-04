package com.musicflow.app.utility;

import java.util.HashMap;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.musicflow.app.R;

public class CustomFontTextView extends TextView {
    public static final String BLACK = "fonts/NeuzeitGro-Bla.otf";
    public static final String BOLD = "fonts/NeuzeitGro-Bol.otf";
    public static final String LIGHT = "fonts/NeuzeitGro-Lig.otf";
    public static final String REGULAR = "fonts/NeuzeitGro-Reg.otf";
    public static final String CONDENSED_BLACK = "fonts/NeuzeitGro-Bla.otf";
    public static final String EXTRA_BLACK = "fonts/NeuzeitGroExt-Bla.otf";

    private static HashMap<String, Typeface> fonts = new HashMap<String, Typeface>();

    public CustomFontTextView(Context context, String fontFileName) {
        super(context);
        if (!isInEditMode()) {
            setTypeface(getFont(fontFileName));
        }
    }

    public CustomFontTextView(Context context) {
        super(context);
        if (!isInEditMode()) {
            setTypeface(getFont(null));
        }
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode()) {
            final TypedArray a =
                    context.obtainStyledAttributes(attrs, R.styleable.CustomFontTextView, defStyle,
                            0);
            setTypeface(getFont(a.getString(R.styleable.CustomFontTextView_fontFileName)));
            a.recycle();
        }
    }

    private Typeface getFont(String fontFileName) {
        if (fontFileName == null) fontFileName = REGULAR;
        if (fonts.containsKey(fontFileName)) return fonts.get(fontFileName);
        final Typeface font = Typeface.createFromAsset(getContext().getAssets(), fontFileName);
        fonts.put(fontFileName, font);
        return font;
    }
}
