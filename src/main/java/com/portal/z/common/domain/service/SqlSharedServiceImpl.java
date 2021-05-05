package com.portal.z.common.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.z.common.domain.model.Columns;
import com.portal.z.common.domain.repository.ColumnsMapper;

/**
 * SQL用共通サービス
 *
 */
@Transactional
@Service
public class SqlSharedServiceImpl implements SqlSharedService {

    @Autowired
    private ColumnsMapper columnsMapper;

    public int getstrcolumnlength(String tablename, String columnname) {

        if (tablename == null || tablename.isEmpty() || columnname == null || columnname.isEmpty()) {
            return 0;
        }

        Columns columns = columnsMapper.selectOne(tablename, columnname);
        if (columns == null) {
            return 0;
        }

        return columns.getCharacter_maximum_length();
    }
}
