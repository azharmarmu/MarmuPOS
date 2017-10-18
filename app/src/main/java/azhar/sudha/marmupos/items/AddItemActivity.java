package azhar.sudha.marmupos.items;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import azhar.sudha.marmupos.R;
import azhar.sudha.marmupos.utils.Constants;

@SuppressWarnings("deprecation")
public class AddItemActivity extends AppCompatActivity {

    EditText etItemName, etPrice, etSKU, etBarCode;
    RadioGroup rgSoldBy;

    private String itemColor = "grey";
    private String itemShape = "box";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        shapeBox = (ImageView) findViewById(R.id.shape_box);
        shapeCircle = (ImageView) findViewById(R.id.shape_circle);
        shapeCircleZigZag = (ImageView) findViewById(R.id.shape_circle_zig);
        shapeHexagon = (ImageView) findViewById(R.id.shape_hexagon);

        shapeBox.setBackground();

    }

    ImageView shapeBox, shapeCircle, shapeCircleZigZag, shapeHexagon;
    public void shapeClick(View view) {
        shapeBox.setImageDrawable(null);
        shapeCircle.setImageDrawable(null);
        shapeCircleZigZag.setImageDrawable(null);
        shapeHexagon.setImageDrawable(null);

        ImageView imageView = (ImageView) view;
        imageView.setImageDrawable(getDrawable(R.drawable.ic_check_black));

        switch (imageView.getId()) {
            case R.id.shape_box:
                itemShape = Constants.BOX;
                break;
            case R.id.shape_circle:
                itemShape = Constants.CIRCLE;
                break;
            case R.id.shape_circle_zig:
                itemShape = Constants.CIRCLE_ZIG_ZAG;
                break;
            case R.id.shape_hexagon:
                itemShape = Constants.HEXAGON;
                break;
            default:
                itemShape = Constants.BOX;
        }

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
                itemColor = Constants.GREY;
                break;
            case R.id.color_red:
                itemColor = Constants.RED;
                break;
            case R.id.color_green:
                itemColor = Constants.GREEN;
                break;
            case R.id.color_blue:
                itemColor = Constants.BLUE;
                break;
            case R.id.color_yellow:
                itemColor = Constants.YELLOW;
                break;
            case R.id.color_sky:
                itemColor = Constants.SKY;
                break;
            case R.id.color_orange:
                itemColor = Constants.ORANGE;
                break;
            case R.id.color_purple:
                itemColor = Constants.PURPLE;
                break;
            default:
                itemColor = Constants.GREY;
        }
    }
}
