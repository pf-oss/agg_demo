package com.security.auth.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @author: pf
 * @create: 2021/1/5 15:16
 */
@Data
public class SysDept implements Serializable {
    private static final long serialVersionUID = -50803863432697751L;

    /**
     * 部门id
     */
    @TableId(type = IdType.ID_WORKER)
     private Long deptId;
    
    /**
     * 父部门id
     */
     private Long parentId;
    
    /**
     * 祖级列表
     */
     private String ancestors;
    
    /**
     * 部门名称
     */
     private String deptName;
    
    /**
     * 显示顺序
     */
     private Integer orderNum;
    
    /**
     * 负责人
     */
     private String leader;
    
    /**
     * 联系电话
     */
     private String phone;
    
    /**
     * 邮箱
     */
     private String email;
    
    /**
     * 部门状态（0正常 1停用）
     */
     private String status;
    
    /**
     * 删除标志（0代表存在 2代表删除）
     */
     private String delFlag;
    
    /**
     * 创建者
     */
     private String createBy;
    
    /**
     * 创建时间
     */
     private Date createTime;
    
    /**
     * 更新者
     */
     private String updateBy;
    
    /**
     * 更新时间
     */
     private Date updateTime;

}