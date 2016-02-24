package jayxu.com.waitlistmanager.UI;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.List;

import jayxu.com.waitlistmanager.R;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends Activity{
    private final String TAG=RegisterActivity.class.getSimpleName();
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;


    private Button mRegisterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email_sign_up);
        mPasswordView = (EditText) findViewById(R.id.password_sign_up);

        mRegisterButton =(Button)findViewById(R.id.sign_up_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                final ParseUser user = new ParseUser();
                user.setEmail(email);
                user.setUsername(email.split("@")[0]);
                user.setPassword(password);

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast toast = Toast.makeText(RegisterActivity.this, "Sign Up Success!", Toast.LENGTH_LONG);
                            toast.show();
                            Intent mIntent = new Intent(RegisterActivity.this, MainActivity.class);
                            mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(mIntent);
                        }else{
                            Toast toast = Toast.makeText(RegisterActivity.this, "Sign Up failed", Toast.LENGTH_LONG);
                            if (e.getMessage().contains("already taken")) {
                                // The user name already exists
                                toast.setText("Username already exist!");
                            }
                            Log.w(TAG, "--------------Sign Up Failed!");
                            Log.w(TAG, e);
                            toast.show();
                        }
                    }
                });
            }
        });

        // Set up the login form.


    }




    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

}



