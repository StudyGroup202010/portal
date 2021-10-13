package com.portal.a.position.domain.model;

import javax.validation.GroupSequence;

/**
 * 入力チェック
 *
 */
@GroupSequence({ ValidUpdate1.class, ValidUpdate2.class })
public interface UpdateOrder {

}