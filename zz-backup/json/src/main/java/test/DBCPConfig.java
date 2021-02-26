package test;

/**
 * @time 2019年12月26日 上午10:44:31
 * @author Administrator
 * @corporation luku
 * @description:数据库连接url,连接池配置说明
 */
public class DBCPConfig {
	
	/* --------------------------------------------------- url  ------------------------------------ */

	/*allowMultiQueries
	 * 虽然mybatis支持批量更新，但是我们都知道mybatis的批量更新是对mysql语句的拼接。
	 * 但是，拼接后的mysql语句会报告语法错误。因此，为了使批处理操作能够顺利执行，
	 * 需要添加此属性
	 * */
	
	/**
	 * useUnicode=true&characterEncoding=utf-8
	 * 这两个属性成对设置，为数据库驱动程序提供传输和返回的数据的编码类型。
	 * */
	
	/*
	 * zeroDateTimeBehavior=convertToNull
	 * 当操作值为0时，无法正确处理时间戳类型。
	 * 相反，默认情况下会抛出一个异常，即您所看到的:java.sql。sqlexception:无法将“00:00 -00- 00:00:00”值从第7列转换为时间戳。
	 * JDBC连接字符串中有一个属性:zeroDateTimeBehavior，如果发生这种情况，可以使用它来配置处理策略，
	 * 它有以下三个属性值:
	 * 	exception:默认值抛出SQL状态[S1009]。不能转换值……异常;
	 *  convertToNull:将日期转换为NULL;
	 *  round:替换为最新日期0001-01-01;
	 * 因此，对于此类异常，可以考虑修改连接字符串并附加zeroDateTimeBehavior=convertToNull属性来避免它们。
	 * */
	
	/*autoReconnect=true
	 * 数据库连接异常中断时是否自动重新连接?它可以解决8小时不进行数据库操作时的自动断开问题
	 * */
	
	/* --------------------------------------------------- dbcp配置  ------------------------------------ */
	/*dbcp.initialSize- 0
	 * 初始化连接:在连接池启动时创建的初始化连接的数量，在版本1.2之后得到支持,
	 * */
	
	/*dbcp.maxActive- 8
	 * 最大活动连接数:连接池一次可分配的最大活动连接数，
	 * 如果它被设置为一个非正数，它就是无限制的
	 * */
	
	/*dbcp.maxIdle- 8
	 * */
	
	/*dbcp.minIdle- 0
	 * 最小空闲连接:
	 * 允许在连接池中保持空闲的连接的最小数量，在此之下将创建新的连接，
     * 如果设置为0，则不创建它
	 * */
	
	/*dbcp.maxWait- infinite(-1)
	 * 最大等待时间:
	 * 连接池在没有可用连接时等待返回连接的最长时间(以毫秒为单位)，
	 * 如果超时，则抛出异常，如果设置为-1，则等待是无限的
	 * */
	
	/*dbcp.testOnBorrow- false
	 * 确保链接可用
	 * 指示在将连接从池中取出之前是否进行检查，如果检查失败，
	 * 从池中删除连接并尝试另一个连接。
	 * 如果设置为true，则必须将validationQuery参数设置为非空字符串才能生效
	 * */
	
	/*dbcp.testOnReturn- false
	 * 指示是否在返回池之前进行检查
	 * 注意:如果设置为true, validationQuery参数必须设置为非空字符串才能生效
	 * */
	
	/*dbcp.validationQuery=select 1
	 * sql query
	 * */
	
	/*dbcp.testWhileIdle=true
	 * 是否检测空闲连接
	 * 指示连接是否已被空闲连接收集器验证(如果有)。如果检测失败，
	 * 连接将从池中移除。
	 * 注意:如果设置为true, validationQuery参数必须设置为非空字符串才能生效
	 * */
	
	/*dbcp.timeBetweenEvictionRunsMillis=60000,默认-1
	 * 空闲连接收集器线程在运行时休眠的时间(以毫秒为单位)的值。如果设置为非正，
	 * 则不运行空闲连接收集器线程
	 * */
	
	/*dbcp.numTestsPerEvictionRun- 3
	 * dbcp.numTestsPerEvictionRun=3
	 * 设置每次要检查的连接数
	 * 在每个空闲连接收集器线程(如果有)运行时检查的连接数
	 * */
	
	/*dbcp.minEvictableIdleTimeMillis=30000 
	 * 默认1000 * 60 * 30ms
	 * 设置最小空闲时间
	 * 连接在池中保持空闲且不被空闲连接收集器线程(如果有的话)回收的最小时间(以毫秒为单位)
	 * 即连接可以保持空闲的最小时长，只有超过这个值才可能被回收
	 * */
	
	/*dbcp.removeAbandoned-false
	 * dbcp.removeAbandoned=true
	 * 当getNumIdle() < 2 And (getNumActive() > getMaxActive() -3)会触发连接泄露检测(连接超过一定时长没有被使用)。
	 * */
	
	/*dbcp.removeAbandonedTimeout-300
	 * 可理解为最大保持连接时长(在没有操作的情况下)。
	 * */
	
	/*dbcp.logAbandoned-false
	 * dbcp.logAbandoned=true
	 * 一般开启
	 * 标记当一条语句或连接被破坏时，是否打印程序的堆栈跟踪日志。
	 * */
}