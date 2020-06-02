package com.syf.mapper;

import com.syf.model.BsUser;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {


    BsUser selectByPrimaryKey(String id);


    public void insert(BsUser user);

    public BsUser selectLike(@Param("name") String name);


}