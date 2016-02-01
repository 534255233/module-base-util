package com.zlp.util;

public class CharUtil {
	
	/**
     * 补齐不足长度
     * @param length 长度
     * @param number 数字
     * @return
     */
    private String lpad(int length, int number) {
        String f = "%0" + length + "d";
        return String.format(f, number);
    }
    
    public static void main(String[] args) {
    	CharUtil tt = new CharUtil();
    	String oo = tt.lpad(5, 100);
    	System.out.println(oo);
    }

}
