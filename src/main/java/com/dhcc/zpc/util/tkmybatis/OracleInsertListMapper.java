package com.dhcc.zpc.util.tkmybatis;

import org.apache.ibatis.annotations.InsertProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

/**
 * Date: 2018/10/25
 * Time: 17:27
 *
 * @author yuanshaobo
 */
@RegisterMapper
public interface OracleInsertListMapper<T> {

    /**
     * oracle 批量插入
     * @param recordList
     * @return
     */
    @InsertProvider(type = OracleInsertListProvider.class, method = "dynamicSQL")
    int insertList(List<T> recordList);
}
