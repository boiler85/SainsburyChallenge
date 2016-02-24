package boiler.com.sainsburychallenge.controller;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import boiler.com.sainsburychallenge.model.Product;
import boiler.com.sainsburychallenge.model.ProductList;

/**
 * Created by lenowo on 2016-02-23.
 */
public class DataRetriever {

    private static final String DOMAIN = "https://www.sainsburys.co.uk";
    private static final String WS_URL = DOMAIN + "/webapp/wcs/stores/servlet/CategoryDisplay?listView=true&orderBy=FAVOURITES_FIRST&parent_category_rn=12518&top_category=12518&langId=44&beginIndex=0&pageSize=20&catalogId=10137&searchTerm=&categoryId=185749&listId=&storeId=10151&promotionId=#langId=44&storeId=10151&catalogId=10122&categoryId=185749&parent_category_rn=12518&top_category=12518&pageSize=20&orderBy=FAVOURITES_FIRST&searchTerm=&beginIndex=0";

    private final ModelReadyNotifier mModelReadyNotifier;

    public DataRetriever(ModelReadyNotifier modelReadyNotifier) {
        mModelReadyNotifier = modelReadyNotifier;
        new HttpDownloader().execute();
    }

    private class HttpDownloader extends AsyncTask<Void, Void, List<Product>> {

        @Override
        protected List<Product> doInBackground(Void... params) {

            List<Product> result = new ArrayList<>();
            try {
                Document doc = Jsoup.connect(WS_URL).get();
                Element body = doc.body();
                Elements products = body.getElementsByClass("product");
                for (Element product : products) {
                    Elements productName = product.getElementsByClass("productName");
                    if (productName == null || productName.size() == 0) {
                        continue;
                    }
                    String name = productName.get(0).text();
                    Elements img = productName.get(0).getElementsByTag("img");
                    String thumbnail = img == null || img.size() == 0 ? "" : DOMAIN + img.get(0).attr("src");
                    Elements pricing = product.getElementsByClass("pricePerUnit");
                    String priceText = pricing == null || pricing.size() == 0 ? null : pricing.get(0).text();
                    double price = priceText == null ? 0. : Double.parseDouble(priceText.substring(1, priceText.indexOf("/")));
                    result.add(new Product(name, thumbnail, price));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<Product> result) {
            mModelReadyNotifier.modelReady(new ProductList(result));
        }
    }

    public interface ModelReadyNotifier {
        void modelReady(ProductList products);
    }
}
