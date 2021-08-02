package com.portal.a.employee.domain.model;

import javax.validation.GroupSequence;

/**
 * 入力チェック
 *
 */
@GroupSequence({ ValidUpdate1.class, ValidUpdate2.class })
public interface UpdateOrder {

}