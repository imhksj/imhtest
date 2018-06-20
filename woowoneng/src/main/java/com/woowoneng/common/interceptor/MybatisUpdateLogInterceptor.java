package com.woowoneng.common.interceptor;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
/*
@Intercepts 
({ 
	@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
	@Signature(type = Executor.class, method = "query",  args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
	@Signature(type = Executor.class, method = "query",  args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
 */
@Intercepts({@Signature(type=StatementHandler.class, method="update", args={Statement.class})})
public class MybatisUpdateLogInterceptor implements Interceptor {

	private Log logger = LogFactory.getLog(this.getClass());

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler handler = (StatementHandler)invocation.getTarget();
		
		BoundSql boundSql = handler.getBoundSql();
		// 쿼리
		String sql = "    ";
		sql += boundSql.getSql();
		
//		System.out.println("sql > \n"+sql);
		// 쿼리실행시 맵핑되는 파라미터를 구한다
		Object param = handler.getParameterHandler().getParameterObject();
		
		if(param == null){				// 파라미터가 아무것도 없을 경우
			sql = sql.replaceFirst("\\?", "''");
		}
		// 해당 파라미터의 클래스가 Integer, Long, Float, Double 클래스일 경우
		else{
			if(param instanceof Integer || param instanceof Long || param instanceof Float || param instanceof Double){
				sql = sql.replaceFirst("\\?", param.toString());
			}
			// 해당 파라미터의 클래스가 String 일 경우(이 경우는 앞뒤에 '(홑따옴표)를 붙여야해서 별도 처리
			else if(param instanceof String){
				sql = sql.replaceFirst("\\?", "'" + param + "'");
			}
			// 해당 파라미터가 Map 일 경우
			else if(param instanceof Map){

				/*
				 * 쿼리의 ?와 매핑되는 실제 값들의 정보가 들어있는 ParameterMapping 객체가 들어간 List 객체로 return이 된다.
				 * 이때 List 객체의 0번째 순서에 있는 ParameterMapping 객체가 쿼리의 첫번째 ?와 매핑이 된다
				 * 이런 식으로 쿼리의 ?과 ParameterMapping 객체들을 Mapping 한다
				 */
				List<ParameterMapping> paramMapping = boundSql.getParameterMappings();	

				for(ParameterMapping mapping : paramMapping){
					// 파라미터로 넘긴 Map의 key 값이 들어오게 된다
					String propValue = mapping.getProperty();
					if( propValue.indexOf("_frch") > -1){
						
					}
					else{
						// 넘겨받은 key 값을 이용해 실제 값을 꺼낸다
						Object value = ((Map) param).get(propValue);
						// SQL의 ? 대신에 실제 값을 넣는다. 이때 String 일 경우는 '를 붙여야 하기땜에 별도 처리
						if(value instanceof String){
							sql = sql.replaceFirst("\\?", "'" + value + "'");
						}else{
							sql = sql.replaceFirst("\\?", value.toString());
						}
					}
				}
			}
			// 해당 파라미터가 사용자 정의 클래스일 경우
			else{
				/*
				 * 쿼리의 ?와 매핑되는 실제 값들이 List 객체로 return이 된다.
				 * 이때 List 객체의 0번째 순서에 있는 ParameterMapping 객체가 쿼리의 첫번째 ?와 매핑이 된다
				 * 이런 식으로 쿼리의 ?과 ParameterMapping 객체들을 Mapping 한다
				 */
				List<ParameterMapping> paramMapping = boundSql.getParameterMappings();

				Class<? extends Object> paramClass = param.getClass();
				// logger.debug("paramClass.getName() : {}", paramClass.getName());
				for(ParameterMapping mapping : paramMapping){
					// 해당 파라미터로 넘겨받은 사용자 정의 클래스 객체의 멤버변수명
					String propValue = mapping.getProperty();
					// 관련 멤버변수 Field 객체 얻어옴
					Field field = paramClass.getDeclaredField(propValue);
					// 멤버변수의 접근자가 private일 경우 reflection을 이용하여 값을 해당 멤버변수의 값을 가져오기 위해 별도로 셋팅
					field.setAccessible(true);
					// 해당 파라미터로 넘겨받은 사용자 정의 클래스 객체의 멤버변수의 타입
					Class<?> javaType = mapping.getJavaType();
					// SQL의 ? 대신에 실제 값을 넣는다. 이때 String 일 경우는 '를 붙여야 하기땜에 별도 처리
					if(String.class == javaType){
						sql = sql.replaceFirst("\\?", "'" + field.get(param) + "'");
					}
					else if(Date.class == javaType){
						Date date = (Date) field.get(param);
					}
					else{
						sql = sql.replaceFirst("\\?", (field.get(param) == null) ? null : field.get(param).toString());
					}
				}
			}
		}
		
		logger.debug("=====================================================================");
        logger.debug("sql : \r\n"+ sql);
        logger.debug("parameter : \r\n"+ param);
        logger.debug("=====================================================================");
		
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub

	}

}
