package azhar.sudha.marmupos.main.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import azhar.sudha.marmupos.R;
import azhar.sudha.marmupos.main.model.HashListModel;
import azhar.sudha.marmupos.utils.Constants;

/**
 * Created by azharuddin on 3/8/17.
 */

@SuppressWarnings("unchecked")
public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.MyViewHolder> {
    private Context context;
    private List<HashListModel> itemList;

    public ItemListAdapter(Context context, List<HashListModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_common, parent, false);
        return new ItemListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HashListModel listModel = itemList.get(position);
        HashMap<String, Object> hashMap = (HashMap<String, Object>) listModel.getListMap();
        holder.itemColor.setBackgroundColor(Color.parseColor(String.valueOf(hashMap.get(Constants.ITEM_COLOR))));
        holder.itemName.setText(String.valueOf(hashMap.get(Constants.ITEM_NAME)));
        holder.itemQty.setText(String.valueOf(hashMap.get(Constants.ITEM_QTY)));
        holder.itemPrice.setText(String.valueOf(hashMap.get(Constants.ITEM_PRICE)));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemColor, itemName, itemQty, itemPrice;

        MyViewHolder(View itemView) {
            super(itemView);
            itemColor = itemView.findViewById(R.id.item_color);
            itemName = itemView.findViewById(R.id.item_name);
            itemQty = itemView.findViewById(R.id.item_qty);
            itemPrice = itemView.findViewById(R.id.item_price);
        }
    }
}
