package com.example.get_together;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.os.Handler;
import android.widget.*;
import android.util.Log;
import android.view.View;
import android.view.Window;

public class LoginActivity extends Activity implements View.OnClickListener{
	private static final String TAG = "LoginTest";
	private Handler loginHandler;
	private EditText mail;
	private EditText pws;
	private Button btn_login;
	String userMail;
	String password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		Log.i(TAG, "in the login");
		mail = (EditText) this.findViewById(R.id.editText1);
        pws = (EditText) this.findViewById(R.id.editText2);
        btn_login = (Button) this.findViewById(R.id.button1);
        btn_login.setOnClickListener(this);
	}
	
	//if you do the network access in the main thread 
	//you'll the wrong log massage: android.os.NetworkOnMainThreadException
	//I was stuck on this problem for no less than 3 hours...
	class LoginService implements  Runnable
	{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			userMail = mail.getText().toString().trim();
			password = pws.getText().toString().trim();
			Log.i(TAG, "in the login");
			//connect to server
			String connectURL="http://172.19.208.114/get_together/login.php/";
			
			//send the mail and password to server.
			boolean isLoginSucceed = gotoLogin(userMail, password,connectURL);
			//if login succeed
			if(isLoginSucceed){
				Intent intent = new Intent();
				//test
				intent.setClass(getApplicationContext(), MenuActivity.class);
				//intent.setClass(getApplicationContext(), EventActivity.class);
				startActivity(intent);
			}else{
				//Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
				//System.out.println("");
			}
		}
	}
	
	//login function
	private boolean gotoLogin(String userMail, String password,String connectUrl) {
		String result = null; //
		boolean isLoginSucceed = false;
		//test
		System.out.println("userMail:"+userMail);
		System.out.println("password:"+password);
		//send Http Post request
		HttpPost httpRequest = new HttpPost(connectUrl);
		//use NameValuePair[] to store the params which need to be sent.
		List params = new ArrayList();
		params.add(new BasicNameValuePair("mail",userMail));
		params.add(new BasicNameValuePair("pwd",password));
		try{
			//send HTTP request
			httpRequest.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
			//get HTTP response
			HttpResponse httpResponse=new DefaultHttpClient().execute(httpRequest);
			// if succeed
			if(httpResponse.getStatusLine().getStatusCode()==200){
				//get the string comes from server.
				result=EntityUtils.toString(httpResponse.getEntity());
				System.out.println("result= "+result);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		if(result.equals("login succeed")){
			isLoginSucceed = true;
		}
		return isLoginSucceed;
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			LoginService login_thread = new LoginService();
			new Thread(login_thread).start();
		}
		
}
