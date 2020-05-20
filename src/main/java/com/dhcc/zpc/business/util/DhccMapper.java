package com.dhcc.zpc.business.util;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * Date: 2018/5/16
 * Time: 15:31
 * tk.mybatis.mapper.additional.insert.InsertListMapper接口是一个和数据库无关的方法，他不支持任何的主键策略。
 * tk.mybatis.mapper.additional.insert.InsertListMapper 使用于MySQL,H2等
 * com.dhcc.mapper.OracleInsertListMapper使用于oracle
 * 但是有了 genId 方式后，这个接口增加了对 @KeySql 注解 genId 方法的支持。
 * InsertListMapper支持批量插入，并且可以回写 ID
 * @author yuanshaobo
 */
public interface DhccMapper<T>
        extends
        Mapper<T> ,
//        OracleInsertListMapper<T>
        InsertListMapper<T>
{
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
