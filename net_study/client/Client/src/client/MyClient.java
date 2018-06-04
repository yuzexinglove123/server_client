package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class MyClient {

	public static void main(String[] args) {
		try {
			// 1.创建客户端socket，指定服务器地址和端口号，向服务器发送请求信息
			Socket socket = new Socket("localhost", 8888);
			// 2.获取输出字节流，向服务器发送消息
			OutputStream os = socket.getOutputStream();
			// 3.将字节输出流包装为字符打印流
			PrintWriter pw = new PrintWriter(os);
			// 像客户端发送请求信息
			StringBuffer bf = new StringBuffer();
			bf.append("用户名：").append("admin");
			bf.append("密码：").append("123");
			pw.write(bf.toString());

			// 刷新缓存
			pw.flush();
			// socket
			socket.shutdownOutput();
			// 3.获取输入字节流，读取服务器端的响应信息
			InputStream is = socket.getInputStream();
			//
			InputStreamReader isr = new InputStreamReader(is);

			BufferedReader br = new BufferedReader(isr);
			String data = null;
			while (null != (data = br.readLine())) {
				System.out.println(new Date());
				System.out.println("yuzexing，服务器端说：" + data);
			}
			//
			socket.shutdownInput();
			// 4.
			br.close();
			isr.close();
			is.close();
			pw.close();
			os.close();
			socket.close();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
