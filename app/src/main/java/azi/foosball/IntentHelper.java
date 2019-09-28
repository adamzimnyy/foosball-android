package azi.foosball;

import android.app.Activity;
import android.content.Intent;

public class IntentHelper {

	public static void startActivityIntent(Activity source, Class<? extends Activity> nextActivity){
		Intent  i = new Intent(source,nextActivity);
		source.startActivity(i);
	}
}