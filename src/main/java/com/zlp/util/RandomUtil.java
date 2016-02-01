package com.zlp.util;

public class RandomUtil {

	private final static String AZ09 = "abcdefghijklmnopqrstuvwxyz0123456789";
	
	private static int genRandomInt(int count) {
		return (int) Math.round(Math.random() * (count));
	}
	
	public static String genRandomString(int length){
	    StringBuffer sb = new StringBuffer();
	    int len = AZ09.length() - 1;
	    for (int i = 0; i < length; i++) {
	    	final char tmp = AZ09.charAt(genRandomInt(len));
	    	//没有重复的字符串
//	    	System.out.println("i="+i);
	    	if(sb.indexOf(tmp+"") > 0) {
	    		i--;
	    		continue;
	    	}
	        sb.append(tmp);
	    }
	    return sb.toString();
	}
	
	public static void main(String[] args) {
		String tt = RandomUtil.genRandomString(8);
		System.out.println(tt);
	}
	
}
