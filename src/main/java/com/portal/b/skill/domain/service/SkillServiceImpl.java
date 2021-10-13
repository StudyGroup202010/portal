package com.portal.b.skill.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.b.common.domain.model.Career;
import com.portal.b.common.domain.model.Skill;
import com.portal.b.common.domain.repository.CareerMapper;
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
    CareerMapper careerMapper;

    public List<Skill> selectSkillMany() {
        return skillMapper.selectMany();
    }

    public Skill selectSkillOne(String employee_id) {
        return skillMapper.selectOne(employee_id);
    }

    public Career selectCareerOne(String employee_id, String certification_no) {
        return careerMapper.selectOne(employee_id, certification_no);
    }

    public boolean insertCareerOne(Career career) {
        return careerMapper.insertOne(career);
    }

    public boolean updateSkillOne(Skill skill) {

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

    public boolean updateCareerOne(Career career) {

        boolean result = false;

        // 業務経歴情報を更新する。
        result = careerMapper.updateOne(career);

        // 業務経歴更新結果の判定
        if (result != true) {
            // 更新できなかったときは追加する
            result = careerMapper.insertOne(career);
        }

        return result;
    }

    public boolean deleteCareerOne(String employee_id, String certification_no) {
        return careerMapper.deleteOne(employee_id, certification_no);
    }

    public List<Skill> selectSkillBy(String employee_cd, String employee_name1_last, String biko) {
        return skillMapper.selectBy(employee_cd, employee_name1_last, biko);
    }

    public List<Career> selectCareerBy1(String employee_id, String business_content, String biko) {
        return careerMapper.selectBy1(employee_id, business_content, biko);
    }

    public List<Career> selectCareerBy2(String employee_id) {
        return careerMapper.selectBy2(employee_id);
    }
}
