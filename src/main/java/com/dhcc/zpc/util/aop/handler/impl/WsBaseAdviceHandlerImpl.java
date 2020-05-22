package com.dhcc.zpc.util.aop.handler.impl;

import com.dhcc.zpc.util.aop.handler.WsBaseAdviceHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WsBaseAdviceHandlerImpl implements WsBaseAdviceHandler {

    @Override
    public void insertWsBase() {

        log.info("后置最终通知执行了!!!!");
    }
}
