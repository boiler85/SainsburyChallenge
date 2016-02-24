package boiler.com.sainsburychallenge;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import boiler.com.sainsburychallenge.controller.DataRetriever;
import boiler.com.sainsburychallenge.controller.DataRetriever.ModelReadyNotifier;
import boiler.com.sainsburychallenge.model.ProductList;
import boiler.com.sainsburychallenge.view.RecyclerViewAdapter;

public class MainActivity extends Activity implements ModelReadyNotifier {

    private RecyclerView mRecyclerView;
    private TextView mOverallPriceTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mOverallPriceTxt = (TextView) findViewById(R.id.price_overall);
        new DataRetriever(this);
    }

    @Override
    public void modelReady(ProductList products) {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, products);
        mRecyclerView.setAdapter(adapter);
        mOverallPriceTxt.setText(new StringBuilder(getString(R.string.overall)).append(": £").append(products.getOverallPrice()));
    }
}
