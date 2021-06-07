package resource.util.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @time 2019年12月25日 下午3:36:08
 * @author Administrator
 * @corporation luku
 * @description: 动态数据源
 */
public class DynamicDataSouce extends AbstractRoutingDataSource{
	/**
	 * 此方法如果返回一个不存在targetDataSources中的dataSource名或null，则使用默认dataSource
	 * */
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceResolver.getDataSource();    
	}

}



