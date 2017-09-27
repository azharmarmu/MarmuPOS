package azhar.sudha.marmupos.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import azhar.sudha.marmupos.R;

import static azhar.sudha.marmupos.utils.DBUtils.DB_RegisterUser;
import static azhar.sudha.marmupos.utils.ViewUtils.Stringify;
import static azhar.sudha.marmupos.utils.ViewUtils.checkEmptyOnMandatoryField;
import static azhar.sudha.marmupos.utils.ViewUtils.checkPasswordIsSame;
import static azhar.sudha.marmupos.utils.ViewUtils.showToastSmall;

@SuppressWarnings("deprecation")
public class RegisterActivity extends AppCompatActivity {

    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        String checkBoxText = "I agree to all the " +
                "<a href='http://www.redbus.in/mob/mTerms.aspx' > " +
                "Terms and Conditions</a>";
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        checkBox.setText(Html.fromHtml(checkBoxText));
        checkBox.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void registerUser(View view) {
        if (checkBox.isChecked()) {
            EditText etMail = (EditText) findViewById(R.id.et_email);
            EditText etPassword = (EditText) findViewById(R.id.et_password);
            EditText etConfirmPassword = (EditText) findViewById(R.id.et_confirm_password);
            EditText etBusinessName = (EditText) findViewById(R.id.et_business_name);

            checkEmptyOnMandatoryField(etMail);
            checkEmptyOnMandatoryField(etPassword);
            checkEmptyOnMandatoryField(etConfirmPassword);
            checkEmptyOnMandatoryField(etBusinessName);

            checkPasswordIsSame(etPassword, etConfirmPassword);

            DB_RegisterUser(Stringify(etMail), Stringify(etPassword), Stringify(etBusinessName), RegisterActivity.this);
        } else {
            showToastSmall(RegisterActivity.this, "please agree term and conditions");
        }
    }
}
