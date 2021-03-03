package xhm.struts.fileupload;

import java.io.File;

import com.opensymphony.xwork2.ActionSupport;
/*
 * 文件上传：经过默认过滤器栈defaultStack的org.apache.struts2.interceptor.FileUploadInterceptor
 * 会传递三个文件相关参数，他们的命名格式如下：
 * [your file name parameter] - 这是实际的文件的上载。在这个例子中是 "myFile" - 这是临时文件，名字随机生成的。如upload_b0f38a09_ae49_44ad_92a2_718f17bf3a2e_00000001.tmp
 * [your file name parameter]ContentType - 这是被上传的文件，该文件的内容类型。在这个例子中是 "myFileContentType"
 * [your file name parameter]FileName - 这是被上传的文件的名称。在这个例子中是 "myFileFileName"
 * 
 * 与文件上传相关的struts常量:
 * struts.multipart.maxSize  -  上传文件的最大大小，默认250M
 * struts.multipart.parser  -  上传多部表单所使用的库，默认jakarta
 * struts.multipart.saveDir  -  存放临时文件的名录，默认是javax.servlet.context.tempdir.
 * */

/*文件上传必须继承ActionSupport类
 * 否则上传不进入Action直接返回success视图
 * 
 * 继承自ActionSupport后：不管是文件超过大小尺寸还是类型限制，都会返回input视图 - 不会进入Action方法
 * 只有当文件符合上传限制了才会进入到Action方法中
 * 
 * struts.multipart.maxSize  和 	<param name="maximumSize">1000000</param
*  前者默认2M，struts2默认使用common-fileupload实现文件的上传，实现起来非常方便，但是这里面也有一个问题，
*  就是common-fileupload组件默认最大支持上传文件的大小为2M
*  
*  作用范围：前者指的是客户端请求发送的数据大小：包括多个文件的总大小。后者指的是满足前者后，单个文件的大小
*  如何判定上传失败？
*  前者和后者(哪怕就一个文件不满足)有一个不满足就失败。
 * 
 * 这里还要解决中文文件名上传失败
 * 经测试框架会正确返回中文文件名，起初上传失败的原因是中文文件太大导致超过文件上传限制才上传失败的。
 * 
 * 注意：如果不上传文件，直接提交则也会进入到Action中，要对List或文件进行判断，返回正确的视图。
 * 
 * struts2文件上传HTTP Status 412 – Precondition Failed
 * 当重新提交表单的时候会返回此错误
 * 解决：暂无。
 * 
 * */
public class FileUploadAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	//临时文件
	File uploadFile;
	
	String uploadFileContentType;
	
	//文件真正的名字
	String uploadFileFileName ;
	
	//保存位置
	String desDir = "c:/users/Administrator/Desktop/";
	
	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}
	
	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}
	
	public String execute() throws Exception {
		if(uploadFile != null)
			/* 上传临时文件-文件名不等于客户端文件名，随机生成的文件名，如jpg图片：upload_b0f38a09_ae49_44ad_92a2_718f17bf3a2e_00000001.tmp
			 * 可以看到临时文件以tmp结尾的
			 *    
			 * E:\workspace\java\xhm-web-basic\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\Study-Struts2\\(转义)upload_b0f38a09_ae49_44ad_92a2_718f17bf3a2e_00000001.tmp
			 */
		System.out.println("uploadFile" + uploadFile.getAbsolutePath());
		
		//MIME类型，当上传文件为jpg时：image/jpeg
		//System.out.println("contentType" + uploadFileContentType);
		
		/*文件名：QQ图片20190224152335.jpg
		 * 等于客户端选择文件的名字
		 * */   
		//System.out.println("FileName:" + uploadFileFileName);
		
		//保存文件
		//File desFile = new File(desDir, uploadFileFileName);
		//FileUtils.copyFile(uploadFile, desFile);
		
		return "success"; 
	}
}
