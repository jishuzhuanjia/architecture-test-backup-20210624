package com.zj.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zj.dao.TestMapper;
import com.zj.service.TestService;

/**
 * @time 2019年12月25日 下午5:19:42
 * @author Administrator
 * @corporation luku
 * @description:
 */
@Service("testServiceImpl")
public class TestServiceImpl implements TestService {
	
	@Autowired private TestMapper testMapper;

	public void test1() {
		System.out.println("TestService.test1()");
		ArrayList<Integer> idList = new ArrayList<Integer>(10000);
		System.out.println(idList.size());
		for(int i=0;i<10000;i++) { 
			idList.add(i);
		}
		System.out.println("sql result:" + testMapper.dynamicDataSourceTest(idList));
	}

	public int insertARow(int value) {
		// TODO Auto-generated method stub
		//int i = 1/0;
		
		/**
		 * 注意：不管是运行时异常还是checked异常，都是可以捕获的，捕获以后，事务就不会回滚。
		 * 因此不管是checked/unchecked的事务，都需要显示抛出，有以下解决方案：
		 * 1.try{}catch(){throw new RuntimeException()}
		 * 
		 * 2.catch中添加
		 * TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		 * 语句，手动回滚，这样上层就无需去处理异常（现在项目的做法）
		 * */
		
		try {
			//运行时异常
			@SuppressWarnings("unused")
			int i = 1/0;
		} catch (Exception e) {
			/*实际没有提交
			Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@15f5b5a5]
			Transaction synchronization deregistering SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@15f5b5a5]
			Transaction synchronization closing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@15f5b5a5]*/
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return testMapper.insertARow(value);
	}
	
	

}



