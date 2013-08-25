package com.example.autosend;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import com.example.autosend.R;

public class MainActivity extends Activity implements OnClickListener{

	private static final String TAG = "MainActivity";

    private EditText editText;
	private Button done;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate()");

        setContentView(R.layout.activity_main);
        
        editText = (EditText) findViewById(R.id.messageText);
        done = (Button) findViewById(R.id.done);
        done.setOnClickListener(this);
        
        Intent i = new Intent(this, MainService.class);
        startService(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public void onClick(View target)
    {
    	if(target == done)
    	{
    		String text = editText.getText().toString();
    		Log.i(TAG, String.valueOf(text));
    	}
    }
    
}
