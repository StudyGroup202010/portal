package com.portal.b.skill.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.a.common.domain.repository.EmployeeMapper;
import com.portal.b.common.domain.model.Skill;
import com.portal.b.common.domain.repository.SkillMapper;

/**
 * SkillServiceImpl
 *
 */
@Transactional
@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    SkillMapper skillMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    public List<Skill> selectMany() {
        return skillMapper.selectMany();
    }

    public Skill selectOne(String employee_id) {
        return skillMapper.selectOne(employee_id);
    }

    public boolean updateOne(Skill skill) {

        boolean result = false;

        // スキル情報を更新する。
        result = skillMapper.updateOne(skill);

        // スキル情報更新結果の判定
        if (result != true) {
            // 更新できなかったときは追加する
            result = skillMapper.insertOne(skill);
        }

        return result;
    }

    public List<Skill> selectBy(String employee_cd, String employee_name1_last, String biko) {
        return skillMapper.selectBy(employee_cd, employee_name1_last, biko);
    }
}
