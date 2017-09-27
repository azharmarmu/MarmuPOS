package azhar.sudha.marmupos.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by azharuddin on 20/9/17.
 */

public class ViewUtils {

    public static void checkEmptyOnMandatoryField(EditText editText) {
        if (TextUtils.isEmpty(editText.getText()) || Stringify(editText).equals("")) {
            editText.setError("Mandatory Field!");
            editText.requestFocus();
        }
    }

    public static void checkPasswordIsSame(EditText password1, EditText password2) {
        if (!Stringify(password1).equalsIgnoreCase(Stringify(password2))) {
            password2.setError("Mandatory Field!");
            password2.requestFocus();
        }
    }

    public static String Stringify(EditText editText) {
        return editText.getText().toString().trim();
    }

    public static String Stringify(TextView textView) {
        return textView.getText().toString().trim();
    }

    public static void showToastSmall(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }
}
