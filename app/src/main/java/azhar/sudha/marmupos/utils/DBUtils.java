package azhar.sudha.marmupos.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import azhar.sudha.marmupos.main.MainActivity;

import static azhar.sudha.marmupos.utils.ViewUtils.showToastSmall;

/**
 * Created by azharuddin on 20/9/17.
 */

@SuppressWarnings({"ConstantConditions", "ThrowableResultOfMethodCallIgnored"})
public class DBUtils {
    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static final FirebaseDatabase DATABASE = FirebaseDatabase.getInstance();
    public static final DatabaseReference userListDBRef = DATABASE.getReference(Constants.USER);
    public static final DatabaseReference categoryListDBRef = DATABASE.getReference(Constants.CATEGORY);


    public static void DB_LoginUser(String mail, String password, final Activity activity) {
        //// TODO: 27/9/17 progress need to been shown
        mAuth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(Constants.SUCCESS, "signInWithEmail:success");
                            activity.startActivity(new Intent(activity, MainActivity.class));
                            activity.finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(Constants.ERROR, "signInWithEmail:failure", task.getException());
                            showToastSmall(activity, task.getException().getMessage());
                        }
                    }
                });
    }

    public static void DB_RegisterUser(final String mail, final String password, final String businessName, final Activity activity) {
        //// TODO: 27/9/17 progress need to been shown
        mAuth.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(Constants.SUCCESS, "createUserWithEmail:success");
                            saveUserDetailsInDB(mail, password, businessName);
                            activity.startActivity(new Intent(activity, MainActivity.class));
                            activity.finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e(Constants.ERROR, "createUserWithEmail:failure", task.getException());
                            showToastSmall(activity, task.getException().getMessage());
                        }
                    }
                });
    }


    @SuppressWarnings("ConstantConditions")
    private static void saveUserDetailsInDB(String mail, String password, String businessName) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(Constants.EMAIL, mail);
        map.put(Constants.PASSWORD, password);
        map.put(Constants.BUSINESS_NAME, businessName);

        // Add user details to DB
        userListDBRef.child(mAuth.getCurrentUser().getUid()).updateChildren(map);
    }

}
