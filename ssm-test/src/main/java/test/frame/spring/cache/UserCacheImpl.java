package test.frame.spring.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import ssm.test.controller.User;

/**
 * @CreateTime：2020年2月12日 下午12:08:36
 * @Author：zhoujian
 * @QQ：2025513
 * @FileDescription：
 * @IsFinished：false
 */
@Service
public class UserCacheImpl {

	/**
	 * <pre>
	 * 1.@Cacheable注解的使用
	 * 可以标记在一个方法上，也可以标记在一个类上。当标记在一个方法上时表示该方法是支持缓存的，当标记在一个类上时则表示该类所有的方法都是支持缓存的。
	 * 
	 * 用在方法就会成为可缓存的方法,当该方法首次调用时,会将返回的结果存入到缓存中,第二次调用此方法时,只要
	 * 传递的参数值与之前调用时一致,就会直接从缓存中取出结果并返回,不会进入到方法。

	 * 1.1.注: value指定cache name,必须在配置文件中存在,否则该方法不是缓存方法。
	 * 
	 * 1.2.key:
	 * 1.2.1.默认策略,如果提供key,ehcache会使用默认key策略。
	 * 1.2.2.自定义策略
	 * 是指我们可以通过Spring的EL表达式来指定我们的key。这里的EL表达式可以使用方法参数及它们对应的属性。
	 * 使用方法参数时我们可以直接使用“#参数名”或者“#p参数index”。
	 * 
	 * 除了上述使用方法参数作为key之外，Spring还为我们提供了一个root对象可以用来生成key。通过该root对象我们可以获取到以下信息。
	 * #root.methodName  当前方法名
	 * #root.method.name 当前方法
	 * #root.target  当前被调用的对象
	 * #root.targetClass  当前被调用的对象的class
	 * #root.args[0]  当前方法参数组成的数组
	 * #root.caches[0].name 当前被调用的方法使用的Cache
	 * 注:#root可省略,因为默认是使用#root
	 * 如：
	 *@Cacheable(value={"users", "xxx"}, key="caches[1].name")
	 *public User find(User user) {
	 *   returnnull;
	 *}
	 *
	 *1.3.condition属性
	 * 该属性指定缓存的条件,默认情况为"",会缓存所有的结果,见下面方法testCondition例
	 * * */
	/*@Cacheable(value="hibernateUtilCache",key="#userId")
	public String getCacahedUserName(int userId) {
		System.out.println("-----getCacahedUserName----");
		return "username" + userId;
	}*/
	
	// 使用token参数来自定义key
	// 拼接字符串不能:  @Cacheable(value="hibernateUtilCache",key="#p1+"ddd"")  // 编译错误
	
	// 以下展示多个参数拼接
	// @Cacheable(value="hibernateUtilCache",key="#p1+'UserCacheImpl.getCacahedUserName' + #userId")
	
	// 甚至在拼接的时候,也可以省略#root来使用root属性: 
	@Cacheable(value="hibernateUtilCache",key="#p1+'UserCacheImpl.getCacahedUserName' + args[0]")
	public String getCacahedUserName(int userId,String token) {
		System.out.println("-----getCacahedUserName----");
		return "username" + userId;
	}
	
	// #user.age
	// %
	// 下面的例子缓存大于18岁的用户
	//@Cacheable(value="hibernateUtilCache",condition="#age>=18")
	
	//下面演示等于的书写
	// ==而不能是=,否则抛出: org.springframework.expression.spel.SpelEvaluationException: 
	// EL1001E:(pos 0): Type conversion problem, cannot convert from java.lang.Integer to boolean
	
	//@Cacheable(value="hibernateUtilCache",condition="#age==18")
	
	@CachePut(value="hibernateUtilCache")
	public String testCondition(int age) {
		System.out.println("-----testCondition-----");
		return "user"+age;
	}
	
	/**
	 * 2.@CachePut("cacheName") - 该注解至少一共cache name 在支持Spring
	 * Cache的环境下，对于使用@Cacheable标注的方法，Spring在每次执行前都会检查Cache中是否存在相同key的缓存元素，如果存在就不再执行该方法，而是直接从缓存中获取结果进行返回，否则才会执行并将返回结果存入指定的缓存中。@CachePut也可以声明一个方法支持缓存功能。与@Cacheable不同的是使用@CachePut标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中。
	 * 
	 * @CachePut也可以标注在类上和方法上。使用@CachePut时我们可以指定的属性跟@Cacheable是一样的。
	 * 
	 * @CachePut("users")//每次都会执行方法，并将结果存入指定的缓存中
	 * public User find(Integer id) {
	 *
	 *     retur nnull;
	 * 
	 * }
	 */
	
	/**
	3.@CacheEvict
    @CacheEvict是用来标注在需要清除缓存元素的方法或类上的。当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作。
    @CacheEvict可以指定的属性有value、key、condition、allEntries和beforeInvocation。
            其中value、key和condition的语义与@Cacheable对应的属性类似。即value表示清除操作是发生在哪些Cache上的（对应Cache的名称）；
    key表示需要清除的是哪个key，如未指定则会使用默认策略生成的key；condition表示清除操作发生的条件。
           下面我们来介绍一下新出现的两个属性allEntries和beforeInvocation。

    3.1.allEntries属性
    allEntries是boolean类型，表示是否需要清除缓存中的所有元素。默认为false，表示不需要。当指定了allEntries为true时，
    Spring Cache将忽略指定的key。有的时候我们需要Cache一下清除所有的元素，这比一个一个清除元素更有效率。
    @CacheEvict(value="users", allEntries=true)
	public void delete(Integer id) {
	
	   System.out.println("delete user by id: " + id);
	
	}

	1.3.2  beforeInvocation属性
	清除操作默认是在对应方法成功执行之后触发的，即方法如果因为抛出异常而未能成功返回时也不会触发清除操作。使用beforeInvocation可以改变触发清除操作的时间，
	当我们指定该属性值为true时，Spring会在调用该方法之前清除缓存中的指定元素。

	@CacheEvict(value="users", beforeInvocation=true)
	
	public void delete(Integer id) {
	
	   System.out.println("delete user by id: " + id);
	
	}
            其实除了使用@CacheEvict清除缓存元素外，当我们使用Ehcache作为实现时，我们也可以配置Ehcache自身的驱除策略，
            其是通过Ehcache的配置文件来指定的。由于Ehcache不是本文描述的重点，这里就不多赘述了。*/
	
	/**4     @Caching
    @Caching注解可以让我们在一个方法或者类上同时指定多个Spring Cache相关的注解。其拥有三个属性：cacheable、put和evict，
           分别用于指定@Cacheable、@CachePut和@CacheEvict。

	@Caching(cacheable = @Cacheable("users"), evict = { @CacheEvict("cache2"),

    @CacheEvict(value = "cache3", allEntries = true) })*/
	
	@Caching(cacheable = @Cacheable("users"), evict = { @CacheEvict("cache2"),
		    @CacheEvict(value = "cache3", allEntries = true) })
	public User find(Integer id) {
		return null;
	}
}
