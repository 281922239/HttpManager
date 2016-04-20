package com.simonzl.httpmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
/**
 * �����жϹ�����
 * @author SimonZl
 *
 */
public class NetworkHttpUtils {

	private static final String TAG = "NetworkHttpUtils";
	
	/** 
	 * ����������״̬�����ж� 
	 * @return  true, ���ã� false�� ������ 
	 */  
	public static boolean isOpenNetwork(Context context) {  
	    ConnectivityManager connManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
	    if(activeNetwork != null && activeNetwork.isConnected()) {
	    	boolean isAvailable = activeNetwork.isAvailable();
	    	Log.d(TAG, isAvailable+"");
	        return isAvailable;
	    }  
	    return false;  
	}
}
