package com.zlp.util;

import java.io.File;

public class FileUtil {

	// private static Logger log = LoggerFactory.getLogger(TimeUtil.class);

	/**
	 * 描述：删除指定后缀名的文件
	 * 
	 * @param file  一个目录或者文件
	 * @param lastFileName  要删除的文件后缀名
	 */
	public static boolean deleteByFileLastName(File file, String lastFileName) {
		boolean result = true;
		if (file.isFile()) {
			if (file.getName().endsWith(lastFileName)) {
				System.out.println("删除：" + file.getName());
				result = file.delete();
			}
		} else {
			System.out.println("目录：" + file.getName());
			if(false || file.getName().equals("test")) {
				result = deleteDirectory(file);
			} else {
				File[] files = file.listFiles();
				for (File s : files) {
					result = deleteByFileLastName(s, lastFileName);
				}
			}
		}
		return result;
	}
	/**
	 * 描述：删除指定后缀名的文件
	 * 
	 * @param file  一个目录或者文件
	 * @param lastFileName  要删除的文件后缀名
	 */
	public static boolean deleteDojoHelpFile(File file) {
		boolean result = true;
		final String lastFileName = "uncompressed.js";
		if (file.isFile()) {
			if (file.getName().endsWith(lastFileName)) {
				System.out.println("删除：" + file.getName());
				result = file.delete();
			}
		} else {
			System.out.println("目录：" + file.getName());
			if(file.getName().equals("test") || file.getName().equals("tests") || file.getName().equals("demo") || file.getName().equals("demos") || file.getName().equals("doc") || file.getName().equals("docs")) {
				result = deleteDirectory(file);
			} else {
				File[] files = file.listFiles();
				for (File s : files) {
					result = deleteDojoHelpFile(s);
				}
			}
		}
		return result;
	}
	
	public static boolean deleteDirectory(File file) {
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!file.exists() || !file.isDirectory()) return false;
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = file.listFiles();  
	    boolean flag = true;
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	        	files[i].delete();
	        } else { //删除子目录
	            flag = deleteDirectory(files[i]);  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) return false;  
	    //删除当前目录  
	    return file.delete();
	}
	
}
