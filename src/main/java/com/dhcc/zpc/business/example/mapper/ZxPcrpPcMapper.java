package com.dhcc.zpc.business.example.mapper;

import com.dhcc.zpc.business.example.entity.ZxPcrpPc;
import java.util.List;

public interface ZxPcrpPcMapper {
    int insert(ZxPcrpPc record);

    List<ZxPcrpPc> selectAll();
}