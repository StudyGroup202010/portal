package com.portal.z.user.domain.model;

import javax.validation.GroupSequence;

/**
 * 入力チェック
 *
 */
@GroupSequence({ ValidCreate1.class, ValidCreate2.class })
public interface CreateOrder {

}
