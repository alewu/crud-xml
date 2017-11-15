package com.koko.crud.bean.test;
import java.io.Serializable;

/**
 * 
 * @author: alewu
 * @date 2017年10月10日 18:54:46
 * @description FixedFee  数据库表对应的实体类
 **/
@SuppressWarnings("serial")
public class FixedFee implements Serializable {

	/**固定费用id**/
	private Long fixedFeeId;

	/**租客id**/
	private Long renterId;

	/**费用类型**/
	private String feeType;

	/**费用单价**/
	private java.math.BigDecimal feePrice;

	/**创建时间**/
	private java.util.Date createTime;

	/**修改时间**/
	private java.util.Date updateTime;



	/**
	 * @author: alewu
	 * @return: void fixedFeeId
	 * @Description: 设置固定费用id
	 * @date:2017年10月10日 18:54:46
	 **/
	public void setFixedFeeId(Long fixedFeeId){
		this.fixedFeeId = fixedFeeId;
	}

	/**
	 * @author: alewu
	 * @return: return Long
	 * @Description: 获取固定费用id
	 * @date:2017年10月10日 18:54:46
	 **/
	public Long getFixedFeeId(){
		return this.fixedFeeId;
	}

	/**
	 * @author: alewu
	 * @return: void renterId
	 * @Description: 设置租客id
	 * @date:2017年10月10日 18:54:46
	 **/
	public void setRenterId(Long renterId){
		this.renterId = renterId;
	}

	/**
	 * @author: alewu
	 * @return: return Long
	 * @Description: 获取租客id
	 * @date:2017年10月10日 18:54:46
	 **/
	public Long getRenterId(){
		return this.renterId;
	}

	/**
	 * @author: alewu
	 * @return: void feeType
	 * @Description: 设置费用类型
	 * @date:2017年10月10日 18:54:46
	 **/
	public void setFeeType(String feeType){
		this.feeType = feeType;
	}

	/**
	 * @author: alewu
	 * @return: return String
	 * @Description: 获取费用类型
	 * @date:2017年10月10日 18:54:46
	 **/
	public String getFeeType(){
		return this.feeType;
	}

	/**
	 * @author: alewu
	 * @return: void feePrice
	 * @Description: 设置费用单价
	 * @date:2017年10月10日 18:54:46
	 **/
	public void setFeePrice(java.math.BigDecimal feePrice){
		this.feePrice = feePrice;
	}

	/**
	 * @author: alewu
	 * @return: return java.math.BigDecimal
	 * @Description: 获取费用单价
	 * @date:2017年10月10日 18:54:46
	 **/
	public java.math.BigDecimal getFeePrice(){
		return this.feePrice;
	}

	/**
	 * @author: alewu
	 * @return: void createTime
	 * @Description: 设置创建时间
	 * @date:2017年10月10日 18:54:46
	 **/
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	/**
	 * @author: alewu
	 * @return: return java.util.Date
	 * @Description: 获取创建时间
	 * @date:2017年10月10日 18:54:46
	 **/
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 * @author: alewu
	 * @return: void updateTime
	 * @Description: 设置修改时间
	 * @date:2017年10月10日 18:54:46
	 **/
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	/**
	 * @author: alewu
	 * @return: return java.util.Date
	 * @Description: 获取修改时间
	 * @date:2017年10月10日 18:54:46
	 **/
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

}
