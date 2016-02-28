package com.zlp.util;

/**
 * 描述：保存当前线程数据
 * 例如：保存登录用户信息
 * @author zhoulongpeng
 *
 */
public class CurrentThreadUtil {
	
	private final static ThreadLocal<Object> subject = new ThreadLocal<Object>();
	private final static ThreadLocal<Boolean> subjectDirty = new ThreadLocal<Boolean>();
	
	protected static void internalSetSubject(Object value){
		subject.set(value);
	}
	
	public static void markDirty(){
		subjectDirty.set(true);
	}
	
	public static boolean isSubjectDirty(){
		Boolean result= subjectDirty.get();
		return result==null?false:result.booleanValue();
	}
	
	public static void setSubject(Object value){
		internalSetSubject(value);
		markDirty();
	}
	
	public static Object getSubject(){
		return subject.get();
	}
	
	public static void remove(){
		subject.remove();
		subjectDirty.remove();
	}
	
}
