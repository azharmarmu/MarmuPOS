package azhar.sudha.marmupos.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import azhar.sudha.marmupos.R;


/**
 * Created by azharuddin on 3/5/17.
 */

public class ImageUtils {

    public static void loadImageToViewByURL(final Context context, final ImageView view, final Uri imageUrl) {
        Picasso.with(context)
                .load(imageUrl)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(view, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.e("Picasso", "Sender image from cache");
                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(context)
                                .load(imageUrl)
                                .error(R.drawable.ic_person)
                                .into(view, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        Log.v("Picasso", "Image from firebase");
                                    }

                                    @Override
                                    public void onError() {
                                        Log.e("Picasso", "Could not fetch image");
                                    }
                                });
                    }
                });
    }

    public static void loadCircleImageToViewByURL(final Context context, final ImageView view, final Uri imageUrl) {
        Picasso.with(context)
                .load(imageUrl)
                .transform(new CircleTransform())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(view, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.e("Picasso", "Sender image from cache");
                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(context)
                                .load(imageUrl)
                                .transform(new CircleTransform())
                                .error(R.drawable.ic_person)
                                .into(view, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        Log.v("Picasso", "Image from firebase");
                                    }

                                    @Override
                                    public void onError() {
                                        Log.e("Picasso", "Could not fetch image");
                                    }
                                });
                    }
                });
    }
}
