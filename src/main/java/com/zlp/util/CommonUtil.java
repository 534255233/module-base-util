package com.zlp.util;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class CommonUtil {
	
	//private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	
	/**
	 * 替换url中的特殊字符
	 * @return
	 */
	public static String replaceUrlSpecificSymbol (String args) {
    	if(args == null) return "";
    	args = args.replaceAll("\\%", "%25");
    	args = args.replaceAll("\\+", "%2B");
    	args = args.replaceAll(" ", "%20");
    	args = args.replaceAll("/", "%2F");
    	args = args.replaceAll("\\?", "%3F");
    	args = args.replaceAll("\\#", "%23");
    	args = args.replaceAll("\\&", "%26");
    	args = args.replaceAll("\\=", "%3D");
    	return args;
    }
	
//	public static void main(String[] args) {
//		System.out.println("begin");
//		final String gg = "a%-+-/-?-#-&-=";
//		String ll = replaceUrlSpecificSymbol(gg);
//		System.out.println(ll);
//		
//	}
	
	/**
	 * 
	 * @param arg
	 * @return
	 */
	public static String symbol(int arg) {
		switch(arg) {
		case 1 : return "$gt";
		case 0 : return "$eq";
		case -1 : return "$lt";
		}
		return "$eq";
	}
	
	public static Map<String, Object> mapQuery(String field, String symbol, Object val) {
		Map<String, Object> query = new HashMap<>();
		query.put("field", field);
		query.put("operator", symbol);
		query.put("val", val);
		return query;
	}
	
	/**授权允许*/
	public static final int ACL_YES = 1;
	/**授权不允许*/
	public static final int ACL_NO = 0;
	public static final int PERMISSION_CREATE = 0;
	public static final int PERMISSION_READ = 1;
	public static final int PERMISSION_UPDATE = 2;
	public static final int PERMISSION_DELETE = 3;
	/**
	 * acl实例跟主体和资源关联
	 * 根据此实例进行授权：某种操作是否允许
	 * @param permission 只可取值0，1，2，3
	 * @param yes true表示允许，false表示不允许
	 */
	public static int setPermission(int permission, boolean yes, int aclState) {
		int temp = 1;
		temp = temp << permission;
		if(yes) {
			aclState |= temp;
		} else {
			aclState &= ~temp;
		}
		return aclState;
	}
	/**
	 * 获得ACL授权
	 * @param permission C/R/U/D权限
	 * @return 授权标识，允许/不允许
	 */
	public static int getPermission(int permission, int aclState) {
		int temp = 1;
		temp = temp << permission;
		temp &= aclState;
		if(temp != 0) {
			return ACL_YES;
		}
		return ACL_NO;
	}
	
	public static String configFileName() {
		return ResourceBundle.getBundle("system").getString("run.config.file.name");
	}

}
