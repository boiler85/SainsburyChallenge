package boiler.com.sainsburychallenge.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import boiler.com.sainsburychallenge.R;
import boiler.com.sainsburychallenge.utils.ImageLoaderSingleton;

/**
 * Created by lenowo on 2016-01-18.
 */
public class RowHolder extends RecyclerView.ViewHolder {

    private final NetworkImageView mThumbnail;
    private final TextView mTitle;
    private final TextView mPrice;

    public RowHolder(View view) {
        super(view);
        mThumbnail = (NetworkImageView) view.findViewById(R.id.thumbnail);
        mTitle = (TextView) view.findViewById(R.id.title);
        mPrice = (TextView) view.findViewById(R.id.price);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setPrice(double price, Context context) {
        StringBuilder priceText = new StringBuilder("£");
        priceText.append(String.format("%.2f", price)).append("/").append(context.getString(R.string.unit));
        mPrice.setText(priceText);
    }

    public void setThumbnail(String url, Context context) {
        ImageLoader imageLoader = ImageLoaderSingleton.getInstance(context).getImageLoader();
        mThumbnail.setImageUrl(url, imageLoader);
    }
}
