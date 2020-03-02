package moe.cnkirito.security.oauth2.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import moe.cnkirito.security.oauth2.code.config.MvcConfig;
import moe.cnkirito.security.oauth2.code.endpoint.QQAccount;

public class TestServlet {

	public static void main(String[] args) throws IOException {

		Test();
		/*URL url = new URL("http://127.0.0.1:8090/web/top/login/ValidateAtmServlet.ac");
		// 设定连接的相关参数
		HttpURLConnection connection= (HttpURLConnection) url.openConnection();
		
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
		// 向服务端发送key = value对
		out.write("ComtopSessionSID=<SNAID>BA0305D66D1C9717BBBD909111E49897</SNAID>");
		out.flush();
		out.close();
		// 获取服务端的反馈
		String strLine="";
		String strResponse ="";
		InputStream in =connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		while((strLine =reader.readLine()) != null){
			strResponse +=strLine +"\n";
		}
		
		System.out.print(strResponse);*/
	}
	
	private static void Test(){
		Class mvcConfig = MvcConfig.class;
		String name = mvcConfig.getName();
		String simpleName = mvcConfig.getSimpleName();
		int modifier = mvcConfig.getModifiers();
		Class superClass = mvcConfig.getSuperclass();
		String superName = superClass.getName();
		System.out.println("name="+name+"---simpleName="+simpleName+"----modifier="+modifier+"------superName="+superName);
	}
}
