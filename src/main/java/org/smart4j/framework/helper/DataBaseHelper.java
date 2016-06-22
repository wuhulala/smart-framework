package org.smart4j.framework.helper;


import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.PropsUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by xueaohui on 2016/6/20.
 */
public final class DataBaseHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseHelper.class);

    private static final ThreadLocal<Connection> CONNECTION_HOLDER;
    private static final QueryRunner QUERY_RUNNER;


    private static final BasicDataSource DATA_SOURCE;

    static {

        CONNECTION_HOLDER = new ThreadLocal<Connection>();
        QUERY_RUNNER = new QueryRunner();
        DATA_SOURCE = new BasicDataSource();

        DATA_SOURCE.setDriverClassName(ConfigHelper.getJdbcDriver());
        DATA_SOURCE.setUrl(ConfigHelper.getJdbcUrl());
        DATA_SOURCE.setUsername(ConfigHelper.getJdbcUsername());
        DATA_SOURCE.setPassword(ConfigHelper.getJdbcPassword());

    }

    /**
     * 获取连接
     */
    public static Connection getConnection(){
        Connection connection = CONNECTION_HOLDER.get();
        if(connection == null){
            try {
                connection = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                LOGGER.error("get connection failure" , e);
                throw new RuntimeException(e);
            }finally {
                CONNECTION_HOLDER.set(connection);
            }
        }

        return connection;
    }

    /**
     * 关闭连接
     */
    public static void closeConnection(){
        Connection connection = CONNECTION_HOLDER.get();
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure" + e);
            }finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }

    /**
     * 查询实体列表
     */
    public static <T>List<T> queryEntityList(Class<T> entityClass,String sql,Object... params){
        List <T>entityList = null;

        try {
            Connection connection = getConnection();
            entityList = QUERY_RUNNER.query(connection,sql,new BeanListHandler<T>(entityClass),params);
        } catch (SQLException e) {
            LOGGER.error("query entity list failure",e);
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
        return entityList;
    }

    /**
     * 查询实体
     */
    public static <T>T queryEntity(Class<T> entityClass,String sql,Object... params){
        T entity = null;
        try {
            Connection connection = getConnection();
            entity = QUERY_RUNNER.query(connection,sql,new BeanHandler<T>(entityClass),params);
        } catch (SQLException e) {
            LOGGER.error("query entity list failure",e);
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
        return entity;
    }

    /**
     * 执行查询语句
     */
    public static List<Map<String,Object>> executeQuery(String sql,Object ... params){
        List<Map<String,Object>> result = null;

        try{
            Connection connection = getConnection();
            result  = QUERY_RUNNER.query(connection, sql, new MapListHandler(), params);
        }catch (Exception e){
            LOGGER.error("execute query failure " , e );
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }

        return result;
    }

    /**
     * 执行更新语句(包括update insert delete)
     */
    public static int executeUpdate(String sql , Object... params){
        int rows = 0 ;
        try {
            Connection connection = getConnection();
            rows = QUERY_RUNNER.update(connection, sql, params);
        }catch (SQLException e){
            LOGGER.error("execute update failure" , e );
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }

        return rows;
    }

    /**
     * 插入实体
     */
    public static <T>boolean insertEntity(Class<T> entityClass,Map<String , Object>fieldMap){
        if(CollectionUtil.isEmpty(fieldMap)){
            LOGGER.error("can not insert entity : fieldMap is empty");
            return false;
        }

        String sql = "INSERT INTO " +  getTableName(entityClass) ;
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");

        for (String fieldName : fieldMap.keySet()){
            columns.append(fieldName).append(",");
            values.append("?,");
        }

        columns.replace(columns.indexOf(","),columns.length(),")");
        values.replace(columns.indexOf(","),values.length(),")");

        sql += columns +" VALUES" +values;

        Object[] params = fieldMap.values().toArray();

        return executeUpdate(sql,params) == 1;
    }

    /**
     * 更新实体
     */
    public static <T> boolean updateEntity(Class<T> entityClass , long id , Map<String,Object> fieldMap){
        if(CollectionUtil.isEmpty(fieldMap)){
            LOGGER.error("can not update entity : fieldMap is empty");
            return false;
        }

        String sql = "UPDATE" +  getTableName(entityClass) + " SET ";
        StringBuilder columns = new StringBuilder("(");

        for (String fieldName : fieldMap.keySet()){
            columns.append(fieldName).append("=?, ");
        }

        columns.replace(columns.indexOf(","),columns.length(),")");

        sql += columns.substring(0,columns.lastIndexOf(", ")) + "WHERE  id = ? ";

        List<Object> paramList = new ArrayList<Object>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();

        return executeUpdate(sql, params) == 1;
    }


    /**
     * 删除实体
     */
    public static <T> boolean deleteEntity(Class<T> entityClass ,long id){
        String sql = "DELETE FROM " + getTableName(entityClass) + "WHERE id = ?";
        return executeUpdate(sql, id) == 1;
    }

    private static  String getTableName(Class<?> entityClass) {
        return entityClass.getSimpleName();
    }


}
