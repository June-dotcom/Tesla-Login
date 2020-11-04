package edu.ucucite.teslalogin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import edu.ucucite.teslalogin.UserContract.UserEntry;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText username_login_ed, password_login_ed;
    Button sign_in_btn, sign_up_btn;
    private UserObj userObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        username_login_ed = findViewById(R.id.username_ed_login);
        password_login_ed = findViewById(R.id.password_ed_login);
        sign_in_btn = findViewById(R.id.sign_in_btn);
        sign_up_btn = findViewById(R.id.sign_up_btn);
        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = UserMgmt.fetch_info(username_login_ed.getText().toString(), LoginActivity.this);
                if (cursor.moveToFirst()) {
                    //password authentication - no encryption just the basic equals() method
                    if (password_login_ed.getText().toString().equals(cursor.getString
                            (cursor.getColumnIndex(UserContract.UserEntry.COLUMN_PASSWORD)))) {
                        password_login_ed.setText("");
                        username_login_ed.setText("");
                        alertdialog_login_success(cursor);
                    } else {
                        // wrong password
                        Toast.makeText(LoginActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // no user name detected
                    Toast.makeText(LoginActivity.this, "Wrong login details", Toast.LENGTH_SHORT).show();

                }
            }
        });

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });


    }

    public void alertdialog_login_success(Cursor cursor) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Login information");
        final View credentials_layout = getLayoutInflater().inflate(R.layout.credentials_layout, null);
        builder.setView(credentials_layout);
        build_user_obj(credentials_layout, cursor);
        builder.setNeutralButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(LoginActivity.this, "You have been logout", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @SuppressLint("SetTextI18n")
    public void build_user_obj(View credentials_layout, Cursor cursor){
        TextView login_info = credentials_layout.findViewById(R.id.login_info);
        userObj = new UserObj(
                cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_USERNAME)),
                cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_PASSWORD)),
                cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_FULLNAME)),
                cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_ADDRESS)),
                cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_GENDER)),
                cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_STATUS))
        );
        login_info.setText(userObj.print_user_info());

    }


}