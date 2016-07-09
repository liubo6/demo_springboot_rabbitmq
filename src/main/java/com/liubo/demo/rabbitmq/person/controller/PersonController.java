package com.liubo.demo.rabbitmq.person.controller;

import com.liubo.demo.rabbitmq.person.model.PersonDO;
import com.liubo.demo.rabbitmq.person.service.PersonService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by hzlbo on 2016/7/5.
 */
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Resource
    private PersonService personService;

    @RequestMapping(method = RequestMethod.POST)
    public PersonDO addPerson(PersonDO personDO) throws Exception {
        Assert.notNull(personDO, "对象不能为空");
        boolean result = personService.addPerson(personDO);
        if (result){
            return personService.getPerson(personDO.getId());
        }
        return new PersonDO();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PersonDO getPerson(@PathVariable String id) throws Exception {

        Assert.hasText(id, "ID不能为空");
        return personService.getPerson(id);
    }

}
