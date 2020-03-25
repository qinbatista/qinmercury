package com.qinbatista.mercury;

import com.mercury.game.MercuryActivity;
import com.mercury.game.util.APPBaseInterface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.demo.game.R;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.game.UMGameAgent;
import com.umeng.commonsdk.UMConfigure;

public class MainActivity extends Activity  {
	public static Context context;
	public MercuryActivity MercurySDK;
	public static APPBaseInterface appcall=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context=this;
		UMGameAgent.init(context);
		Log.w("MercuryDemo","[step][2]init activity");
		MercurySDK=new MercuryActivity();
		Log.w("MercuryDemo","[step][3]init callback");
		appcall = new  APPBaseInterface() {
			
			@Override
			public void onPurchaseSuccessCallBack(String strProductId) {
				// TODO Auto-generated method stub
				UMGameAgent.pay(10,"magic_bottle",2,50,2);
				Log.w("MercuryDemo", "onCreate onPurchaseSuccessCallBack strProductId="+strProductId);
				Toast.makeText(context, "onPurchaseSuccessCallBack",Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onPurchaseFailedCallBack(String strProductId) {
				// TODO Auto-generated method stub
				Log.w("MercuryDemo", "onCreate onPurchaseFailedCallBack strProductId="+strProductId);
				Toast.makeText(context, "onPurchaseFailedCallBack",Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onPurchaseCancelCallBack(String strProductId) {
				// TODO Auto-generated method stub
				Log.w("MercuryDemo", "onCreate onPurchaseCancelCallBack strProductId="+strProductId);
				Toast.makeText(context, "onPurchaseCancelCallBack",Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onLoginCancelCallBack(String arg0) {
				// TODO Auto-generated method stub
				Log.w("MercuryDemo", "onCreate onLoginCancelCallBack arg0="+arg0);
			}

			@Override
			public void onLoadSuccessfulCallBack(String s) {
				Log.w("MercuryDemo", "onCreate onLoginCancelCallBack arg0="+s);
			}

			@Override
			public void onLoadFailedCallBack(String s) {
				Log.w("MercuryDemo", "onCreate onLoginCancelCallBack arg0="+s);
			}

			@Override
			public void onSaveSuccessfulCallBack(String s) {
				Log.w("MercuryDemo", "onCreate onLoginCancelCallBack arg0="+s);
			}

			@Override
			public void onSaveFailedCallBack(String s) {
				Log.w("MercuryDemo", "onCreate onLoginCancelCallBack arg0="+s);
			}

			@Override
			public void onOnVideoCompletedCallBack(String s) {
				Log.w("MercuryDemo", "onCreate onLoginCancelCallBack arg0="+s);
			}

			@Override
			public void onOnVideoFailedCallBack(String s) {
				Log.w("MercuryDemo", "onCreate onLoginCancelCallBack arg0="+s);
			}

			@Override
			public void onLoginFailedCallBack(String arg0) {
				// TODO Auto-generated method stub
				Log.w("MercuryDemo", "onCreate onLoginFailedCallBack arg0="+arg0);
			}

			@Override
			public void onLoginSuccessCallBack(String arg0) {
				// TODO Auto-generated method stub
				Log.w("MercuryDemo", "onCreate onLoginSuccessCallBack arg0="+arg0);
			}

			@Override
			public void onFunctionCallBack(String arg0) {
				// TODO Auto-generated method stub
				Log.w("MercuryDemo", "onCreate onFunctionCallBack arg0="+arg0);
				if(arg0.equals("ExitGame"))
				{
					//exit game by yourself
				}
				else if(arg0.equals("UnlockGame"))
				{
					//unlock game
				}
				else if(arg0.equals("SplashEnd"))
				{
					//splash finished
				}
			}
		};
		Log.w("MercuryDemo","[step][4]Init MercurySDK");
		MercurySDK.InitSDK (context,appcall);

		setContentView(R.layout.activity_main);
		Button btn = (Button) findViewById(R.id.pay);
		// button buy
		btn.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.w("MercuryDemo","[step][5]->purchaseProduct");
				MercurySDK.Purchase("production1");

			}
		});

		// ****************************************exit
		Button exit = (Button) findViewById(R.id.exit);
		exit.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.w("MercuryDemo","[step][6]->ExitGame");
				MercurySDK.ExitGame();
			}
		});

		Button btn1 = (Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.w("MercuryDemo","[step][7]->repairindentRequest");
				MercurySDK.repairindentRequest();
			}
		});
		
		Button btn2 = (Button) findViewById(R.id.button2);
		btn2.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.w("MercuryDemo","[step][8]->respondCPserver");
				MercurySDK.respondCPserver();
				
			}
		});
		Button btn3 = (Button) findViewById(R.id.button3);
		btn3.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.w("MercuryDemo","[step][9]->show_insert");
				MercurySDK.show_insert();
			}
		});
		Button btn4 = (Button) findViewById(R.id.button4);
		btn4.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.w("MercuryDemo","[step][10]->show_banner");
				MercurySDK.show_banner();
			}
		});
		Button btn5 = (Button) findViewById(R.id.Button5);
		btn5.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.w("MercuryDemo","[step][11]->show_video");
				MercurySDK.show_video();
			}
		});
		Button btn6 = (Button) findViewById(R.id.Button6);
		btn6.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.w("MercuryDemo","[step][12]->Exchange");
				MercurySDK.Exchange();
			}
		});
	}



	@Override
	public void onPause()
	{
		super.onPause();
		MercurySDK.onPause();
		MobclickAgent.onPause(this);
		UMGameAgent.onPause(this);
	}

	@Override
	public void onStop()
	{
		super.onStop();
		MercurySDK.onStop();
	}

	@Override
	public void onRestart()
	{
		super.onRestart();
		MercurySDK.onRestart();

	}
	@Override
	public void onResume()
	{
		super.onResume();
		MercurySDK.onResume();
		MobclickAgent.onResume(this);
		UMGameAgent.onResume(this);
	}
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		MercurySDK.onDestroy();
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode,resultCode,data);
		MercurySDK.onActivityResult(requestCode,resultCode,data);
	}
	@Override
	public void onNewIntent(Intent intent)
	{
		super.onNewIntent(intent);
		MercurySDK.onNewIntent(intent);
	}
}