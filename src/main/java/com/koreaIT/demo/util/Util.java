package com.koreaIT.demo.util;

public class Util {
	public static boolean empty(String str) {
		if (str == null) {
			return true;
		}
		
		return str.trim().length() == 0;
	}
	
	public static String f(String format, Object... args) {
		return String.format(format, args);
	}
	
	public static String jsHistoryBack(String msg) {
		if (msg == null) {
			msg = "";
		}
		
		return Util.f("""
					<script>
						const msg = '%s'.trim();
						
						if (msg.length > 0) {
							alert(msg);
						}
						
						history.back();
					</script>
				""", msg);
	}
	
	public static String jsReplace(String msg, String uri) {
		if (msg == null) {
			msg = "";
		}
		if (uri == null) {
			uri = "";
		}
		
		return Util.f("""
					<script>
						const msg = '%s'.trim();
						
						if (msg.length > 0) {
							alert(msg);
						}
						
						location.replace('%s');
						
					</script>
				""", msg, uri);
	}
	
}