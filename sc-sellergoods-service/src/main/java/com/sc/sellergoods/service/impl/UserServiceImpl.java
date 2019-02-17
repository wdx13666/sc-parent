package com.sc.sellergoods.service.impl;

import com.sc.pojo.User;
import com.sc.sellergoods.service.UserService;
import com.sc.mapper.UserMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wdx
 * @since 2019-02-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
