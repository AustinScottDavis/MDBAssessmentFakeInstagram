package com.example.davis.myapplication;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.net.URI;

public class Post {
    Uri picture;
    String caption;

    public Post(Uri pic, String cap) {
        this.picture = pic;
        this.caption = cap;
    }
}
