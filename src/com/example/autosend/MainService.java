package com.example.autosend;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.IBinder;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;


public class MainService extends Service{

	/*
	 * 
	 * 
	 */
	
	public static final String TAG = "MainService";
	private MyContentObserver observer;
	private ContentResolver cr;
	
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		
		Log.d(TAG, "onCreate()");
		observer = new MyContentObserver();
		cr = getContentResolver();
		this.getApplicationContext().getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_URI, false, observer);
		
	}
	
	private class MyContentObserver extends ContentObserver {

        public MyContentObserver() {
            super(null);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            
            Cursor c = cr.query(RawContacts.CONTENT_URI,
                    new String[]{RawContacts.DATA_SET},
                    RawContacts.DIRTY + "=?",
                    new String[]{String.valueOf(1)}, null);
            Log.i(TAG, String.valueOf(c.getCount()));
            while(c.moveToFirst())
            {
            	String data = c.getString(c.getColumnIndex(RawContacts.DATA_SET));
            	Log.i(TAG, String.valueOf(data));
            }

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
