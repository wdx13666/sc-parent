package com.sc.sellergoods.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sc.mapper.AddressMapper;
import com.sc.pojo.Address;
import com.sc.sellergoods.service.AddressService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

}
