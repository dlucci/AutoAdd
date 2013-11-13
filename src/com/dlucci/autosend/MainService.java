package com.dlucci.autosend;

import java.util.Date;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.IBinder;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
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
            
            Cursor c = cr.query(Phone.CONTENT_URI,
                    new String[]{Phone.NUMBER, Phone.CONTACT_LAST_UPDATED_TIMESTAMP},
                    //Phone.CONTACT_LAST_UPDATED_TIMESTAMP + "=?",
                    //new String[]{String.valueOf(new Date().getTime())},
                    null,
                    null,
                    null);
            Log.i(TAG, String.valueOf(c.getCount()));
            if(c.moveToFirst())
            {
            	while(!c.isAfterLast())
            	{
            		String phoneNum = c.getString(c.getColumnIndex(Phone.NUMBER));
            		String lastUpdate = c.getString(c.getColumnIndex(Phone.CONTACT_LAST_UPDATED_TIMESTAMP));
            		Log.i(TAG, String.valueOf(phoneNum) + "     " + String.valueOf(lastUpdate) + "   " + String.valueOf(new Date().getTime() - Long.valueOf(lastUpdate)));
            		
            		c.moveToNext();
            	}
            }
            /*while(c.moveToFirst())
            {
            	String data = c.getString(c.getColumnIndex(RawContacts.DATA_SET));
            	//Log.i(TAG, String.valueOf(data));
            }*/

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
