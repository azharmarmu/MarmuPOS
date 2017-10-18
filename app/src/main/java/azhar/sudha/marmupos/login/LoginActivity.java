package azhar.sudha.marmupos.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;

import azhar.sudha.marmupos.R;
import azhar.sudha.marmupos.main.MainActivity;

import static azhar.sudha.marmupos.utils.DBUtils.DB_LoginUser;
import static azhar.sudha.marmupos.utils.DBUtils.mAuth;
import static azhar.sudha.marmupos.utils.ViewUtils.Stringify;
import static azhar.sudha.marmupos.utils.ViewUtils.checkEmptyOnMandatoryField;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    public void registerUser(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    public void loginUser(View view) {
        EditText etMail = (EditText) findViewById(R.id.et_email);
        EditText etPassword = (EditText) findViewById(R.id.et_password);

        checkEmptyOnMandatoryField(etMail);
        checkEmptyOnMandatoryField(etPassword);

        DB_LoginUser(Stringify(etMail), Stringify(etPassword), LoginActivity.this);
    }
}
