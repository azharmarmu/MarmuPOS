package azhar.sudha.marmupos.category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import azhar.sudha.marmupos.R;
import azhar.sudha.marmupos.main.model.CategoryModel;
import azhar.sudha.marmupos.utils.Constants;


/**
 * Created by azharuddin on 25/9/17.
 */

class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private Context context;
    private List<CategoryModel> categories;

    CategoryAdapter(Context context, List<CategoryModel> categories) {
        this.context = context;
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
        CategoryModel categoryModel = categories.get(position);
        holder.categoryPrice.setVisibility(View.GONE);
        HashMap<String, Object> categoryDetail = categoryModel.getCategoryMap();
        selectColor(holder.categoryColor, String.valueOf(categoryDetail.get(Constants.color)));
        holder.categoryName.setText(categoryModel.getName());
    }

    private void selectColor(TextView categoryColor, String color) {
        if (color.equalsIgnoreCase(Constants.GREY)) {
            categoryColor.setBackgroundResource(R.color.colorGray);
        } else if (color.equalsIgnoreCase(Constants.RED)) {
            categoryColor.setBackgroundResource(R.color.colorRed);
        } else if (color.equalsIgnoreCase(Constants.GREEN)) {
            categoryColor.setBackgroundResource(R.color.colorGreen);
        } else if (color.equalsIgnoreCase(Constants.BLUE)) {
            categoryColor.setBackgroundResource(R.color.colorBlue);
        } else if (color.equalsIgnoreCase(Constants.YELLOW)) {
            categoryColor.setBackgroundResource(R.color.colorYellow);
        } else if (color.equalsIgnoreCase(Constants.SKY)) {
            categoryColor.setBackgroundResource(R.color.colorSky);
        } else if (color.equalsIgnoreCase(Constants.ORANGE)) {
            categoryColor.setBackgroundResource(R.color.colorOrange);
        } else if (color.equalsIgnoreCase(Constants.PURPLE)) {
            categoryColor.setBackgroundResource(R.color.colorPurple);
        } else {
            categoryColor.setBackgroundResource(R.color.colorGray);
        }

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
