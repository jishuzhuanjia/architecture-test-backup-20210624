/**
 * 
 */
package com.zj.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;

/**
 * @author ZhouJian
 * 2018骞�3鏈�10鏃� 涓嬪崍7:02:09
 *
 */
public class FileUtils {
	
	/**
	 *Network transfer file
	 */
	public static void rcvFile(Socket socket,String path){
		File fSave = new File(path);
		if(fSave.exists()){
			System.out.println("淇濆瓨鐨勬枃浠跺悕宸插瓨鍦�");
			return;
		}
		/*try {
			fSave.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	    */
		try {
			RandomAccessFile raf = new RandomAccessFile(fSave,"rw");
			InputStream netInput = socket.getInputStream();
			InputStream docInput = new DataInputStream(new BufferedInputStream(netInput));
		    
			byte[] buf = new byte[1024];
			
			int num = docInput.read(buf);
			while(num != -1){
				raf.write(buf, 0, num);
				raf.skipBytes(num);
				num = docInput.read(buf);
			}
			docInput.close();
			raf.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void sendFile(Socket client,String path)
	{   
		File file = new File(path);
		//鏄洰褰曞垯鐩存帴杩斿洖
		if(file.exists()){
			if(file.isDirectory()){
				System.out.println("浼犺緭鏂囦欢澶辫触锛屽弬鏁颁负涓�涓枃浠跺す");
				return;
			}
			FileInputStream fis;
			OutputStream netOut;
			try {
				fis = new FileInputStream(file);
				
				netOut = client.getOutputStream();
				
				OutputStream docOut = new DataOutputStream(new BufferedOutputStream(netOut));
				byte[] buf = new byte[2048];
				int num = fis.read(buf);
				while(num != -1){
					docOut.write(buf, 0, num);
					docOut.flush();
					num = fis.read(buf);
				}
				fis.close();
				docOut.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		
		
	}
}
