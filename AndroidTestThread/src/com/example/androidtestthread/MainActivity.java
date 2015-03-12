package com.example.androidtestthread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

	private final static String TAG = MainActivity.class.getSimpleName();
	private TextView 			tv;
	private int 				i = 0;
	private static final int	REFRESH = 0x000001;
	private Handler 			myHandler = null;
	private static final int 	STOP = 0x000002;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	
	private void initView() {
		tv = (TextView) findViewById(R.id.tv);
		
		myHandler = new Handler(){
			public void handleMessage(Message msg) {
				if(msg.what == REFRESH){
					tv.setText(i + "");
				}
			};
		};
		
		new MyThread().start();
	}
	
	private class MyThread extends Thread{
		@Override
		public void run() {
			super.run();
			while (!Thread.currentThread().isInterrupted()) {
				i ++;
				Message msg = new Message();
				msg.what = REFRESH;
				myHandler.sendMessage(msg);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if(i == 10){
					i = 0;
				}
				
			}
		}
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tv:
			Log.d(TAG, "onCLick TextView");
			break;

		default:
			break;
		}
	}
}
