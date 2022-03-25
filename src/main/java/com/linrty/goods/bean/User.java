package com.linrty.goods.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PackageName: com.linrty.goods.bean
 * @ClassName: User
 * @Description: TODO
 * @author: Linrty
 * @date: 2022/3/24 17:22
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * 用户主键，用户ID
     */
    @JSONField(name = "user_id",defaultValue = "")
    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * 用户手机号码，唯一
     */
    @JSONField(name = "phone",defaultValue = "")
    private String phone;

    /**
     * 用户名
     */
    @JSONField(name = "user_name",defaultValue = "")
    private String userName;

    /**
     * 用户登录密码
     */
    @JSONField(name = "password",defaultValue = "")
    private String password;

}
