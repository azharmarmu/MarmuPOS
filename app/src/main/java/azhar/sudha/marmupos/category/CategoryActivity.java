package azhar.sudha.marmupos.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import azhar.sudha.marmupos.R;
import azhar.sudha.marmupos.interfaces.CategoryListListener;
import azhar.sudha.marmupos.main.model.CategoryModel;
import azhar.sudha.marmupos.main.view.Items;

public class CategoryActivity extends AppCompatActivity implements CategoryListListener {

    RecyclerView recyclerView;

    List<CategoryModel> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Items.categoryListener(this);

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
        RecyclerView.Adapter adapter = new CategoryAdapter(CategoryActivity.this, categoryList);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.removeAllViews();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CategoryActivity.this);
        recyclerView.addItemDecoration(new DividerItemDecoration(CategoryActivity.this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void getCategoryList(List<CategoryModel> categoryList) {
        this.categoryList = categoryList;
        getCategories();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.category_menu, menu);

        /*final MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (android.widget.SearchView) searchItem.getActionView();

        // Detect SearchView icon clicks
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setItemsVisibility(menu, searchItem, false);
            }
        });
        // Detect SearchView close
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                setItemsVisibility(menu, searchItem, true);
                return false;
            }
        });*/

        return super.onCreateOptionsMenu(menu);
    }

    private void setItemsVisibility(Menu menu, MenuItem exception, boolean visible) {
        for (int i = 0; i < menu.size(); ++i) {
            MenuItem item = menu.getItem(i);
            if (item != exception) item.setVisible(visible);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        this.categoryList = Items.categoryList;
        getCategories();
    }
}
