package test.shiro.realm;

import java.util.ArrayList;
import java.util.List;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.zj.util.NullByteArrayException;
import com.zj.util.NullStringException;
import com.zj.util.security.md.MD5Utils;


/*
 * 
 * Shiro三大组件：
 * 1.Subject,实质上就是服务器Session,根据sid(名称可配置)获取。
 * 2.SecurityManager
 * 3.Realm(认证/权限授权)
 * 
 * Shiro Realm何时调用?
 * 1.doGetAuthenticationInfo() - 认证方法
 * 这个方法是在用户登录的时候调用的也就是执行SecurityUtils.getSubject().login（）的时候调用，Shiro也可配置表单认证拦截器进行登录认证。
 *     
 * 2.doGetAuthorizationInfo() - 权限授权方法
 * 方法是在我们调用SecurityUtils.getSubject().isPermitted()这个方法时会调用doGetAuthorizationInfo()
 * 我们在某个方法上加上@RequiresPermissions这个，那么我们访问这个方法的时候，就会自动调用SecurityUtils.getSubject().isPermitted（），从而调用doGetAuthorizationInfo
 * */

/**
 * 用户登录Realm
 * */
public class UserRealm extends AuthorizingRealm {
	
	/** 返回此Realm名 */
	public String getName() {
		return "UserRealm";
	}
	
	/**
	 * 登录验证
	 * */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("shiro认证...");
		
		// 1.获取用户名称
		@SuppressWarnings("unused")
		String username = (String) token.getPrincipal();
		
		// 2.根据用户名username使用Dao层查询，返回实体类如User
		// 如果查询结果为空，则返回异常：
		/*
		 * if(user==null){
		 * 	   //表明当前用户不存在
		 *     throw new UnknownAccountException();
		 * }
		 * 
		 * */
		
		// 3.获取密码
		// 假设pwd是查询结果返回
		String pwd ="fc1709d0a95a6be30bc5926fdb7f22f4";  
		// 4.返回账户，密码以及realmName信息
		// principal应该为查询返回映射实体类，不能为null 
		// 账户用来进行序列化，需要实现序列化接口
		//SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(new User(),pwd,getName());
		return new SimpleAuthenticationInfo("zhoujian",pwd,getName());
	}

	/** 权限授权 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		// 1.从principals获取用户实体
		/* @SuppressWarnings("unused")
		User user = (User) principals.getPrimaryPrincipal();*/
		
		// 2.根据用户实体获取权限信息
		// UserRole userRole = roleServiceImpl.getUserRoles(user);
		
		// 3.定义一个集合对象，存储权限
		List<String> permissions = new ArrayList<String>();
		//  如果userRole != null;
		//  permissions.add(userRole.getRoleKey())
		
		//  假设以下List权限从数据库差得
		List<String> permissionsFromDao = new ArrayList<String>();
		permissionsFromDao.add("delete");
		permissionsFromDao.add("remove");
		permissionsFromDao.add("modify");
		permissions.addAll(permissionsFromDao);
		
		// 4.认证信息
		SimpleAuthorizationInfo simpleAuthorizationInfo =new SimpleAuthorizationInfo();
		// 添加权限
		simpleAuthorizationInfo.addStringPermissions(permissions);
		
		// 5.如果需要可以添加角色权限管理，可联合Dao层查询
		simpleAuthorizationInfo.addRole("admin");
		
		return simpleAuthorizationInfo;
	}
	
	public static void main(String[] args) {
		
		// 1.shiro sha1加密
		Object s = new SimpleHash("SHA1", "123456", null, 1024);
		// fc1709d0a95a6be30bc5926fdb7f22f4
		System.out.println(s);
		// SHA1-长度40
		System.out.println(s.toString().length());
		
		// 2.shiro md5加密
		// MD5/md5都可以
		Object s1 = new SimpleHash("md5", "123456", null, 1024);
		// fc1709d0a95a6be30bc5926fdb7f22f4
		System.out.println(s1);
		
		// 3.验证MD5Utils加密算法正确性
		// e10adc3949ba59abbe56e057f20f883e
		//byte[] bs = null;
		//String string = null;
		
		try {
			System.out.println(MD5Utils.encode("123456", false));
		} catch (NullByteArrayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullStringException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// e10adc3949ba59abbe56e057f20f883e
		System.out.println(new SimpleHash("md5", "123456", null, 1));
		// 32位
		System.out.println(new SimpleHash("md5", "123456", "213213", 1));
		
	}
}
