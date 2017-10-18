package azhar.sudha.marmupos.main.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import azhar.sudha.marmupos.R;
import azhar.sudha.marmupos.category.CategoryActivity;
import azhar.sudha.marmupos.interfaces.CategoryListListener;
import azhar.sudha.marmupos.items.ItemActivity;
import azhar.sudha.marmupos.main.MainActivity;
import azhar.sudha.marmupos.main.model.CategoryModel;
import azhar.sudha.marmupos.utils.DBUtils;

/**
 * Created by azharuddin on 20/9/17.
 */

@SuppressWarnings({"ConstantConditions", "unchecked"})
public class Items {

    public static List<CategoryModel> categoryList = new ArrayList<>();

    public void evaluate(final MainActivity activity, View itemView) {
        RelativeLayout itemLayout = itemView.findViewById(R.id.item_layout);
        RelativeLayout categoryLayout = itemView.findViewById(R.id.category_layout);

        itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(activity, ItemActivity.class));
            }
        });

        categoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(activity, CategoryActivity.class));
            }
        });


        DatabaseReference categoryRef = DBUtils.DATABASE.getReference(DBUtils.mAuth.getCurrentUser().getUid());
        categoryRef.keepSynced(true);

        categoryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    HashMap<String, Object> categoryMap = (HashMap<String, Object>) dataSnapshot.getValue();
                    for (String key : categoryMap.keySet()) {
                        HashMap<String, Object> category = (HashMap<String, Object>) categoryMap.get(key);
                        categoryList.add(new CategoryModel(key, category));
                    }
                    if (categorylistListener != null)
                        categorylistListener.getCategoryList(categoryList);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Error", databaseError.getMessage());
            }
        });

    }

    private static CategoryListListener categorylistListener;

    public static void categoryListener(CategoryListListener categorylistListener) {
        Items.categorylistListener = categorylistListener;
    }

}
