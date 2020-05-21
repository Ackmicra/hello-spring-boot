package com.dhcc.zpc.util.snowflake;

import tk.mybatis.mapper.genid.GenId;

import java.util.Random;

/**
 * 在实体类上增加
 *      @Id
 *     @KeySql(genId = SnowflakeIdGenId.class)
 *     这两个注解，即可生成主键
 * @author yuanshaobo
 * @date: 2019/7/16 11:53
 * @since v1.1
 */
public class SnowflakeIdGenId implements GenId<String> {

    private long workerId = new Random().nextInt(32);
    private long datacenterId = new Random().nextInt(32);

    private SnowflakeIdWorker idWorker = new SnowflakeIdWorker(workerId,datacenterId);;

    @Override
    public String genId(String table, String column) {
        return Long.valueOf(idWorker.nextId()).toString();
    }
}
