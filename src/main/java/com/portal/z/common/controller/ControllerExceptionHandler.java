package com.portal.z.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.portal.z.common.exception.ApplicationException;
import com.portal.z.common.exception.Errors;
import com.portal.z.common.exception.HttpErrors;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

/**
 * 例外共通ハンドラ。
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * AppExceptionのハンドリングを行ないます。
     * 
     * @param request リクエスト
     * @param ex      AppException
     * @return 例外レスポンス
     */
    @ExceptionHandler(ApplicationException.class)
    public String handleAppException(HttpServletRequest request, ApplicationException ex, Model model) {
        return handleError(request, ex.getError(), ex, model, ex.getArgs());
    }

    /**
     * RuntimeExceptionのハンドリングを行ないます。
     * 
     * @param request リクエスト
     * @param ex      RuntimeException
     * @return 例外レスポンス
     */
    @ExceptionHandler(RuntimeException.class)
    public String handleException(HttpServletRequest request, RuntimeException ex, Model model) {
        return handleError(request, Errors.UNEXPECTED, ex, model, ex.toString());
    }

    /**
     * 例外をハンドルして例外レスポンスを作成します。
     * 
     * @param request リクエスト
     * @param error   エラー種別
     * @param ex      例外
     * @param args    メッセージにバインドするパラメータ
     * @return 例外レスポンス
     */
    protected String handleError(HttpServletRequest request, HttpErrors error, Exception ex, Model model,
            Object... args) {

        String message = MessageFormat.format(error.getMessage(), args);

        if (error.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR) {
            log.error(message, ex);
        } else {
            log.debug(message, ex);
        }

        // HTTPの情報ををModelに登録
        model.addAttribute("path", request.getRequestURI());

        // エラー名をModelに登録
        model.addAttribute("error", error.name());

        // HTTPステータスをModelに登録
        model.addAttribute("status", error.getStatus().value());

        // メッセージをModelに登録
        model.addAttribute("message", message);

        // 例外をModelに登録
        model.addAttribute("exception", ex.getClass().getName());

        return "error";
    }

//    /**
//     * {@inheritDoc} <br>
//     * Spring MVCが返す例外をハンドルして例外レスポンスを作成します。
//     */
//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
//            HttpStatus status, WebRequest request) {
//
//        RestError restError = new RestError();
//        if (request instanceof ServletWebRequest) {
//            restError.path = ((ServletWebRequest) request).getRequest().getRequestURI();
//        } else {
//            restError.path = request.getContextPath();
//        }
//
//        restError.error = status.getReasonPhrase();
//        restError.status = status.value();
//        restError.message = ex.getMessage();
//        restError.exception = ex.getClass().getName();
//
//        return new ResponseEntity<>(restError, status);
//    }
}