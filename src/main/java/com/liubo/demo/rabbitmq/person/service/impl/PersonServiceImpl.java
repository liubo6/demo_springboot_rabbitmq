package com.liubo.demo.rabbitmq.person.service.impl;

import com.liubo.demo.rabbitmq.person.dao.PersonDao;
import com.liubo.demo.rabbitmq.person.model.PersonDO;
import com.liubo.demo.rabbitmq.person.service.PersonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hzlbo on 2016/7/5.
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Resource
    private PersonDao personDao;

    /**
     * 添加
     *
     * @param personDO
     * @return
     * @throws Exception
     */
    @Override
    public boolean addPerson(PersonDO personDO) throws Exception {
        boolean result = personDao.addPerson(personDO) > 0;
        return result;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean removePerson(String id) throws Exception {
        return personDao.removePerson(id) > 0;
    }

    /**
     * 修改
     *
     * @param person
     * @return
     * @throws Exception
     */
    @Override
    public boolean modifyPerson(PersonDO person) throws Exception {
        return personDao.modifyPerson(person) > 0;
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public PersonDO getPerson(String id) throws Exception {
        return personDao.getPerson(id);
    }

    /**
     * 查询列表
     *
     * @param person
     * @return
     * @throws Exception
     */
    @Override
    public List<PersonDO> queryPersonList(PersonDO person) throws Exception {
        return personDao.queryPersonList(person);
    }


}
