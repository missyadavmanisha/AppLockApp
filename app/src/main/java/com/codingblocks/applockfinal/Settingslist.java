package com.codingblocks.applockfinal;

import android.graphics.drawable.Drawable;

public class Settingslist {
    Drawable drawable;
    String name,subheading;

    public Settingslist(Drawable drawable, String name, String subheading) {
        this.drawable = drawable;
        this.name = name;
        this.subheading = subheading;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubheading() {
        return subheading;
    }

    public void setSubheading(String subheading) {
        this.subheading = subheading;
    }
}
