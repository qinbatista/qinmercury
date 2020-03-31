package com.singmaan.game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mercury.game.MercuryActivity;
import com.mercury.game.util.APPBaseInterface;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

public class GameActivity extends UnityPlayerActivity{
	public static GameActivity mContext = null;
	public MercuryActivity MercurySDK;
	public static APPBaseInterface appcall=null;
	private String callBackObjectName="PluginMercury";
	private static String tagname="PluginMercury";
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		mContext=this;
		MercurySDK=new MercuryActivity();
		appcall = new  APPBaseInterface() {
			@Override
			public void PurchaseSuccessCallBack(String message) {
				Log.w(tagname, "[GameActivity]PurchaseSuccessCallBack message="+message);
				UnityPlayer.UnitySendMessage(callBackObjectName, "PurchaseSuccessCallBack", message);
			}
			@Override
			public void PurchaseFailedCallBack(String message) {
				Log.w(tagname, "[GameActivity]PurchaseFailedCallBack message="+message);
				UnityPlayer.UnitySendMessage(callBackObjectName, "PurchaseFailedCallBack", message);
			}
			@Override
			public void LoginSuccessCallBack(String message) {
				Log.w(tagname, "[GameActivity]LoginSuccessCallBack message="+message);
				UnityPlayer.UnitySendMessage(callBackObjectName, "LoginSuccessCallBack", message);
			}
			@Override
			public void LoginCancelCallBack(String message) {
				Log.w(tagname, "[GameActivity]LoginCancelCallBack message="+message);
				UnityPlayer.UnitySendMessage(callBackObjectName, "LoginCancelCallBack", message);
			}
			@Override
			public void AdLoadSuccessCallBack(String message) {
				Log.w(tagname, "[GameActivity]AdLoadSuccessCallBack message="+message);
				UnityPlayer.UnitySendMessage(callBackObjectName, "AdLoadSuccessCallBack", message);
			}
			@Override
			public void AdLoadFailedCallBack(String message) {
				Log.w(tagname, "[GameActivity]AdLoadFailedCallBack message="+message);
				UnityPlayer.UnitySendMessage(callBackObjectName, "AdLoadFailedCallBack", message);
			}
			@Override
			public void AdShowSuccessCallBack(String message) {
				Log.w(tagname, "[GameActivity]AdShowSuccessCallBack message="+message);
				UnityPlayer.UnitySendMessage(callBackObjectName, "AdShowSuccessCallBack", message);
			}
			@Override
			public void AdShowFailedCallBack(String message) {
				Log.w(tagname, "[GameActivity]AdShowFailedCallBack message="+message);
				UnityPlayer.UnitySendMessage(callBackObjectName, "AdShowFailedCallBack", message);
			}
			@Override
			public void onFunctionCallBack(String message) {
				Log.w(tagname, "[GameActivity]onFunctionCallBack message="+message);
				UnityPlayer.UnitySendMessage(callBackObjectName, "onFunctionCallBack", message);
			}
		};
		Log.w(tagname,"[step][4]Init MercurySDK");
		MercurySDK.InitSDK (mContext,appcall);
	}

	public static Object getInstance() {
		Log.e(tagname,"getInstance=" + mContext);
		return mContext;
	}

	public void Purchase(final String strProductId)
	{
		UnityPlayer.currentActivity.runOnUiThread(new Runnable()
		{
			@Override
			public void run()
			{
				mContext.runOnUiThread(new Runnable() {
					public void run() {
						Log.e(tagname, "purchaseProduct=="+strProductId);
						MercurySDK.Purchase(strProductId);
					}
				});
			}
		});
	}

	public void show_video()
	{
		UnityPlayer.currentActivity.runOnUiThread(new Runnable()
		{
			@Override
			public void run()
			{
				mContext.runOnUiThread(new Runnable() {
					public void run() {
						Log.e(tagname,"ShowVideoAd");
						MercurySDK.show_video();
					}
				});
			}
		});
	}

	public void Exchange()
	{
		UnityPlayer.currentActivity.runOnUiThread(new Runnable()
		{
			@Override
			public void run()
			{
				mContext.runOnUiThread(new Runnable() {
					public void run() {
						Log.e(tagname,"Exchange");
						MercurySDK.Exchange();
					}
				});
			}
		});
	}

	public void ExitGame()
	{
		UnityPlayer.currentActivity.runOnUiThread(new Runnable()
		{
			@Override
			public void run()
			{
				mContext.runOnUiThread(new Runnable() {
					public void run() {
						Log.e(tagname,"Exit");
						MercurySDK.ExitGame();
					}
				});
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.e(tagname, "onActivityResult(int requestCode, int resultCode, Intent data) ");
		MercurySDK.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		Log.e(tagname, "onNewIntent");
		MercurySDK.onNewIntent(intent);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(tagname, "onStart()");
		MercurySDK.onStart();
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(tagname, "onPause()");
		MercurySDK.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(tagname, "onStop()");
		MercurySDK.onStop();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(tagname, "onRestart()");
		MercurySDK.onRestart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(tagname, "onResume()");
		MercurySDK.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(tagname, "onDestroy()");
		MercurySDK.onDestroy();
	}

}
