package com.portal.b.skill.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.a.common.domain.model.Employee;
import com.portal.a.common.domain.repository.EmployeeMapper;
import com.portal.b.common.domain.model.Career;
import com.portal.b.common.domain.model.Careerprocess;
import com.portal.b.common.domain.model.Careertechnology;
import com.portal.b.common.domain.model.Empcertification;
import com.portal.b.common.domain.model.Skill;
import com.portal.b.common.domain.model.Technology;
import com.portal.b.common.domain.repository.CareerMapper;
import com.portal.b.common.domain.repository.CareerprocessMapper;
import com.portal.b.common.domain.repository.CareertechnologyMapper;
import com.portal.b.common.domain.repository.EmpcertificationMapper;
import com.portal.b.common.domain.repository.ProcessMapper;
import com.portal.b.common.domain.repository.SkillMapper;
import com.portal.b.common.domain.repository.TechnologyMapper;

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

    @Autowired
    TechnologyMapper technologyMapper;

    @Autowired
    CareertechnologyMapper careertechnologyMapper;

    @Autowired
    ProcessMapper processMapper;

    @Autowired
    CareerprocessMapper careerprocessMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    EmpcertificationMapper empcertificationMapper;

    // スキル情報
    public List<Skill> selectSkillMany() {
        return skillMapper.selectMany();
    }

    public Skill selectSkillOne(String employee_id) {
        return skillMapper.selectOne(employee_id);
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

    public boolean deleteSkillOne(String employee_id) {

        // 社員資格を削除する。
        // 社員資格は削除結果不問のため戻り値を評価しない。
        empcertificationMapper.deleteOne(employee_id);

        // スキル情報を削除する。
        return skillMapper.deleteOne(employee_id);
    }

    public List<Skill> selectSkillBy(String employee_cd, String employee_name1_last, String organization_name,
            String biko) {
        return skillMapper.selectBy(employee_cd, employee_name1_last, organization_name, biko);
    }

    public List<Skill> selectSkillByExcel(String employee_cd, String employee_name1_last, String organization_name,
            String biko) {
        return skillMapper.selectByExcel(employee_cd, employee_name1_last, organization_name, biko);
    }

    // 業務経歴
    public Career selectCareerOne(String employee_id, String certification_no) {
        return careerMapper.selectOne(employee_id, certification_no);
    }

    public boolean insertCareerOne(Career career) {
        return careerMapper.insertOne(career);
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

    public List<Career> selectCareerBy1(String employee_id, String business_content, String biko) {
        return careerMapper.selectBy1(employee_id, business_content, biko);
    }

    public Career selectCareerBy2() {
        return careerMapper.selectBy2();
    }

    // 技術マスタ
    public List<Technology> selectTechnologyBy(String technology_kbn) {
        return technologyMapper.selectBy(technology_kbn);
    }

    // 業務経歴技術
    public List<Careertechnology> selectCareertechnologyBy(String employee_id, String certification_no,
            String technology_kbn) {
        return careertechnologyMapper.selectBy(employee_id, certification_no, technology_kbn);
    }

    public boolean insertCareertechnologyOne(Careertechnology careertechnology) {
        return careertechnologyMapper.insertOne(careertechnology);
    }

    public boolean deleteCareertechnologyOne(String employee_id, String certification_no) {
        return careertechnologyMapper.deleteOne(employee_id, certification_no);
    }

    // 工程マスタ
    public List<Process> selectProcessMany() {
        return processMapper.selectMany();
    }

    // 業務経歴工程
    public List<Careerprocess> selectCareerprocessBy(String employee_id, String certification_no) {
        return careerprocessMapper.selectBy(employee_id, certification_no);
    }

    public boolean insertCareerprocessOne(Careerprocess careerprocess) {
        return careerprocessMapper.insertOne(careerprocess);
    }

    public boolean deleteCareerprocessOne(String employee_id, String certification_no) {
        return careerprocessMapper.deleteOne(employee_id, certification_no);
    }

    // 社員情報
    public Employee selectEmployeeOne(String employee_id) {
        return employeeMapper.selectOne(employee_id, null, null);
    }

    // 社員資格
    public List<Empcertification> selectEmpcertificationBy(String employee_id) {
        return empcertificationMapper.selectBy(employee_id);
    }

    public boolean deleteEmpcertificationOne(String employee_id) {
        return empcertificationMapper.deleteOne(employee_id);
    }

    public boolean insertEmpcertificationOne(Empcertification empcertification) {
        return empcertificationMapper.insertOne(empcertification);
    }
}
