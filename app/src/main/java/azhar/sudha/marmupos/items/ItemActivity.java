package azhar.sudha.marmupos.items;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import azhar.sudha.marmupos.R;
import azhar.sudha.marmupos.interfaces.CategoryListListener;
import azhar.sudha.marmupos.main.model.CategoryModel;
import azhar.sudha.marmupos.main.view.Items;
import azhar.sudha.marmupos.utils.Constants;

public class ItemActivity extends AppCompatActivity implements CategoryListListener {

    Spinner spinnerCategories;
    RecyclerView recyclerView;

    List<CategoryModel> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Items.categoryListener(this);

        spinnerCategories = (Spinner) findViewById(R.id.category_spinner);

        FloatingActionButton addItem = (FloatingActionButton) findViewById(R.id.add_item);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ItemActivity.this, AddItemActivity.class));
            }
        });

        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateUI();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateUI() {
    }

    private void getCategories() {
        List<String> categories = new ArrayList<>();
        categories.add(0, Constants.ALL_CATEGORY);
        for (int i = 0; i < categoryList.size(); i++) {
            CategoryModel categoryModel = categoryList.get(i);
            categories.add(categoryModel.getName());
        }

        ArrayAdapter<String> nameAdapter = new ArrayAdapter<>(ItemActivity.this,
                android.R.layout.simple_spinner_item, categories);
        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(nameAdapter);
    }

    @Override
    public void getCategoryList(List<CategoryModel> categoryList) {
        this.categoryList = categoryList;
        getCategories();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.categoryList = Items.categoryList;
        getCategories();
    }
}
