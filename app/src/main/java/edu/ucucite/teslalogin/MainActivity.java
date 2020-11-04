package edu.ucucite.teslalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserObj userObj;
    private Button register_btn, login_btn;
    ArrayList<EditText> editTexts = new ArrayList<>();
    EditText username_ed, password_ed, full_name_ed, address_ed;
    RadioGroup gender_rg, status_rg;
    public static String status_val_reg = "";
    public static String gender_val_reg = "";
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        buildEditTexts();
        register_btn = findViewById(R.id.submit_btn);
        login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        status_rg = findViewById(R.id.status_selector);
        status_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.status_single:
                        status_val_reg = "Single";
                        break;
                    case R.id.status_married:
                        status_val_reg = "Married";
                        break;
                    case R.id.status_divorced:
                        status_val_reg = "Divorced";
                        break;
                    case R.id.status_separated:
                        status_val_reg = "Separated";
                        break;
                    case R.id.status_widowed:
                        status_val_reg = "Widowed";
                        break;
                    case R.id.status_unspecified:
                        status_val_reg = "Unspecified";
                        break;

                }
            }
        });

        gender_rg = findViewById(R.id.gender_selector);

        gender_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.gender_male:
                        gender_val_reg = "Male";
                        break;
                    case R.id.gender_female:
                        gender_val_reg = "Female";
                        break;
                    case R.id.gender_unspecified:
                        gender_val_reg = "Unspecified";
                        break;

                }
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid_ed() && !gender_val_reg.equals("") && !status_val_reg.equals("")) {
                    userObj = new UserObj(
                            username_ed.getText().toString(),
                            password_ed.getText().toString(),
                            full_name_ed.getText().toString(),
                            address_ed.getText().toString(),
                            gender_val_reg,
                            status_val_reg
                    );
                    user_validation_reg(userObj);
                } else {
                    Toast.makeText(MainActivity.this, "Please complete the fields", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public void buildEditTexts() {
        username_ed = findViewById(R.id.username_ed_login);
        password_ed = findViewById(R.id.password_ed_login);
        full_name_ed = findViewById(R.id.full_name_ed_reg);
        address_ed = findViewById(R.id.address_reg_ed);


        // arraylists of edit text for easier unset and validation of empty text
        editTexts.add(username_ed);
        editTexts.add(password_ed);
        editTexts.add(full_name_ed);
        editTexts.add(address_ed);

    }

    public void unset_all_fields() {
        for (EditText editTextItem : editTexts) {
            editTextItem.setText("");
        }
        gender_rg.clearCheck();
        status_rg.clearCheck();
        status_val_reg = "";
        gender_val_reg = "";

    }

    public boolean isValid_ed() {
        Boolean isValid = true;
        for (EditText editTextItem : editTexts) {
            if (TextUtils.isEmpty(editTextItem.getText().toString())) {
                editTextItem.setError("Please enter this field");
                isValid = false;
            }
        }
        return isValid;

    }

    public void user_validation_reg(UserObj userObj) {
        if (!UserMgmt.is_username_registered(userObj.getUsername(), MainActivity.this)) {
            UserMgmt.insert_user(userObj, MainActivity.this);
            unset_all_fields();
            Toast.makeText(MainActivity.this,
                    "Created successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this,
                    userObj.getUsername() + " actually exists", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

}