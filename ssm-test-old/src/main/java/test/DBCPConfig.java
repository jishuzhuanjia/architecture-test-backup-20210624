package test;

/**
 * @time 2019��12��26�� ����10:44:31
 * @author Administrator
 * @corporation luku
 * @description:���ݿ�����url,���ӳ�����˵��
 */
public class DBCPConfig {
	
	/* --------------------------------------------------- url  ------------------------------------ */

	/*allowMultiQueries
	 * ��Ȼmybatis֧���������£��������Ƕ�֪��mybatis�����������Ƕ�mysql����ƴ�ӡ�
	 * ���ǣ�ƴ�Ӻ��mysql���ᱨ���﷨������ˣ�Ϊ��ʹ�����������ܹ�˳��ִ�У�
	 * ��Ҫ���Ӵ�����
	 * */
	
	/**
	 * useUnicode=true&characterEncoding=utf-8
	 * ���������Գɶ����ã�Ϊ���ݿ����������ṩ����ͷ��ص����ݵı������͡�
	 * */
	
	/*
	 * zeroDateTimeBehavior=convertToNull
	 * ������ֵΪ0ʱ���޷���ȷ����ʱ������͡�
	 * �෴��Ĭ������»��׳�һ���쳣��������������:java.sql��sqlexception:�޷�����00:00 -00- 00:00:00��ֵ�ӵ�7��ת��Ϊʱ�����
	 * JDBC�����ַ�������һ������:zeroDateTimeBehavior����������������������ʹ���������ô������ԣ�
	 * ����������������ֵ:
	 * 	exception:Ĭ��ֵ�׳�SQL״̬[S1009]������ת��ֵ�����쳣;
	 *  convertToNull:������ת��ΪNULL;
	 *  round:�滻Ϊ��������0001-01-01;
	 * ��ˣ����ڴ����쳣�����Կ����޸������ַ���������zeroDateTimeBehavior=convertToNull�������������ǡ�
	 * */
	
	/*autoReconnect=true
	 * ���ݿ������쳣�ж�ʱ�Ƿ��Զ���������?�����Խ��8Сʱ���������ݿ����ʱ���Զ��Ͽ�����
	 * */
	
	/* --------------------------------------------------- dbcp����  ------------------------------------ */
	/*dbcp.initialSize- 0
	 * ��ʼ������:�����ӳ�����ʱ�����ĳ�ʼ�����ӵ��������ڰ汾1.2֮��õ�֧��,
	 * */
	
	/*dbcp.maxActive- 8
	 * ���������:���ӳ�һ�οɷ���������������
	 * �����������Ϊһ���������������������Ƶ�
	 * */
	
	/*dbcp.maxIdle- 8
	 * */
	
	/*dbcp.minIdle- 0
	 * ��С��������:
	 * ���������ӳ��б��ֿ��е����ӵ���С�������ڴ�֮�½������µ����ӣ�
     * �������Ϊ0���򲻴�����
	 * */
	
	/*dbcp.maxWait- infinite(-1)
	 * ���ȴ�ʱ��:
	 * ���ӳ���û�п�������ʱ�ȴ��������ӵ��ʱ��(�Ժ���Ϊ��λ)��
	 * �����ʱ�����׳��쳣���������Ϊ-1����ȴ������޵�
	 * */
	
	/*dbcp.testOnBorrow- false
	 * ȷ�����ӿ���
	 * ָʾ�ڽ����Ӵӳ���ȡ��֮ǰ�Ƿ���м�飬������ʧ�ܣ�
	 * �ӳ���ɾ�����Ӳ�������һ�����ӡ�
	 * �������Ϊtrue������뽫validationQuery��������Ϊ�ǿ��ַ���������Ч
	 * */
	
	/*dbcp.testOnReturn- false
	 * ָʾ�Ƿ��ڷ��س�֮ǰ���м��
	 * ע��:�������Ϊtrue, validationQuery������������Ϊ�ǿ��ַ���������Ч
	 * */
	
	/*dbcp.validationQuery=select 1
	 * sql query
	 * */
	
	/*dbcp.testWhileIdle=true
	 * �Ƿ����������
	 * ָʾ�����Ƿ��ѱ����������ռ�����֤(�����)��������ʧ�ܣ�
	 * ���ӽ��ӳ����Ƴ���
	 * ע��:�������Ϊtrue, validationQuery������������Ϊ�ǿ��ַ���������Ч
	 * */
	
	/*dbcp.timeBetweenEvictionRunsMillis=60000,Ĭ��-1
	 * ���������ռ����߳�������ʱ���ߵ�ʱ��(�Ժ���Ϊ��λ)��ֵ���������Ϊ������
	 * �����п��������ռ����߳�
	 * */
	
	/*dbcp.numTestsPerEvictionRun- 3
	 * dbcp.numTestsPerEvictionRun=3
	 * ����ÿ��Ҫ����������
	 * ��ÿ�����������ռ����߳�(�����)����ʱ����������
	 * */
	
	/*dbcp.minEvictableIdleTimeMillis=30000 
	 * Ĭ��1000 * 60 * 30ms
	 * ������С����ʱ��
	 * �����ڳ��б��ֿ����Ҳ������������ռ����߳�(����еĻ�)���յ���Сʱ��(�Ժ���Ϊ��λ)
	 * �����ӿ��Ա��ֿ��е���Сʱ����ֻ�г������ֵ�ſ��ܱ�����
	 * */
	
	/*dbcp.removeAbandoned-false
	 * �����ݿ���Դ���������ݿ��������ϴ�ʱ���Ƿ��������й¶���
	 * dbcp.removeAbandoned=true
	 * ��getNumIdle() < 2 And (getNumActive() > getMaxActive() -3)�ᴥ������й¶���(���ӳ���һ��ʱ��û�б�ʹ��)��
	 * */
	
	/*dbcp.removeAbandonedTimeout-Ĭ��300s����λ�롣
	 * ������Ϊ��󱣳�����ʱ��(��û�в����������)��
	 * */
	
	/*dbcp.logAbandoned-false
	 * dbcp.logAbandoned=true
	 * һ�㿪��
	 * ��ǵ�һ���������ӱ��ƻ�ʱ���Ƿ��ӡ����Ķ�ջ������־��
	 * */
}


