package com.dhcc.zpc.util.execption.handler.impl;

import com.dhcc.zpc.util.execption.BusinessServiceException;
import com.dhcc.zpc.util.execption.handler.PlatformExceptionLogHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrintExceptionHandlerImpl implements PlatformExceptionLogHandler {
    @Override
    public void handleExceptionLog(BusinessServiceException serviceException) {
        log.error(serviceException.printLog(true));
    }

    @Override
    public void handleExceptionLog(Exception exception) {
        exception.printStackTrace();
    }
}
