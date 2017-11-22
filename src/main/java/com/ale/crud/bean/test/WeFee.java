package com.ale.crud.bean.test;
import java.io.Serializable;

/**
 * 
 * @author: alewu
 * @date 2017年10月10日 18:54:46
 * @description WeFee  数据库表对应的实体类
 **/
@SuppressWarnings("serial")
public class WeFee implements Serializable {

	/**水电费用id**/
	private Long weFeeId;

	/**租客id**/
	private Long renterId;

	/**水费单价**/
	private java.math.BigDecimal waterPrice;

	/**初始用水读数**/
	private java.math.BigDecimal initWaterReading;

	/**每月用水保底吨数**/
	private java.math.BigDecimal waterTonnage;

	/**电费单价**/
	private java.math.BigDecimal electricPrice;

	/**初始用电读数**/
	private java.math.BigDecimal initElectricReading;

	/**每月用电保底度数**/
	private java.math.BigDecimal electricDegree;

	/**创建时间**/
	private java.util.Date createTime;

	/**更新时间**/
	private java.util.Date updateTime;



	/**
	 * @author: alewu
	 * @return: void weFeeId
	 * @Description: 设置水电费用id
	 * @date:2017年10月10日 18:54:46
	 **/
	public void setWeFeeId(Long weFeeId){
		this.weFeeId = weFeeId;
	}

	/**
	 * @author: alewu
	 * @return: return Long
	 * @Description: 获取水电费用id
	 * @date:2017年10月10日 18:54:46
	 **/
	public Long getWeFeeId(){
		return this.weFeeId;
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
	 * @return: void waterPrice
	 * @Description: 设置水费单价
	 * @date:2017年10月10日 18:54:46
	 **/
	public void setWaterPrice(java.math.BigDecimal waterPrice){
		this.waterPrice = waterPrice;
	}

	/**
	 * @author: alewu
	 * @return: return java.math.BigDecimal
	 * @Description: 获取水费单价
	 * @date:2017年10月10日 18:54:46
	 **/
	public java.math.BigDecimal getWaterPrice(){
		return this.waterPrice;
	}

	/**
	 * @author: alewu
	 * @return: void initWaterReading
	 * @Description: 设置初始用水读数
	 * @date:2017年10月10日 18:54:46
	 **/
	public void setInitWaterReading(java.math.BigDecimal initWaterReading){
		this.initWaterReading = initWaterReading;
	}

	/**
	 * @author: alewu
	 * @return: return java.math.BigDecimal
	 * @Description: 获取初始用水读数
	 * @date:2017年10月10日 18:54:46
	 **/
	public java.math.BigDecimal getInitWaterReading(){
		return this.initWaterReading;
	}

	/**
	 * @author: alewu
	 * @return: void waterTonnage
	 * @Description: 设置每月用水保底吨数
	 * @date:2017年10月10日 18:54:46
	 **/
	public void setWaterTonnage(java.math.BigDecimal waterTonnage){
		this.waterTonnage = waterTonnage;
	}

	/**
	 * @author: alewu
	 * @return: return java.math.BigDecimal
	 * @Description: 获取每月用水保底吨数
	 * @date:2017年10月10日 18:54:46
	 **/
	public java.math.BigDecimal getWaterTonnage(){
		return this.waterTonnage;
	}

	/**
	 * @author: alewu
	 * @return: void electricPrice
	 * @Description: 设置电费单价
	 * @date:2017年10月10日 18:54:46
	 **/
	public void setElectricPrice(java.math.BigDecimal electricPrice){
		this.electricPrice = electricPrice;
	}

	/**
	 * @author: alewu
	 * @return: return java.math.BigDecimal
	 * @Description: 获取电费单价
	 * @date:2017年10月10日 18:54:46
	 **/
	public java.math.BigDecimal getElectricPrice(){
		return this.electricPrice;
	}

	/**
	 * @author: alewu
	 * @return: void initElectricReading
	 * @Description: 设置初始用电读数
	 * @date:2017年10月10日 18:54:46
	 **/
	public void setInitElectricReading(java.math.BigDecimal initElectricReading){
		this.initElectricReading = initElectricReading;
	}

	/**
	 * @author: alewu
	 * @return: return java.math.BigDecimal
	 * @Description: 获取初始用电读数
	 * @date:2017年10月10日 18:54:46
	 **/
	public java.math.BigDecimal getInitElectricReading(){
		return this.initElectricReading;
	}

	/**
	 * @author: alewu
	 * @return: void electricDegree
	 * @Description: 设置每月用电保底度数
	 * @date:2017年10月10日 18:54:46
	 **/
	public void setElectricDegree(java.math.BigDecimal electricDegree){
		this.electricDegree = electricDegree;
	}

	/**
	 * @author: alewu
	 * @return: return java.math.BigDecimal
	 * @Description: 获取每月用电保底度数
	 * @date:2017年10月10日 18:54:46
	 **/
	public java.math.BigDecimal getElectricDegree(){
		return this.electricDegree;
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
	 * @Description: 设置更新时间
	 * @date:2017年10月10日 18:54:46
	 **/
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	/**
	 * @author: alewu
	 * @return: return java.util.Date
	 * @Description: 获取更新时间
	 * @date:2017年10月10日 18:54:46
	 **/
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

}
