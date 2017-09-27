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
import java.util.HashMap;
import java.util.List;

import azhar.sudha.marmupos.R;
import azhar.sudha.marmupos.interfaces.CategoryListListener;
import azhar.sudha.marmupos.main.view.Items;

public class ItemActivity extends AppCompatActivity implements CategoryListListener {

    Spinner spinnerCategories;
    RecyclerView recyclerView;

    HashMap<String, Object> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Items.categoryListener(this);

        this.categoryList = Items.categoryList;
        getCategories();

        FloatingActionButton addItem = (FloatingActionButton) findViewById(R.id.add_category);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ItemActivity.this, AddItemActivity.class));
            }
        });

        spinnerCategories = (Spinner) findViewById(R.id.category_spinner);

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

        for (String key : categoryList.keySet()) {
            categories.add(key);
        }

        ArrayAdapter<String> nameAdapter = new ArrayAdapter<>(ItemActivity.this,
                android.R.layout.simple_spinner_item, categories);
        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(nameAdapter);
    }

    @Override
    public void getCategoryList(HashMap<String, Object> categoryList) {
        this.categoryList = categoryList;
        getCategories();
    }
}
