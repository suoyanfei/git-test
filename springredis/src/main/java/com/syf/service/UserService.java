package com.syf.service;

import com.syf.model.BsUser;

public interface UserService {

    public BsUser selectByPrimaryKey(String usrId);

    public void A() ;
    public void B() throws Exception;

    public BsUser selectLike( String name);
}
