package boiler.com.sainsburychallenge.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenowo on 2016-02-23.
 */
public class ProductList {
    private final List<Product> mProducts;
    private double mOverallPrice;

    public ProductList(List<Product> products) {
        mProducts = new ArrayList<>();
        mOverallPrice = 0.;
        for (Product product : products) {
            addProduct(product);
        }
    }

    public Product get(int index) {
        return mProducts.get(index);
    }

    public int size() {
        return mProducts.size();
    }

    public double getOverallPrice() {
        return mOverallPrice;
    }

    private void addProduct(Product product) {
        mProducts.add(product);
        mOverallPrice += product.getPrice();
    }
}
