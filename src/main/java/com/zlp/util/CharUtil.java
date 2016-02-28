package com.zlp.util;

public class CharUtil {
	
	private static final String az09 = "abcdefghijklmnopqrstuvwxyz0123456789";
	
	/**
     * 补齐不足长度
     * @param length 长度
     * @param number 数字
     * @return
     */
    public static String lpad(int length, int number) {
        String f = "%0" + length + "d";
        return String.format(f, number);
    }
    
    public static String genRandomChar(final int length) {
    	StringBuffer sb = new StringBuffer();
    	for(int i = 0; i < length; i++) {
    		sb.append(az09.charAt(getRandom(35)));
    	}
    	return sb.toString();
    }
    
    private static int getRandom(int count) {
    	return (int) Math.round(Math.random() * (count));
    }
    
    public static void main(String[] args) {
//    	CharUtil tt = new CharUtil();
//    	String oo = tt.lpad(5, 100);
//    	System.out.println(oo);
    	for(int i = 0; i < 20; i++) {
    		System.out.println(genRandomChar(16));
    	}
    }

}
