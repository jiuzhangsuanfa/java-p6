package com.jzsf.tuitor.service.impl;

import com.jzsf.tuitor.dao.AddressDao;
import com.jzsf.tuitor.pojo.Address;
import com.jzsf.tuitor.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
@Service
public class AddressServiceImpl
        extends BaseServiceImpl<Address, String>
        implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    protected JpaRepository getRepository() {
        return addressDao;
    }


}
