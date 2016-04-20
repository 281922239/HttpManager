package com.simonzl.httpmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ����ҳ�棬���в���url�����Լ���url���Լ���Ҫ���ݹ�ȥ�Ĳ���
 * @author SimonZl
 * 
 */
public class MainActivity extends Activity implements OnClickListener {

	public static final String TAG = "MainActivity";
	/** �ļ�ѡ�������� */
	public static final int FILE_SELECT_CODE = 200;

	// ���������װ��
	private HttpManager httpManager;
	private TextView text_Result;
	private Button Btn_Get, Btn_Post, Btn_uploadFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	/**
	 * ��ʼ���ؼ�
	 */
	private void initView() {
		text_Result = (TextView) findViewById(R.id.text_Result);
		Btn_Get = (Button) findViewById(R.id.Btn_Get);
		Btn_Get.setOnClickListener(this);
		Btn_Post = (Button) findViewById(R.id.Btn_post);
		Btn_Post.setOnClickListener(this);
		Btn_uploadFile = (Button) findViewById(R.id.Btn_uploadFile);
		Btn_uploadFile.setOnClickListener(this);
		// ��ʼ��HttpManager
		httpManager = new HttpManager(this);
	}

	/**
	 * Get����
	 */
	private void get() {
		// ���ʵ�url
		String url = "";
		httpManager.requestGet(url, new StringTaskHandler() {

			@Override
			public void onSuccess(String result) {
				System.out.println(result);
				text_Result.setText(result);
			}

			@Override
			public void onFail() {
				Toast.makeText(MainActivity.this, "�����쳣��������������", Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**
	 * post����
	 */
	private void post() {
		// ���ʵ�url
		String url = "";
		// ���ݹ�ȥ�Ĳ�������
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("", ""));
		// ����
		httpManager.requestPost(url, params, new StringTaskHandler() {

			@Override
			public void onSuccess(String result) {
				System.out.println(result);
				text_Result.setText(result);
			}

			@Override
			public void onFail() {
				Toast.makeText(MainActivity.this, "�����쳣��������������", Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**
	 * ���ļ�ѡ����ʾ��
	 */
	private void showFileChooser() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("*/*");
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
	}

	/**
	 * �ϴ��ļ�����
	 * @param filePath �ļ�·��
	 */
	private void uploadFile(String filePath) {
		// ���ʵ�url
		String url = "";
		// �ϴ��ļ���Ӧ�Ĳ�����
		String uploadName = "file1";
		// ���ݸ�����˵�����
		HashMap<String, String> hashMap = new HashMap<String, String>();
		// �ļ�·������
		List<String> list = new ArrayList<String>();
		list.add(filePath);
		// ����
		httpManager.requestUpLoad(url, hashMap, list, uploadName, new StringTaskHandler() {

					@Override
					public void onSuccess(String result) {
						System.out.println(result);
						text_Result.setText(result);
					}

					@Override
					public void onFail() {
						Toast.makeText(MainActivity.this, "�����쳣��������������",
								Toast.LENGTH_SHORT).show();
					}
				});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.Btn_Get:
			get();
			break;
		case R.id.Btn_post:
			post();
			break;
		case R.id.Btn_uploadFile:
			showFileChooser();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case FILE_SELECT_CODE: 
			// �õ��ļ�
			if (resultCode == RESULT_OK) {
				Uri uri = data.getData();
				// ����Uri�õ���Ӧ���ļ�·��
				String path = FileUtils.getPath(this, uri);
				// ��Ϊ��
				if (!TextUtils.isEmpty(path)) {
					// �ϴ��ļ�
					uploadFile(path);
					System.out.println(path);
					text_Result.setText(path);
				}
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
