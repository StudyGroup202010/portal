package com.portal.z.common.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.portal.z.common.domain.logic.UserRowCallbackHandler;

@Repository("UserDaoJdbcImpl")
public class UserDaoJdbcImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbc;

    //SQL取得結果をサーバーにCSVで保存する
    @Override
    public void userCsvOut() throws DataAccessException {

        // M_USERテーブルのデータを全件取得するSQL
        String sql  = " select"
                + "    user_id"
                + "   ,user_due_date"
                + "   ,pass_update"
                + "   ,login_miss_times"
                + "   ,lock_flg"
                + "   ,enabled_flg"
                + " from"
                + "   zm001_user"
                + " order by"
                + "   user_id"
                ;

        // ResultSetExtractorの生成
        //　時間がかかるような処理を実行して、処理が終わったら結果を受け取るという方式
        UserRowCallbackHandler handler = new UserRowCallbackHandler();

        //SQL実行＆CSV出力
        jdbc.query(sql, handler);
    }
}
