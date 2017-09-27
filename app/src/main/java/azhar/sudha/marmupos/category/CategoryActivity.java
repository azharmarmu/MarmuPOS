package azhar.sudha.marmupos.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;

import azhar.sudha.marmupos.R;
import azhar.sudha.marmupos.interfaces.CategoryListListener;
import azhar.sudha.marmupos.main.view.Items;

public class CategoryActivity extends AppCompatActivity implements CategoryListListener {

    RecyclerView recyclerView;

    HashMap<String, Object> categoryList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Items.categoryListener(this);

        this.categoryList = Items.categoryList;
        getCategories();

        FloatingActionButton addCategory = (FloatingActionButton) findViewById(R.id.add_category);
        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoryActivity.this, AddCategoryActivity.class));
            }
        });
    }

    private void getCategories() {
        if (categoryList != null) {
            populateView();
        }
    }

    private void populateView() {
        RecyclerView.Adapter adapter = new CategoryAdapter(categoryList);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.removeAllViews();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CategoryActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void getCategoryList(HashMap<String, Object> categoryList) {
        this.categoryList = categoryList;
        getCategories();
    }
}
