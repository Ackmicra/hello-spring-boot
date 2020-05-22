package com.dhcc.zpc.util.execption.handler;

import com.dhcc.zpc.util.execption.BusinessServiceException;
import org.springframework.stereotype.Service;

/**
 * @author Ackmicra
 */
@Service
public interface PlatformExceptionLogHandler {
    void handleExceptionLog(BusinessServiceException serviceException);
    void handleExceptionLog(Exception exception);
}
