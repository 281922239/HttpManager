package com.simonzl.httpmanager;

import java.io.InputStream;
/**
 * ����������ɳ�����
 * @author SimonZl
 *
 * @param <T>
 */
public abstract class TaskHandler<T> {
	/**����ɹ� */
    public abstract void onSuccess(T result);  
    /**����ʧ�� */
    public abstract void onFail();  
    /**ת��InputStream���ͷ���ֵ�ɷ���T */
    public abstract T parseResult(InputStream result);  
}
