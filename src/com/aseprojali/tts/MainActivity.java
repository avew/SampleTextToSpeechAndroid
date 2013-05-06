package com.aseprojali.tts;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnInitListener {
	private TextToSpeech speech;
	private EditText txtInput;
	private Button speak;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		speech = new TextToSpeech(this, this);
		speak = (Button) findViewById(R.id.btnTekan);
		txtInput = (EditText) findViewById(R.id.edtInput);

		speak.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				speak();
			}

		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (speech != null) {
			speech.stop();
			speech.shutdown();
		}
		super.onDestroy();
	}

	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			int result = speech.setLanguage(Locale.US);
			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "Bahasa tidak support");
				Toast.makeText(getApplicationContext(), "Bahasa tidak support",
						Toast.LENGTH_LONG).show();
			} else {
				speak.setEnabled(true);
				speak();
			}
		}
	}

	private void speak() {
		// TODO Auto-generated method stub
		String text = txtInput.getText().toString();
		speech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}
}
