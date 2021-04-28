package xhm.struts.download;

import com.opensymphony.xwork2.ActionSupport;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/*�ļ�����*/

/*�����ļ����ã�
 * <action name="download" class="xhm.struts.download.DownloadAction">
    		<result type="stream">
    			<param name="contentType">application/octet-stream</param>
    			<param name="inputName">fileInputStream</param>
    			<param name="contentDisposition">attachment;filename="ideaIU-2019.2.1������.exe"</param>
    			<param name="bufferSize">1024</param>
    			<!-- �ļ���С ����λb:���㿪����Ĭ���¸ı������Ҳ��Ҫ���²�����Ŀ -->
    			<!-- <param name="contentLength">2048</param> -->
    			<param name="contentLength">${fileSize}</param>
    		</result>
    </action>
 * 
 * */
public class DownloadAction extends ActionSupport {
	
	//�ļ���С
	long fileSize;
	
	String fileName;
	
	public String getFileName() {
		return fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//ʵ��Java.io.InputStream����
	InputStream fileInputStream;

	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	
	@Override
	public String execute() throws Exception {
		/*
		 * �ļ�������Ҫת����ISO8859-1������ַ�����
		 * 
		 * */
		fileName = new String("ideaIU-2019.2.1������.exe".getBytes(), "ISO8859-1");
		
		File downloadFile = new File("E:/��װ��/ideaIU-2019.2.1.exe");
		fileSize = downloadFile.length();
		
		fileInputStream = new FileInputStream(downloadFile);
		
		return super.execute();
	}

}
