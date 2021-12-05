package com.portal.a.employee.domain.model;

import javax.validation.GroupSequence;

/**
 * 入力チェック
 *
 */
@GroupSequence({ ValidCreate1.class, ValidCreate2.class })
public interface CreateOrder {

}
