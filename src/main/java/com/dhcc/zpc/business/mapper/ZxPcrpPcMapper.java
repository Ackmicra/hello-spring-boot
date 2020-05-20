package com.dhcc.zpc.business.mapper;

import com.dhcc.zpc.business.entity.ZxPcrpPc;
import java.util.List;

public interface ZxPcrpPcMapper {
    int insert(ZxPcrpPc record);

    List<ZxPcrpPc> selectAll();
}