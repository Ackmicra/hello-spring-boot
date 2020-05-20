package com.dhcc.zpc.business.mapper;

import com.dhcc.zpc.business.entity.TcUser;
import com.dhcc.zpc.business.util.DhccMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface TcUserMapper extends DhccMapper<TcUser> {

    void deleteUser(String idNo);
}
