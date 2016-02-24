package boiler.com.sainsburychallenge.model;

/**
 * Created by lenowo on 2016-01-18.
 */
public class Product {

    private final String mTitle;
    private final String mThumbnail;
    private final double mPrice;

    public Product(String title, String thumbnail, double price) {
        mTitle = title;
        mThumbnail = thumbnail;
        mPrice = price;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public double getPrice() {
        return mPrice;
    }
}
