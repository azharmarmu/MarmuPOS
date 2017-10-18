package azhar.sudha.marmupos.category;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.HashMap;

import azhar.sudha.marmupos.R;
import azhar.sudha.marmupos.utils.Constants;

import static azhar.sudha.marmupos.utils.DBUtils.DATABASE;
import static azhar.sudha.marmupos.utils.DBUtils.mAuth;
import static azhar.sudha.marmupos.utils.ViewUtils.Stringify;

@SuppressWarnings("ConstantConditions")
public class AddCategoryActivity extends AppCompatActivity {

    private String categoryColor = "grey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
    }

    public void colorClick(View view) {
        ImageView colorGray, colorRed, colorGreen, colorBlue, colorYellow, colorSky, colorOrange, colorPurple;
        colorGray = (ImageView) findViewById(R.id.color_grey);
        colorRed = (ImageView) findViewById(R.id.color_red);
        colorGreen = (ImageView) findViewById(R.id.color_green);
        colorBlue = (ImageView) findViewById(R.id.color_blue);
        colorYellow = (ImageView) findViewById(R.id.color_yellow);
        colorSky = (ImageView) findViewById(R.id.color_sky);
        colorOrange = (ImageView) findViewById(R.id.color_orange);
        colorPurple = (ImageView) findViewById(R.id.color_purple);

        colorGray.setImageDrawable(null);
        colorRed.setImageDrawable(null);
        colorGreen.setImageDrawable(null);
        colorBlue.setImageDrawable(null);
        colorYellow.setImageDrawable(null);
        colorSky.setImageDrawable(null);
        colorOrange.setImageDrawable(null);
        colorPurple.setImageDrawable(null);

        ImageView imageView = (ImageView) view;
        imageView.setImageDrawable(getDrawable(R.drawable.ic_check_white));

        switch (imageView.getId()) {
            case R.id.color_grey:
                categoryColor = Constants.GREY;
                break;
            case R.id.color_red:
                categoryColor = Constants.RED;
                break;
            case R.id.color_green:
                categoryColor = Constants.GREEN;
                break;
            case R.id.color_blue:
                categoryColor = Constants.BLUE;
                break;
            case R.id.color_yellow:
                categoryColor = Constants.YELLOW;
                break;
            case R.id.color_sky:
                categoryColor = Constants.SKY;
                break;
            case R.id.color_orange:
                categoryColor = Constants.ORANGE;
                break;
            case R.id.color_purple:
                categoryColor = Constants.PURPLE;
                break;
            default:
                categoryColor = Constants.GREY;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_category_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_category_save:
                saveCategory();
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    private void saveCategory() {
        EditText categoryName = (EditText) findViewById(R.id.category_name);
        HashMap<String, Object> category = new HashMap<>();
        category.put(Constants.color, categoryColor);
        DATABASE.getReference(mAuth.getCurrentUser().getUid()).child(Stringify(categoryName)).updateChildren(category);
    }
}
