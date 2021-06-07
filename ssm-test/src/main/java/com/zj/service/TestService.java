package com.zj.service;

import resource.util.datasource.DataSource;

/**
 * @time 2019年12月25日 下午5:18:46
 * @author Administrator
 * @corporation luku
 * @description: 测试动态数据源、慢查询(slow sql)
 */


public interface TestService {
	
	/**
	 * 
	 * <pre>
	 * Note:
	 * @DataSource注解使用要求：
	 * 1.当没有此注解时，使用defaultTargetDataSource
	 * 2.当有此注解时，如果标注的数据源存在targetDataSources中，则使用此数据源，如果不存在，则使用defaultTargetDataSource。
	 * </pre>
	 * 
	 * */
	@DataSource("master")
	public void test1();
	
	@DataSource("master")
	public int insertARow(int value);
}



 