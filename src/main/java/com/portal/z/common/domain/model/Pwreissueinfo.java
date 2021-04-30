package com.portal.z.common.domain.model;

import java.sql.Timestamp;
import lombok.Data;

/**
 * パスワード再発行情報
 */
@Data
public class Pwreissueinfo {
    private String token; // トークン
    private String user_id; // ユーザーID
    private String secret; // 秘密情報
    private Timestamp expirydate; // 認証情報有効期限
}
