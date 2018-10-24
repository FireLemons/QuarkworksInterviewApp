package com.example.fly_s_y.applemusicalbumviewer;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

/**
 * Class allowing for data binding of images
 *
 * Code based on
 * https://stackoverflow.com/a/35809319/3667444
 * https://stackoverflow.com/a/47312208/3667444
 */
public class DataBindingAdapters {
    @BindingAdapter("app:src")
    public static void setImageUri(ImageView view, String imageUri) {
        if (imageUri == null) {
            view.setImageURI(null);
        } else {
            view.setImageURI(Uri.parse(imageUri));
        }
    }

    @BindingAdapter("app:src")
    public static void setImageUri(ImageView view, Uri imageUri) {
        view.setImageURI(imageUri);
    }

    @BindingAdapter("app:src")
    public static void setImageDrawable(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }

    @BindingAdapter("app:src")
    public static void setImageResource(ImageView view, int resource){
        view.setImageResource(resource);
    }
}
