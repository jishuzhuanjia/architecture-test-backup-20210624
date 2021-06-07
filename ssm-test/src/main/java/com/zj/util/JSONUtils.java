package com.zj.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletResponse;
import org.apache.commons.lang.StringUtils;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @time 2019骞�12鏈�20鏃� 15:50:03
 * @author zhoujian
 * @corporation luku
 * @description JSON宸ュ叿绫�
 * @finished true
 * @finishTime 2019骞�12鏈�27鏃� 14:50:25
 * @version 1.0
 */
public class JSONUtils {

	/**
	 * 浣跨敤jackson灏嗗璞¤浆鎹㈡垚JSON骞跺啓鍏esponse娴�
	 * 
	 * 
	 * @param response
	 * @param object
	 *            濡傛灉object涓簄ull锛岃繑鍥瀓son "null"
	 * 
	 * @param include
	 *            灞炴�ц繃婊よ鍒欙紝鍙彇濡備笅鍊硷細
	 * 
	 *            <pre>
	 *  娉ㄦ剰锛氭璁剧疆瀵逛簬Map鍜孡ist鏃犳晥銆�
	 *  Include.ALWAYS - 榛樿 
	 *  Include.NON_EMPTY - ""鍜宯ull閮戒笉浼氳搴忓垪鍖�
	 *  Include.NON_NULL - ""浼氳搴忓垪鍖栵紝 
	 *  Include.NON_DEFAULT - 榛樿鍊间笉浼氳搴忓垪鍖栵紝鍗冲垵濮嬪寲鐨勫�间笉浼氳鍒濆鍖栥�俰nt涓�0 寮曠敤绫诲瀷涓簄ull銆�
	 *            </pre>
	 */
	public static void printJSON(ServletResponse response, Object object, Include include) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");

		ObjectMapper objectMapper = new ObjectMapper();
		if (include != null)
			objectMapper.setSerializationInclusion(include);

		String jsonStr = null;
		PrintWriter writer = null;
		try {
			jsonStr = objectMapper.writeValueAsString(object);
			writer = response.getWriter();
			writer.write(jsonStr);
			writer.flush();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * 灏唈son搴忓垪鍖栦负Map/List
	 * @return 濡傛灉jsonStr==null 鎴杍sonStr.equals("")==true,鍒欒繑鍥瀗ull
	 * <pre>
	 * NOTE锛氬鏋滀负瀵硅薄锛岃繑鍥濵ap锛屽鏋滀负鏁扮粍锛岃繑鍥濴ist
	 * </pre>
	 */
	public static Object JSON2MapOrList(String jsonStr) {
		// true: null || ""
		if (StringUtils.isEmpty(jsonStr))
			return null;

		ObjectMapper objectMapper = new ObjectMapper();
		Object result = null;
		// normalization
		jsonStr = jsonStr.trim();

		try {
			// 鎰忓懗鐫�鏄暟缁勶紝杞崲鎴怢ist
			if (jsonStr.startsWith("[")) {
				result = objectMapper.readValue(jsonStr, List.class);
			} else {
				// 鏄璞★紝灏辫浆鎹㈡垚Map
				result = objectMapper.readValue(jsonStr, Map.class);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static void main(String[] args) {
		// 娴嬭瘯 JSON2Map
		/*
		 * String jsonStr = "[1,2,3]"; 
		 * Map<String, Object> json2Map = JSON2Map(jsonStr);
		 * System.out.println(json2Map.get("username"));
		 */

		// 娴嬭瘯鍙嶅簭鍒楀寲鏁扮粍
		/*
		 * String jsonStr = "[1,2,3]"; 
		 * ObjectMapper mapper = new ObjectMapper(); try {
		 * List list = mapper.readValue(jsonStr, List.class); 
		 * //[1, 2, 3]
		 * System.out.println(list); } catch (IOException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */

		// JSON2MapOrList娴嬭瘯
		/*String jsonMap = "{\"username:\":null,\"password\":\"123\"}";
		System.out.println(((Map<String, Object>) JSON2MapOrList(jsonMap)).get("username"));
		System.out.println(((Map<String, Object>) JSON2MapOrList(jsonMap)).get("password"));*/
		//String jsoList = "[1,\"zhou\",true]";
		// [1, zhou, true]
		//System.out.println(JSON2MapOrList(jsoList));
	}
}
