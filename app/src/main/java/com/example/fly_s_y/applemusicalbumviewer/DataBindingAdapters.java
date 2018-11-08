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
    /**
     * Binds an image to an ImageView via a URI
     * @param view The view where the image will be displayed
     * @param imageUri The URI referencing the image
     * @param defaultImage The image to display when imageUri fails to reference an image
     */
    @BindingAdapter(value={"appleMusicAlbumViewer:src", "appleMusicAlbumViewer:defaultImage"}, requireAll = false)
    public static void setImageUri(ImageView view, String imageUri, int defaultImage) {
        if (imageUri == null) {
            view.setImageResource(defaultImage);
        } else {
            view.setImageURI(Uri.parse(imageUri));
        }
    }

    /**
     * Binds an image to an ImageView via a URI
     * @param view The view where the image will be displayed
     * @param imageUri The URI referencing the image
     * @param defaultImage The image to display when imageUri fails to reference an image
     */
    @BindingAdapter(value={"appleMusicAlbumViewer:src", "appleMusicAlbumViewer:defaultImage"}, requireAll = false)
    public static void setImageUri(ImageView view, Uri imageUri, int defaultImage) {
        if(imageUri == null){
            view.setImageResource(defaultImage);
        } else {
            view.setImageURI(imageUri);
        }
    }

    @BindingAdapter(value={"appleMusicAlbumViewer:src", "appleMusicAlbumViewer:defaultImage"}, requireAll = false)
    public static void setImageDrawable(ImageView view, Drawable drawable, int defaultImage) {
        if(drawable == null){
            view.setImageResource(defaultImage);
        } else {
            view.setImageDrawable(drawable);
        }
    }

    @BindingAdapter(value={"appleMusicAlbumViewer:src", "appleMusicAlbumViewer:defaultImage"}, requireAll = false)
    public static void setImageResource(ImageView view, int resource, int defaultImage){
        if(resource < 0){
            view.setImageResource(defaultImage);
        } else {
            view.setImageResource(resource);
        }
    }
}
