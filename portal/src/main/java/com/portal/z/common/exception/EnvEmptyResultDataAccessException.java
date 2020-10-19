package com.portal.z.common.exception;

import org.springframework.dao.EmptyResultDataAccessException;

public class EnvEmptyResultDataAccessException extends EmptyResultDataAccessException {
    
    /**
     * 環境マスタ用の独自例外（Env_EmptyResultDataAccessException）
     * ＊環境マスタは業務的なマスタなので通常のEmptyResultDataAccessExceptionと分けておいた方が拾いやすいため。
     */
      private static final long serialVersionUID = 1L;

	  public EnvEmptyResultDataAccessException(String msg,int expectedSize) {
        super(msg,expectedSize);
      }
    }