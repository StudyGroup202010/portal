package com.portal.z.common.domain.logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;

public class UserRowCallbackHandler implements RowCallbackHandler {

    @Override
    public void processRow(ResultSet rs) throws SQLException {

        try {

            //ファイル書き込みの準備
            File file = new File("userlist.csv");
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            //取得件数分loop
            do {

                //ResultSetから値を取得してStringにセット
                String str = rs.getString("user_id")       + ","
                        + rs.getDate("user_due_date")   + ","
                        + rs.getDate("pass_update")     + ","
                        + rs.getInt("login_miss_times") + ","
                        + rs.getBoolean("lock_flg")     + ","
                        + rs.getBoolean("enabled_flg");

                //ファイルに書き込み＆改行
                bw.write(str);
                bw.newLine();

            } while(rs.next());

            //強制的に書き込み＆ファイルクローズ
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }
}
