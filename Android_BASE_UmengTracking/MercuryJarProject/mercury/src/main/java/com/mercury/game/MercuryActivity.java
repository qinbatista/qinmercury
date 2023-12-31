package com.mercury.game;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.support.v4.app.ActivityCompat;
import com.mercury.game.InAppAdvertisement.InAppAD;
import com.mercury.game.InAppChannel.InAppChannel;
import com.mercury.game.util.APPBaseInterface;
import com.mercury.game.util.Function;
import com.mercury.game.util.InAppBase;
import com.mercury.game.util.MD5;
import com.mercury.game.util.MercuryConst;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.game.UMGameAgent;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.mercury.game.MercuryApplication.OpenUmeng;
import static com.mercury.game.util.Function.readFileData;
import static com.mercury.game.util.Function.writeFileData;

public class MercuryActivity  {

	public static Context mContext = null;
	private InAppChannel mInAppChannel;
	public InAppAD mInAppAD;


	public static int mSimOperatorId;
	private int mChannelId;
	private int mExtSDKId = -1;
	public static String ChannelForServer;
	//private String msg_string;
	//public static int msg;
	public static String nikeString;	
	public int platform;
	public static String packagenameforuse;
	public static String isLogOpen="";
	public static MercuryActivity activityforappbase=null;
	public static int Platform=-1;
	public static String DeviceId="";
	public static String SavePidName="";
	public static String SortChannelID="";
	public static String LongChannelID="";
	private static ImageView img = null;
	public static UMGameAgent umgameaget = null;
	public void InitSDK(Context ContextFromUsers,final APPBaseInterface appcall)
	{
		getDeviceId((Activity) mContext);
		LogLocal("[MercuryActivity][InitSDK] 2.0");
		if (OpenUmeng ==true) {
			umgameaget.init(ContextFromUsers);
		}
		mContext = ContextFromUsers;
		ChannelSplash();
		mInAppChannel = new InAppChannel() ;
		mInAppAD= new InAppAD() ;
		activityforappbase=this;
		InitChannel(appcall);
		InitAd(appcall);

	}
	public void ChannelSplash()
	{
		LogLocal("[MercuryActivity][ChannelSplash] ChannelSplash.png");
		try {
			final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.MATCH_PARENT,
					RelativeLayout.LayoutParams.MATCH_PARENT);
			String name = "ChannelSplash.png";
			InputStream in = mContext.getResources().getAssets().open(name);
			if (in != null) {
				final Bitmap bitmap = BitmapFactory.decodeStream(in);
				// activity.getWindow().getDecorView().getHandler().postDelayed(
				((Activity) mContext).runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						final ImageView image = new ImageView(mContext);
						image.setImageBitmap(bitmap);
						((Activity) mContext).addContentView(image, params);
						image.setScaleType(ScaleType.FIT_XY);
						new Thread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								try {
									Thread.sleep(3000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								((Activity) mContext).runOnUiThread(new Runnable() {
									@Override
									public void run() {
										ViewGroup vg = (ViewGroup) image
												.getParent();
												if (vg!=null)
												{vg.removeView(image);}
									}
								});
							}

						}).start();
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogLocal("[MercuryActivity][ChannelSplash] init e="+e.toString());
		}
	}
 	public String GetUniqueID(){
 	    String id="";   
 	    //获取当前时间戳         
 	    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");    
 	    String temp = sf.format(new Date());    
 	    //获取6位随机数  
 	    int random=(int) ((Math.random()+1)*100000);    
 	    id=temp+random;    
 	    return id;    
 	}

	public static String getDeviceId(Activity context) {

		String strUserID = "";
		String imei = "";

		try {
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
				// TODO: Consider calling
				//    ActivityCompat#requestPermissions
				// here to request the missing permissions, and then overriding
				//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
				//                                          int[] grantResults)
				// to handle the case where the user grants the permission. See the documentation
				// for ActivityCompat#requestPermissions for more details.
				imei = tm.getDeviceId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LogLocal("[getDeviceId] -imei = ["+imei+"]");
		LogLocal("[getDeviceId] -readFileData(\"UserIMEI\") = ["+readFileData("UserIMEI")+"]");
		if((imei==null||imei=="")&&(readFileData("UserIMEI")==null||readFileData("UserIMEI")==""))
		{
			imei=MercuryApplication.channel_name+java.util.UUID.randomUUID();
			writeFileData("UserIMEI",imei);
			strUserID=imei;
			LogLocal("[getDeviceId] write imei = ["+imei+"]");
		}
		else if((imei==null||imei=="")&&(readFileData("UserIMEI")!=null||readFileData("UserIMEI")!=""))
		{
			imei=readFileData("UserIMEI");
			strUserID=imei;
			LogLocal("[getDeviceId] read imei = ["+imei+"]");
		}
		else if((imei!=null||imei!="")&&(readFileData("UserIMEI")!=null||readFileData("UserIMEI")!=""))
		{
			strUserID=readFileData("UserIMEI");
			LogLocal("[getDeviceId] Set imei as local imei = ["+strUserID+"]");
		}
		else
		{
			strUserID=imei;
			LogLocal("[getDeviceId] Set imei as phone = ["+imei+"]");
		}
		LogLocal("[getDeviceId] strUserID = ["+strUserID+"]");
		DeviceId= MD5.getMessageDigest(strUserID.getBytes());
		LogLocal("[getDeviceId] Get DeviceId = ["+DeviceId+"]");
		return DeviceId;

	}

	public void InitChannel(final APPBaseInterface appcall)
	{
		final Context applicationContext = mContext.getApplicationContext();		
		LogLocal("[MercuryActivity][InitChannel] Local InitChannel()->"+mInAppChannel);
		mInAppChannel.ActivityInit((Activity)mContext, appcall);
	
	}
	public void InitAd(final APPBaseInterface appcall)
	{
		final Context applicationContext = mContext.getApplicationContext();
		LogLocal("[MercuryActivity][InitAd] Local InitAd()->"+mInAppAD);
		mInAppAD.ActivityInit((Activity)mContext,appcall);

	}
	public void Purchase(String pidname)
	{
		LogLocal("[MercuryActivity][Purchase] " + pidname);
		mInAppChannel.Purchase(pidname);
	}
	public void RestoreProduct()
	{
		LogLocal("[MercuryActivity][RestoreProduct] ");
		mInAppChannel.RestoreProduct();
	}
	public void ExitGame()
	{
		LogLocal("[MercuryActivity][ExitGame] ");
		new Handler(mContext.getMainLooper()).post(new Runnable() {
			@Override
			public void run() 
			{
				mInAppChannel.ExitGame();
			}
			});
	}
	public void Redeem()
	{
		LogLocal("[MercuryActivity][Redeem] ");
		new Handler(mContext.getMainLooper()).post(new Runnable() {
			@Override
			public void run()
			{

			}
		});
	}

	public String ShortChannelID()
	{
		return SortChannelID;
	}
	public String LongChannelID()
	{
		return LongChannelID;
	}

	public void ActiveBanner()
	{
		LogLocal("[MercuryActivity][ActiveBanner]" + mInAppAD);
		if(mInAppAD != null) { mInAppAD.ActiveBanner(); }
	}
	public void ActiveInterstitial()
	{
		LogLocal("[MercuryActivity][ActiveInterstitial]" + mInAppAD);
		if(mInAppAD != null) { mInAppAD.ActiveInterstitial(); }
	}
	public void ActiveNative()
	{
		LogLocal("[MercuryActivity][ActiveNative]" + mInAppAD);
		if(mInAppAD != null) { mInAppAD.ActiveNative(); }
	}
	public void ActiveRewardVideo()
	{
		LogLocal("[MercuryActivity][ActiveRewardVideo]" + mInAppAD);
		if(mInAppAD != null) { mInAppAD.ActiveRewardVideo(); }
	}
	public static Object getInstance() {
		Platform=MercuryConst.Unity;
		return mContext;
	}
	public int getChannelId() {
		return mChannelId;
	}

	public InAppBase getmInAppChannel()
	{
		LogLocal("[MercuryActivity] getBaseInApp()->mInApp="+mInAppChannel);
		return mInAppChannel;
	}
	public InAppBase getmInAppAD()
	{
		LogLocal("[MercuryActivity] getBaseInApp()->mInApp="+mInAppAD);
		return mInAppAD;
	}
	public void showMessageDialog()
	{
		LogLocal("[MercuryActivity]->showMessageDialog:mInAppChannel="+mInAppChannel);
		if(mInAppChannel != null)
		{
			mInAppChannel.showMessageDialog();
		}
	}
	public void Message(final String news)
	{
		Toast.makeText(mContext, news,Toast.LENGTH_SHORT).show();
	}
	public static void LogLocal(final String news)
	{
		Log.w("MercurySDK",news);
	}

	public void onPause() {
		if (OpenUmeng ==true) {
			MobclickAgent.onPause(mContext);
			UMGameAgent.onPause(mContext);
		}
		if(mInAppChannel != null) { LogLocal("[MercuryActivity] mInAppChannel onPause()->" + mInAppChannel);mInAppChannel.onPause();}
		if(mInAppAD != null) { LogLocal("[MercuryActivity] mInAppAD onPause()->" + mInAppAD);mInAppAD.onPause();}
	}
	public void onStop() {

		if(mInAppChannel != null) { LogLocal("[MercuryActivity] mInAppChannel onStop()->" + mInAppChannel);mInAppChannel.onStop();}
		if(mInAppAD != null) { LogLocal("[MercuryActivity] mInAppAD onStop()->" + mInAppAD);mInAppAD.onStop();}
	}
	public void onStart() {

		if(mInAppChannel != null) { LogLocal("[MercuryActivity] mInAppChannel onStart()->" + mInAppChannel);mInAppChannel.onStart();}
		if(mInAppAD != null) { LogLocal("[MercuryActivity] mInAppAD onStart()->" + mInAppAD);mInAppAD.onStart();}

	}
	public void onRestart()
	{
		if(mInAppChannel != null) { LogLocal("[MercuryActivity] mInAppChannel onRestart()->" + mInAppChannel);mInAppChannel.onRestart();}
		if(mInAppAD != null) { LogLocal("[MercuryActivity] mInAppAD onRestart()->" + mInAppAD);mInAppAD.onRestart();}
	}
	public void onResume()
	{
		if (OpenUmeng ==true) {
			MobclickAgent.onResume(mContext);
			UMGameAgent.onResume(mContext);
		}
		if(mInAppChannel != null) { LogLocal("[MercuryActivity] mInAppChannel onResume()->" + mInAppChannel);mInAppChannel.onResume();}
		if(mInAppAD != null) { LogLocal("[MercuryActivity] mInAppAD onResume()->" + mInAppAD);mInAppAD.onResume();}
	}

	public void onDestroy()
	{
		if(mInAppChannel != null) { LogLocal("[MercuryActivity] mInAppChannel onDestroy()->" + mInAppChannel);mInAppChannel.onDestroy();}
		if(mInAppAD != null) { LogLocal("[MercuryActivity] mInAppAD onDestroy()->" + mInAppAD);mInAppAD.onDestroy();}
	}
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(mInAppChannel != null) { LogLocal("[MercuryActivity]->onActivityResult:mInAppChannel="+mInAppChannel);mInAppChannel.onActivityResult(requestCode,resultCode,data); }
		if(mInAppAD != null) { LogLocal("[MercuryActivity]->onActivityResult:mInAppChannel="+mInAppAD);mInAppAD.onActivityResult(requestCode,resultCode,data); }
	}
	public void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		if(mInAppChannel != null) { LogLocal("[MercuryActivity]->onNewIntent:mInAppChannel="+mInAppChannel);mInAppChannel.onNewIntent(intent); }
		if(mInAppAD != null) { LogLocal("[MercuryActivity]->onNewIntent:mInAppAD="+mInAppAD);mInAppAD.onNewIntent(intent); }
	}
	
	


}
