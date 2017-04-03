package org.template.com.provider;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;
import org.template.com.model.Permission;

import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;

public class RoleProvider extends MapperTemplate {

	
	public RoleProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
	}

	
	public String findByUserLoginName(MappedStatement ms) {
		
		//修改返回值类型为实体类型
        setResultType(ms, Permission.class);
		String sql = "select r.id, r.role_name, r.role_code, r.remarker, r.disabled, r.create_time, "
				+ " r.update_time FROM t_role r inner join t_user_role ru on r.id=ru.role_id  "
				+ "inner join t_user u on ru.user_id = u.id where u.login_name=#{userName} and r.disabled=0";
		return sql;
	}
	
	  /* public String batchInsert(Map<String,List<BoAgentDevice>> batchData){
	        List<BoAgentDevice> list = batchData.get("list");
	        StringBuffer sql = new StringBuffer("insert into tbl_bo_agent_device(agent_id,device_no,create_time) values ");
	        MessageFormat messageFormat = new MessageFormat("(#'{'list[{0}].agentId},#'{'list[{0}].deviceNo},#'{'list[{0}].createTime})");
	        for (int i = 0; i < list.size(); i++) {
	            sql.append(messageFormat.format(new Integer[]{i}));
	            sql.append(",");
	        }
	        sql.setLength(sql.length() - 1);
	        return sql.toString();
	    }*/
}
