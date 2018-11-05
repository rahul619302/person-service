package com.cg.person.service.starter.dao;

import com.cg.person.service.starter.pojo.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPersonDao extends JpaRepository<Person, Integer> {

    @Query("select max(id) from Person")
    Integer getMaxId();
}
