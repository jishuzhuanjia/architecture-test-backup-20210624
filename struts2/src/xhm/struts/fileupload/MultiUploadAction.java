package xhm.struts.fileupload;

import com.opensymphony.xwork2.ActionSupport;

import java.io.File;
import java.util.List;

/*���ļ��ϴ�
 * 
 * 
 * ֻ�е�ÿһ���ļ��������ϴ�Ҫ���ϴ����ܳɹ���
 * 
 * ��ʱAction��д����
 * ��ͬ����Ԫ��д����       ----------��ʽ1pojo
 * File uploadFile1;
 * String uploadFile1ContentType;
 * String uploadFile1FileName;
 * File uploadFile2;
 * String uploadFile2ContentType;
 * String uploadFile2FileName;
 * 
 * 
 * ͬ��д����
 * 1.�б����Բ���Ҫʵ����   -------------��ʽ2 
 * List<File> file
 * List<String> finleContentType
 * List<String> fileFilleName
 * ע��ǰ�˿����ж����ͬfile
 * 
 * 2.����:���Բ���Ҫʵ����   -------------��ʽ3
 * File[] uploadFile1;
 * String[] uploadFile1ContentType;
 * String[] uploadFile1FileName;
 * 
 * �����ԣ���ʽ2�ͷ�ʽ3�����ж�����壬ǰ�˿������ö��
 *       ��ʽ1��2��3���Ի��ʹ��
 * 
 *��ֻ�Ƕ���pojoʱ��ǰ���ж��input����ô����
 *��ʱ��ͬ���ļ�����','�����Ҹ�ֵ��fileFileName
 *���ж���ļ�ʱ�����ø�ֵ��File��pojo��ʽֻӦ���ϴ�һ��ͬ��input��
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
		 ������Ϊ���� ��OK
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
		
		 //������Ϊ�б� ��OK�����Կ��Բ����г�ʼ����Ҫ�ṩsetter
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
