package xhm.struts.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

/*文件下载*/

/*配置文件配置：
 * <action name="download" class="xhm.struts.download.DownloadAction">
    		<result type="stream">
    			<param name="contentType">application/octet-stream</param>
    			<param name="inputName">fileInputStream</param>
    			<param name="contentDisposition">attachment;filename="ideaIU-2019.2.1汉化版.exe"</param>
    			<param name="bufferSize">1024</param>
    			<!-- 文件大小 ：单位b:就算开发者默认下改变此属性也需要重新部署项目 -->
    			<!-- <param name="contentLength">2048</param> -->
    			<param name="contentLength">${fileSize}</param>
    		</result>
    </action>
 * 
 * */
public class DownloadAction extends ActionSupport {
	
	//文件大小
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
	
	//实现Java.io.InputStream的类
	InputStream fileInputStream;

	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	
	@Override
	public String execute() throws Exception {
		/*
		 * 文件名：需要转换成ISO8859-1编码的字符串。
		 * 
		 * */
		fileName = new String("ideaIU-2019.2.1汉化版.exe".getBytes(), "ISO8859-1");
		
		File downloadFile = new File("E:/安装包/ideaIU-2019.2.1.exe");
		fileSize = downloadFile.length();
		
		fileInputStream = new FileInputStream(downloadFile);
		
		return super.execute();
	}

}
