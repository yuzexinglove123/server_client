package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

	public static void main(String[] args) {
		try {
			// 1. 创建一个服务端的socket，即ServerSocket对象，指定绑定的端口，并且监听端口
			ServerSocket ss = new ServerSocket(8888);
			// 2.调用accept（）方法，開始监听客户端请求，创建socket，等待客户端的链接
			System.out.println("=================服务器即将启动，等待客户端的链接==================");
			Socket socket = ss.accept();
			// 3.获取输入字节流，读取客户端请求信息
			InputStream ins = socket.getInputStream();

			// 将字节流包装成子浮流
			InputStreamReader isr = new InputStreamReader(ins);
			// 为字符输入流添加缓冲
			BufferedReader br = new BufferedReader(isr);
			// 读取字符输入流中的数据信息
			String data = null;
			while (null != (data = br.readLine())) {
				System.out.println(new Date());
				System.out.println("我是服务端，客户端说：" + data);
			}
			// 调用shutdown方法关闭输入流
			socket.shutdownInput();
			// 4.获取输入字节流，响应客户端的信息
			OutputStream os = socket.getOutputStream();
			// 将字节流包装成字符打印流
			PrintWriter pw = new PrintWriter(os);
			// 向客户端回复消息
			pw.write("用户名和密码输入正确");
			// 刷新缓存
			pw.flush();
			//
			socket.shutdownOutput();
			// 关闭资源
			pw.close();
			os.close();
			br.close();
			isr.close();
			ins.close();
			socket.close();
			ss.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
