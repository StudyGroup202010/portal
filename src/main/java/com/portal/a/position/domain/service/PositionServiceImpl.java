package com.portal.a.position.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.a.common.domain.model.Position;
import com.portal.a.common.domain.repository.PositionMapper;

/**
 * PositionServiceImpl
 *
 */
@Transactional
@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    PositionMapper positionMapper;

    public List<Position> selectMany() {
        return positionMapper.selectMany();
    }

    public Position selectOne(String position_cd) {
        return positionMapper.selectOne(position_cd);
    }

    public boolean insertOne(Position position) {
        return positionMapper.insertOne(position);
    }

    public boolean updateOne(Position position) {
        return positionMapper.updateOne(position);
    }

    public boolean deleteOne(String position_cd) {
        return positionMapper.deleteOne(position_cd);
    }

    public List<Position> selectBy(String position_name, String start_yearmonth, String end_yearmonth, String biko) {
        return positionMapper.selectBy(position_name, start_yearmonth, end_yearmonth, biko);
    }
}
