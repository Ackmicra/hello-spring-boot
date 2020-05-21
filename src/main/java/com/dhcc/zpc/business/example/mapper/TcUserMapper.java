package com.dhcc.zpc.business.example.mapper;

import com.dhcc.zpc.business.example.entity.TcUser;
import com.dhcc.zpc.util.tkmybatis.DhccMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface TcUserMapper extends DhccMapper<TcUser> {

    void deleteUser(String idNo);
}
