package boiler.com.sainsburychallenge.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import boiler.com.sainsburychallenge.model.ProductList;
import boiler.com.sainsburychallenge.model.Product;
import boiler.com.sainsburychallenge.R;

/**
 * Created by lenowo on 2016-01-18.
 */

// Adapter for the list (RecyclerView)
public class RecyclerViewAdapter extends RecyclerView.Adapter<RowHolder> {

    private final ProductList mProductList;
    private final Context mContext;

    public RecyclerViewAdapter(Context ctx, ProductList list) {
        mProductList = list;
        mContext = ctx;
    }

    @Override
    public RowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        return new RowHolder(v);
    }

    @Override
    public void onBindViewHolder(final RowHolder holder, final int position) {
        Product listItem = mProductList.get(position);
        holder.getLayoutPosition();
        holder.setTitle(listItem.getTitle());
        holder.setPrice(listItem.getPrice(), mContext);
        holder.setThumbnail(listItem.getThumbnail(), mContext);
    }

    @Override
    public int getItemCount() {
        return mProductList == null ? 0 : mProductList.size();
    }
}
