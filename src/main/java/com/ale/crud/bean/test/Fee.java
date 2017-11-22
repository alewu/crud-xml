package com.ale.crud.bean.test;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @Author: alewu
 * @date 2017年09月15日 19:59:49
 * @Description Fee  数据库表对应的实体类
 **/
@SuppressWarnings("serial")
@Data
public class Fee implements Serializable {

	/**费用id**/
	private Long feeId;

	/**租客id**/
	private Long renterId;

	/**费用类型**/
	private String feeType;

	/**费用单价**/
	private java.math.BigDecimal price;

	/**初始读数**/
	private java.math.BigDecimal initNum;

	/**每月保底读数**/
	private java.math.BigDecimal floorNum;

	/**创建时间**/
	private java.util.Date createTime;

	/**修改时间**/
	private java.util.Date updateTime;

}
