package azi.foosball;

import java.util.Arrays;
import java.util.Objects;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PasswordActivity extends AppCompatActivity {

	@BindView(R.id.passwordText)
	EditText passwordText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		ButterKnife.bind(this);
		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
					.setAction("Action", null).show();
			}
		});

		checkPassword();
	}

	private void checkPassword() {

		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

		if (sharedPref.contains("password")) {
			validatePassword(sharedPref.getString("password", null));
		}
	}

	@OnClick(R.id.loginButton)
	public void login() {

		validatePassword(passwordText.getText().toString());
	}

	private void validatePassword(String password) {

		if (Arrays.stream(Player.values()).map(Enum::name).anyMatch(name -> Objects.equals(name, password))) {

			IntentHelper.startActivityIntent(this, MainActivity.class);

			SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
			preferences.edit().putString("password", password).apply();

			this.finish();
		}
	}
}
