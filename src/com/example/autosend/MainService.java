package com.example.autosend;

import android.app.Service;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.IBinder;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.util.Log;


public class MainService extends Service{

	/*
	 * 
	 * 
	 */
	
	public static final String TAG = "MainService";
	private MyContentObserver observer;
	@Override
	public void onCreate()
	{
		super.onCreate();
		
		Log.d(TAG, "onCreate()");
		observer = new MyContentObserver();
		this.getApplicationContext().getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_URI, false, observer);
		
	}
	
	private class MyContentObserver extends ContentObserver {

        public MyContentObserver() {
            super(null);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Log.i(TAG, "contact change!");
        }
	}
	
	@Override
	public void onDestroy()
	{
		
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
