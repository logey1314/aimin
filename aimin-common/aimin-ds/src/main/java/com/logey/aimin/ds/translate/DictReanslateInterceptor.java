package com.logey.aimin.ds.translate;

import com.logey.aimin.ds.annotation.DictTranslate;
import com.logey.aimin.ds.entity.Dict;
import com.logey.aimin.ds.mapper.DictMapper;
import com.logey.aimin.ds.service.DictService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
@RequiredArgsConstructor
public class DictReanslateInterceptor implements Interceptor {

    private final  DictService dictService;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //查询数据

        //遍历字段 是否有注解
        //如果有 获取注解的key
        //dict表查出值
        //查出数据
        Object result = invocation.proceed();
        if (result instanceof List<?>) {
            for (Object object : (List<?>) result) {
                Class<?> clazz = object.getClass();
                Field[] declaredFields = clazz.getDeclaredFields();
                for (Field field : declaredFields) {
                    if (field.isAnnotationPresent(DictTranslate.class)) {
                        DictTranslate annotation = field.getAnnotation(DictTranslate.class);
                        String targetField = annotation.valueTo();
                        field.setAccessible(true);
                        Object value = field.get(object);
                        Field declaredField = clazz.getDeclaredField(targetField);
                        String dictName = dictService.getById((int) value).getDictName();
                        declaredField.setAccessible(true);
                        declaredField.set(object,dictName);
                    }

                }
            }
        }

        return  result;
    }
}
