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
public class SysPost implements Serializable {
    private static final long serialVersionUID = 165781678070227744L;

    /**
     * 岗位ID
     */
    @TableId(type = IdType.ID_WORKER)
     private Long postId;
    
    /**
     * 岗位编码
     */
     private String postCode;
    
    /**
     * 岗位名称
     */
     private String postName;
    
    /**
     * 显示顺序
     */
     private Integer postSort;
    
    /**
     * 状态（0正常 1停用）
     */
     private String status;
    
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
    
    /**
     * 备注
     */
     private String remark;

}