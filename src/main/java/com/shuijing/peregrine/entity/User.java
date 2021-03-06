package com.shuijing.peregrine.entity;

import com.shuijing.peregrine.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @since 2020-05-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="User对象", description="用户信息")
public class User extends BaseEntity<User> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "{name.not.blank}")
    private String name;

    @ApiModelProperty(value = "年龄")
    @NotNull(message = "{age.not.null}")
    private Integer age;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
