package com.mercury.game.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

import com.mercury.game.MercuryActivity;

import org.apache.http.util.EncodingUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.mercury.game.MercuryActivity.LogLocal;

public final class Function {
        public  static void writeFileData(String fileName,String message)
        {
            try{
                //E2WApp.LogLocal("[E2WApp]->writeFileData fileName="+fileName+",message="+message+"-"+E2WApp.mContext);
                FileOutputStream fout = MercuryActivity.mContext.openFileOutput(fileName, MercuryActivity.mContext.MODE_PRIVATE);
                byte [] bytes = message.getBytes();
                fout.write(bytes);
                fout.close();
                //E2WApp.LogLocal("[E2WApp]->writeFileData Success");
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        public static String readFileData(String fileName)
        {

            String res="";
            try
            {
                //E2WApp.LogLocal("[E2WApp]->readFileData:"+fileName+"-"+E2WApp.mContext);
                FileInputStream fin = MercuryActivity.mContext.openFileInput(fileName);
                int length = fin.available();
                byte [] buffer = new byte[length];
                fin.read(buffer);
                res = EncodingUtils.getString(buffer, "UTF-8");
                fin.close();
                //E2WApp.LogLocal("[E2WApp]->readFileData Success");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return res;
        }
        public static void verifyGame(final String gamename)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        //1.创建OkHttpClient对象
                        OkHttpClient client = new OkHttpClient();
                        //2.创建RequestBody对象
                        RequestBody requestBody = new FormBody.Builder()
                                .add("username", "tom")
                                .add("password", "123")
                                .build();
                        //3.创建Request对象
                        Request request = new Request.Builder()
                                .post(requestBody)
                                .url("http://office.singmaan.com:9988/get_update_verify?gamename="+gamename)
                                .build();
                        //4. 同步请求
                        // Response response = client.newCall(request).execute();
                        //5.异步请求
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                LogLocal("[MercuryActivity][GetProductionInfo] failed=");
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String s = response.body().string();
                                if (s != null) {
                                    writeFileData("verifyGame",s);
                                    LogLocal("[MercuryActivity][verifyGame] success="+s);
                                }
                            }
                        });


                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {

                    }
                }
            }).start();
            int remote_version = 0;
            String remote_dialog_message = "";
            String remote_dialog_title = "";
            String remote_url = "";

            //get remote version
            String remote_config = readFileData("verifyGame");
            LogLocal("[MercuryActivity][verifyGame] remote_config="+remote_config);
            try {
                JSONObject json= (JSONObject) new JSONTokener(remote_config).nextValue();
                JSONObject json_data = json.getJSONObject("data");
                JSONObject json_result = json_data.getJSONObject("result");
                remote_version = Integer.parseInt((String) json_result.get("version"));
                remote_url = (String) json_result.get("url");
                remote_dialog_message = (String) json_result.get("message");
                remote_dialog_title = (String) json_result.get("titile");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            //default value
            int local_version = 0;
            String local_dialog_message = "检测到新版本";
            String local_dialog_title = "更新游戏体验有更多游戏内容";
            String local_url = "http://www.singmaan.com";

            String display_dialog_message = "";
            String display_dialog_titile = "";
            String display_url = "";

            if (remote_dialog_message.equals("")==false)
            {
                display_dialog_message = remote_dialog_message;
                display_dialog_titile = remote_dialog_title;
                display_url = remote_url;

            }
            else
            {
                display_dialog_message = local_dialog_message;
                display_dialog_titile = local_dialog_title;
                display_url = local_url;
            }

            //get apk version
            try {
                PackageManager manager = MercuryActivity.mContext.getPackageManager();
                PackageInfo info = manager.getPackageInfo(MercuryActivity.mContext.getPackageName(), 0);
                local_version = info.versionCode;
                Log.d("MercurySDK", "version:"+local_version);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (remote_version>local_version)
            //have new version
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MercuryActivity.mContext);
                builder.setMessage(display_dialog_titile);
                builder.setTitle(display_dialog_message);
                final String finalRemote_url = display_url;
                builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        Uri content_url = Uri.parse(finalRemote_url);//此处填链接
                        intent.setData(content_url);
                        MercuryActivity.mContext.startActivity(intent);
                        ((Activity) MercuryActivity.mContext).finish();
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                });
                builder.setNeutralButton("下次更新,前往游戏", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                            ((Activity) MercuryActivity.mContext).finish();
//                            android.os.Process.killProcess(android.os.Process.myPid());
                    }
                });
                builder.setCancelable(false);
                builder.create().show();

            }
        }

    }
