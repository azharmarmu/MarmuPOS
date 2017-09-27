package azhar.sudha.marmupos.category;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

import azhar.sudha.marmupos.R;


/**
 * Created by azharuddin on 25/9/17.
 */

class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private HashMap<String, Object> categories;

    CategoryAdapter(HashMap<String, Object> categories) {
        this.categories = categories;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_common, parent, false);
        return new CategoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.categoryPrice.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categoryColor, categoryName, categoryItems, categoryPrice;

        MyViewHolder(View itemView) {
            super(itemView);
            categoryColor = itemView.findViewById(R.id.item_color);
            categoryName = itemView.findViewById(R.id.item_name);
            categoryItems = itemView.findViewById(R.id.item_qty);
            categoryPrice = itemView.findViewById(R.id.item_price);
        }
    }
}
