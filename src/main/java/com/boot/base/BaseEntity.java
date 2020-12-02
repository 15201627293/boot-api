package com.boot.base;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname BaseEntity
 * @Description TODO
 * @Date 2020/6/18 4:18 下午
 * @Created by hly
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 506921518718002644L;

    @TableId
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
