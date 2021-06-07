package com.zj.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;

/**
 * ≤‚ ‘Mapper
 * 
 * */
public interface TestMapper {
	
	public ArrayList<Integer> dynamicDataSourceTest(@Param("idsList") ArrayList<Integer> ids);
	
	public int insertARow(int value);
}
