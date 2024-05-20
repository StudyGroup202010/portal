package com.portal.a.common.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.portal.a.common.domain.model.Sequence;
import com.portal.a.common.domain.repository.SequenceMapper;

/**
 * シーケンス用共通Service
 *
 */
@Transactional
@Service
public class SequenceSharedServiceImpl implements SequenceSharedService {

    @Autowired
    SequenceMapper sequenceMapper;

    public Sequence selectOne() {
    	
    	Sequence sequence = new Sequence();

        // id取得処理
        try {
            // idを追加する。
            boolean result =  sequenceMapper.insertSequenceOne();

            // id登録結果の判定
            if (result == true) {
                //最新のidを取得
            	sequence = sequenceMapper.selectSequenceOne();
            	//最新以外のidを削除
            	sequenceMapper.deleteSequenceOne(sequence.getId());
            }
        } catch (Exception e) {
            // エラーが発生したとき。
            return null;
        }
        return sequence;
    }
}
