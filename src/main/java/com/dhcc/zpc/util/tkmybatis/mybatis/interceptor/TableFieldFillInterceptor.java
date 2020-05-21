package com.dhcc.zpc.util.tkmybatis.mybatis.interceptor;

import com.dhcc.zpc.util.tkmybatis.mybatis.annotation.CreateTime;
import com.dhcc.zpc.util.tkmybatis.mybatis.annotation.UpdateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author yuanshaobo
 * @date: 2020/1/6 15:45
 * @since v1.1
 */
@Slf4j
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class TableFieldFillInterceptor implements Interceptor {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //sql类型
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        // 获取 SQL 命令
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        // 获取参数
        Object parameter = invocation.getArgs()[1];
        if(parameter instanceof Map
                && ((Map) parameter).containsKey("list")
                && ((Map) parameter).get("list") instanceof List){
            // 批量插入更新操作
            List<?> parameterList = (List<?>) ((Map) parameter).get("list");
            Field[] declaredFields = parameterList.get(0).getClass().getDeclaredFields();
            for (Object o : parameterList) {
                fieldFill(sqlCommandType, o, declaredFields);
            }
        }else if(parameter != null){
            // 单条记录插入更新操作
            // 获取私有成员变量
            Field[] declaredFields = parameter.getClass().getDeclaredFields();
            fieldFill(sqlCommandType, parameter, declaredFields);
        }

        return invocation.proceed();
    }

    private void fieldFill(SqlCommandType sqlCommandType, Object object, Field[] declaredFields) throws IllegalAccessException {
        for (Field field : declaredFields) {
            if (field.getAnnotation(CreateTime.class) != null) {
                // insert 语句插入 createTime
                if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                    field.setAccessible(true);
                    field.set(object, getTimeValue());
                }
            }
            // insert 或 update 语句插入 updateTime
            if (field.getAnnotation(UpdateTime.class) != null) {
                if (SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)) {
                    field.setAccessible(true);
                    field.set(object, getTimeValue());
                }
            }
        }
    }

    private String getTimeValue(){
        LocalDateTime localDateTime = LocalDateTime.now();
        StringBuilder builder = new StringBuilder();
        formatter.formatTo(localDateTime, builder);
        String value = builder.toString();
        return value;
    }
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
