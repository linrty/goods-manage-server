package com.linrty.goods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linrty.goods.bean.User;
import org.springframework.stereotype.Repository;

/**
 * @author Linrty
 * 关于User的Dao层，BaseMapper内封装了User基本的增删改查等功能
 */
@Repository
public interface UserDAO extends BaseMapper<User> {

}
