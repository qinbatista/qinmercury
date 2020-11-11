package com.mercury.game.InAppAdvertisement;
import com.mercury.game.MercuryApplication;
import com.mercury.game.util.APPBaseInterface;
import com.mercury.game.util.InAppBase;
import com.mercury.game.MercuryActivity;
//comment
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
//end

public class InAppAD extends InAppBase {
	//comment
	public static String appShow="InAppAD";
	public static String MyScence = "";
	public void ActivityInit(Activity context,final APPBaseInterface appcall)
	{
		super.ActivityInit(context, appcall);
		MercuryActivity.LogLocal("[InAppAD][ActivityInit]="+appShow);
	}
	@Override
	public void ApplicationInit(Application app)
	{
		MercuryActivity.LogLocal("[InAppAD][ApplicationInit]="+appShow);
	}

	@Override
	public void onPause()
	{
		MercuryActivity.LogLocal("["+appShow+"][onPause]");
	}


	@Override
	public void onResume()
	{
		MercuryActivity.LogLocal("["+appShow+"][onResume]");
	}

	@Override
	public void onDestroy()
	{
		MercuryActivity.LogLocal("["+appShow+"][onDestroy]");
	}
	@Override
	public void onStop()
	{
		MercuryActivity.LogLocal("["+appShow+"][onStop]");
	}
	@Override
	public void onStart()
	{
		MercuryActivity.LogLocal("["+appShow+"][onStart]");
	}
	public void onTerminate()
	{

	}
	public void onActivityResult(int reqCode, int resCode, Intent data) {
		MercuryActivity.LogLocal("["+appShow+"]->onActivityResult");
		super.onActivityResult(reqCode, resCode, data);
	}

	@Override
	public void attachBaseContext(Context base)
	{
		super.attachBaseContext(base);
	}
	public void ActiveInterstitial() {
		// TODO Auto-generated method stub
		MercuryActivity.LogLocal("["+appShow+"] ActiveInterstitial");
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setMessage("Testing Mode");
		builder.setTitle("ActiveInterstitial");
		builder.setPositiveButton("Success", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				AdShowSuccessCallBack("ActiveInterstitial");
				AdLoadSuccessCallBack("ActiveInterstitial");
			}
		});
		builder.setNeutralButton("Failed", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				AdShowFailedCallBack("ActiveInterstitial");
			}
		});
		builder.setCancelable(false);
		builder.create().show();
	}
	public void ActiveBanner() {
		// TODO Auto-generated method stub
		MercuryActivity.LogLocal("["+appShow+"] ActiveBanner");
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setMessage("Testing Mode");
		builder.setTitle("ActiveBanner");
		builder.setPositiveButton("Success", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				AdShowSuccessCallBack("ActiveBanner");
				AdLoadSuccessCallBack("ActiveBanner");
			}
		});
		builder.setNeutralButton("Failed", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				AdShowFailedCallBack("ActiveBanner");
			}
		});
		builder.setCancelable(false);
		builder.create().show();
	}
	public void ActiveNative() {
		// TODO Auto-generated method stub
		MercuryActivity.LogLocal("["+appShow+"] ActiveNative");
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setMessage("Testing Mode");
		builder.setTitle("ActiveNative");
		builder.setPositiveButton("Success", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				AdShowSuccessCallBack("ActiveNative");
				AdLoadSuccessCallBack("ActiveNative");
			}
		});
		builder.setNeutralButton("Failed", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				AdShowFailedCallBack("ActiveNative");
			}
		});
		builder.setCancelable(false);
		builder.create().show();
	}
	public void ActiveRewardVideo() {
		// TODO Auto-generated method stub
		MercuryActivity.LogLocal("["+appShow+"] ActiveRewardVideo");
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setMessage("Testing Mode");
		builder.setTitle("ActiveRewardVideo");
		builder.setPositiveButton("Success", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				AdShowSuccessCallBack("ActiveRewardVideo");
				AdLoadSuccessCallBack("ActiveRewardVideo");
			}
		});
		builder.setNeutralButton("Failed", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				AdShowFailedCallBack("ActiveRewardVideo");
			}
		});
		builder.setCancelable(false);
		builder.create().show();
	}
	//end
}

