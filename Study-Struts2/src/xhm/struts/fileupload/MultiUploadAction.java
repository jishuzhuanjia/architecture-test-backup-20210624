package xhm.struts.fileupload;

import java.io.File;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

/*多文件上传
 * 
 * 
 * 只有当每一个文件都符合上传要求，上传才能成功。
 * 
 * 此时Action中写法：
 * 不同名表单元素写法：       ----------方式1pojo
 * File uploadFile1;
 * String uploadFile1ContentType;
 * String uploadFile1FileName;
 * File uploadFile2;
 * String uploadFile2ContentType;
 * String uploadFile2FileName;
 * 
 * 
 * 同名写法：
 * 1.列表：可以不需要实例化   -------------方式2 
 * List<File> file
 * List<String> finleContentType
 * List<String> fileFilleName
 * 注：前端可以有多个不同file
 * 
 * 2.数组:可以不需要实例化   -------------方式3
 * File[] uploadFile1;
 * String[] uploadFile1ContentType;
 * String[] uploadFile1FileName;
 * 
 * 经测试：方式2和方式3可以有多个定义，前端可以引用多个
 *       方式1和2，3可以混合使用
 * 
 *当只是定义pojo时，前端有多个input会怎么样？
 *此时不同的文件名被','隔开且赋值给fileFileName
 *当有多个文件时，不好赋值给File，pojo方式只应该上传一个同名input。
 */
public class MultiUploadAction extends  ActionSupport{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<File> file1s;
	List<String> file1sContentType;
	List<String> file1sFileName;
	
	File file3;
	String file3ContentType;
	String file3FileName;
	
	public void setFile3(File file3) {
		this.file3 = file3;
	}

	public void setFile3ContentType(String file3ContentType) {
		this.file3ContentType = file3ContentType;
	}

	public void setFile3FileName(String file3FileName) {
		this.file3FileName = file3FileName;
	}

	List<File> file2s;
	List<String> file21sContentType;
	List<String> file2sFileName;
	
	public void setFile1s(List<File> file1s) {
		this.file1s = file1s;
	}

	public void setFile1sContentType(List<String> file1sContentType) {
		this.file1sContentType = file1sContentType;
	}

	public void setFile1sFileName(List<String> file1sFileName) {
		this.file1sFileName = file1sFileName;
	}
	
	public void setFile2s(List<File> file2s) {
		this.file2s = file2s;
	}

	public void setFile21sContentType(List<String> file21sContentType) {
		this.file21sContentType = file21sContentType;
	}

	public void setFile2sFileName(List<String> file2sFileName) {
		this.file2sFileName = file2sFileName;
	}

	@Override
	public String execute() throws Exception {
		if(file1s != null)
		System.out.println(file1s.size());
		if(file2s != null)
		System.out.println(file2s.size());
		
		System.out.println(file3FileName);
		
		return super.execute();
	}
	
	/*File uploadFile1;

	public void setUploadFile1(File uploadFile1) {
		this.uploadFile1 = uploadFile1;
	}

	public void setUploadFile1ContentType(String uploadFile1ContentType) {
		this.uploadFile1ContentType = uploadFile1ContentType;
	}

	public void setUploadFile1FileName(String uploadFile1FileName) {
		this.uploadFile1FileName = uploadFile1FileName;
	}

	String uploadFile1ContentType;
	public void setUploadFile2(File uploadFile2) {
		this.uploadFile2 = uploadFile2;
	}

	public void setUploadFile2ContentType(String uploadFile2ContentType) {
		this.uploadFile2ContentType = uploadFile2ContentType;
	}

	public void setUploadFile2FileName(String uploadFile2FileName) {
		this.uploadFile2FileName = uploadFile2FileName;
	}

	String uploadFile1FileName;
	
	File uploadFile2;
	String uploadFile2ContentType;
	String uploadFile2FileName;
	
	 
	@Override
	public String execute() throws Exception {
		 当属性为数组 ：OK
		for (File file : uploadFile1) {
			System.out.println(file.getName());
		}
		
		for (String contentType : uploadFile1ContentType) {
			System.out.println(contentType);
		}
		
		for (String FileName : uploadFile1FileName) {
			System.out.println(FileName);
		}
		
		
		//System.out.println(uploadFile2.length);
		
		 //当属性为列表 ：OK，属性可以不进行初始化，要提供setter
		for (File file : uploadFile1) {
			System.out.println(file.getName());
		}
		
		for (String contentType : uploadFile1ContentType) {
			System.out.println(contentType);
		}
		
		for (String FileName : uploadFile1FileName) {
			System.out.println(FileName);
		}
		
		//System.out.println("other file:" +uploadFile2FileName);
		
		System.out.println(uploadFile1FileName);
		System.out.println(uploadFile2FileName);
		 
		return super.execute();
	}*/
	
	
	
}
