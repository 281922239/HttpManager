package com.simonzl.httpmanager;

import java.io.InputStream;
/**
 * TaskHandler<T> ���࣬����Tָ��ΪInputStream
 * @author SimonZl
 *
 */
public abstract class InputStreamTaskHandler extends TaskHandler<InputStream>{

	@Override
	public InputStream parseResult(InputStream result) {
		// TODO Auto-generated method stub
		return result;
	}
}
