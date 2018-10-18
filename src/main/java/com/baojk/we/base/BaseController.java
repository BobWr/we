package com.baojk.we.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author baojikui (bjklwr@outlook.com)
 * @date 2018/10/17
 */
@Configuration
@Slf4j
public class BaseController {

    protected HttpSession session;

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.session = request.getSession();
        this.request = request;
        this.response = response;
    }

    @ExceptionHandler({ ApiException.class })
    public ErrorResponse handlerException(ApiException api, HttpServletResponse response) {
        log.error(api.getMessage());
        response.setStatus(api.getHttpCode());
        ErrorResponse errorResponse = new ErrorResponse(api);
        return errorResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handlerException(MethodArgumentNotValidException e, HttpServletResponse response) {
        log.error(e.getMessage(), e.getStackTrace());
        response.setStatus(500);
        ErrorResponse errorResponse = new ErrorResponse(e.getBindingResult().getFieldError().getDefaultMessage(),
                                                        "PARAM_ERROR");
        return errorResponse;
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handlerException(Exception e, HttpServletResponse response) {
        log.error(e.getMessage(), e.getStackTrace());
        response.setStatus(500);
        ErrorResponse errorResponse = new ErrorResponse("10000500", "SYS_ERROR");
        return errorResponse;
    }
}
