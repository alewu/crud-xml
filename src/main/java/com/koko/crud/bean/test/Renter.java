package com.koko.crud.bean.test;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @Author: alewu
 * @date 2017年09月15日 19:59:49
 * @Description Renter  数据库表对应的实体类
 **/
@SuppressWarnings("serial")
public class Renter implements Serializable {

	/**租客id**/
	private Long renterId;

	/**房间id**/
	private Long roomId;

	/**手机号码**/
	private String renterMobile;

	/**租客姓名**/
	private String renterName;

	/**微信号**/
	private String wechatId;

	/**微信普通用户的标识**/
	private String openId;

	/**身份证**/
	private String idCard;

	/**租客身份证图片**/
	private String renterImage;

	/**临时租客身份证图片**/
	private String tempImage;

	/**起租日**/
	private Date startTime;

	/**到租日**/
	private Date endTime;

	/**交租日 1-28号**/
	private Integer payTime;

	/**交租周期：1:1月，2:2月，...,12：12月**/
	private Integer payPeriod;

	/**押金**/
	private java.math.BigDecimal deposit;

	/**月租金**/
	private java.math.BigDecimal rentMonthly;

	/**入住人数**/
	private Integer renterNum;

	/**提醒天数**/
	private Integer remindDay;

	/**提醒时间**/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date remindTime;

	/**提醒备注**/
	private String remindRemark;

	/**紧急联系人**/
	private String emergencyContact;

	/**紧急联系人号码**/
	private String emergencyMobile;

	/**合同图片**/
	private String contractPicture;
    @JsonIgnoreProperties(ignoreUnknown = true)
	/**状态：1：未绑定，2：已审核，3：未审核，4：已退租**/
	private Integer renterStatus;
    @JsonIgnoreProperties(ignoreUnknown = true)
	/**创建时间**/
	private Date createTime;
    @JsonIgnoreProperties(ignoreUnknown = true)
	/**更新时间**/
	private Date updateTime;

    /**签约时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date contractTime;

	public Date getContractTime() {
		return contractTime;
	}

	public void setContractTime(Date contractTime) {
		this.contractTime = contractTime;
	}

	/**
	 * @Author: alewu
	 * @return: void renterId
	 * @Description: 设置租客id
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setRenterId(Long renterId){
		this.renterId = renterId;
	}

	/**
	 * @Author: alewu
	 * @return: return Long
	 * @Description: 获取租客id
	 * @date:2017年09月15日 19:59:49
	 **/
	public Long getRenterId(){
		return this.renterId;
	}

	/**
	 * @Author: alewu
	 * @return: void roomId
	 * @Description: 设置房间id
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setRoomId(Long roomId){
		this.roomId = roomId;
	}

	/**
	 * @Author: alewu
	 * @return: return Long
	 * @Description: 获取房间id
	 * @date:2017年09月15日 19:59:49
	 **/
	public Long getRoomId(){
		return this.roomId;
	}

	/**
	 * @Author: alewu
	 * @return: void renterMobile
	 * @Description: 设置手机号码
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setRenterMobile(String renterMobile){
		this.renterMobile = renterMobile;
	}

	/**
	 * @Author: alewu
	 * @return: return String
	 * @Description: 获取手机号码
	 * @date:2017年09月15日 19:59:49
	 **/
	public String getRenterMobile(){
		return this.renterMobile;
	}

	/**
	 * @Author: alewu
	 * @return: void renterName
	 * @Description: 设置租客姓名
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setRenterName(String renterName){
		this.renterName = renterName;
	}

	/**
	 * @Author: alewu
	 * @return: return String
	 * @Description: 获取租客姓名
	 * @date:2017年09月15日 19:59:49
	 **/
	public String getRenterName(){
		return this.renterName;
	}

	/**
	 * @Author: alewu
	 * @return: void wechatId
	 * @Description: 设置微信号
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setWechatId(String wechatId){
		this.wechatId = wechatId;
	}

	/**
	 * @Author: alewu
	 * @return: return String
	 * @Description: 获取微信号
	 * @date:2017年09月15日 19:59:49
	 **/
	public String getWechatId(){
		return this.wechatId;
	}

	/**
	 * @Author: alewu
	 * @return: void openId
	 * @Description: 设置微信普通用户的标识
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setOpenId(String openId){
		this.openId = openId;
	}

	/**
	 * @Author: alewu
	 * @return: return String
	 * @Description: 获取微信普通用户的标识
	 * @date:2017年09月15日 19:59:49
	 **/
	public String getOpenId(){
		return this.openId;
	}

	/**
	 * @Author: alewu
	 * @return: void idCard
	 * @Description: 设置身份证
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setIdCard(String idCard){
		this.idCard = idCard;
	}

	/**
	 * @Author: alewu
	 * @return: return String
	 * @Description: 获取身份证
	 * @date:2017年09月15日 19:59:49
	 **/
	public String getIdCard(){
		return this.idCard;
	}

	/**
	 * @Author: alewu
	 * @return: void renterImage
	 * @Description: 设置租客身份证图片
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setRenterImage(String renterImage){
		this.renterImage = renterImage;
	}

	/**
	 * @Author: alewu
	 * @return: return String
	 * @Description: 获取租客身份证图片
	 * @date:2017年09月15日 19:59:49
	 **/
	public String getRenterImage(){
		return this.renterImage;
	}

	/**
	 * @Author: alewu
	 * @return: void tempImage
	 * @Description: 设置临时租客身份证图片
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setTempImage(String tempImage){
		this.tempImage = tempImage;
	}

	/**
	 * @Author: alewu
	 * @return: return String
	 * @Description: 获取临时租客身份证图片
	 * @date:2017年09月15日 19:59:49
	 **/
	public String getTempImage(){
		return this.tempImage;
	}

	/**
	 * @Author: alewu
	 * @return: void startTime
	 * @Description: 设置起租日
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}

	/**
	 * @Author: alewu
	 * @return: return java.util.Date
	 * @Description: 获取起租日
	 * @date:2017年09月15日 19:59:49
	 **/
	public Date getStartTime(){
		return this.startTime;
	}

	/**
	 * @Author: alewu
	 * @return: void endTime
	 * @Description: 设置到租日
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}

	/**
	 * @Author: alewu
	 * @return: return java.util.Date
	 * @Description: 获取到租日
	 * @date:2017年09月15日 19:59:49
	 **/
	public Date getEndTime(){
		return this.endTime;
	}

	/**
	 * @Author: alewu
	 * @return: void payTime
	 * @Description: 设置交租日
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setPayTime(Integer payTime){
		this.payTime = payTime;
	}

	/**
	 * @Author: alewu
	 * @return: return java.util.Date
	 * @Description: 获取交租日
	 * @date:2017年09月15日 19:59:49
	 **/
	public Integer getPayTime(){
		return this.payTime;
	}

	/**
	 * @Author: alewu
	 * @return: void payPeriod
	 * @Description: 设置交租周期：1:1月，2:2月，...,12：12月
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setPayPeriod(Integer payPeriod){
		this.payPeriod = payPeriod;
	}

	/**
	 * @Author: alewu
	 * @return: return Integer
	 * @Description: 获取交租周期：1:1月，2:2月，...,12：12月
	 * @date:2017年09月15日 19:59:49
	 **/
	public Integer getPayPeriod(){
		return this.payPeriod;
	}

	/**
	 * @Author: alewu
	 * @return: void deposit
	 * @Description: 设置押金
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setDeposit(java.math.BigDecimal deposit){
		this.deposit = deposit;
	}

	/**
	 * @Author: alewu
	 * @return: return java.math.BigDecimal
	 * @Description: 获取押金
	 * @date:2017年09月15日 19:59:49
	 **/
	public java.math.BigDecimal getDeposit(){
		return this.deposit;
	}

	/**
	 * @Author: alewu
	 * @return: void rentMonthly
	 * @Description: 设置月租金
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setRentMonthly(java.math.BigDecimal rentMonthly){
		this.rentMonthly = rentMonthly;
	}

	/**
	 * @Author: alewu
	 * @return: return java.math.BigDecimal
	 * @Description: 获取月租金
	 * @date:2017年09月15日 19:59:49
	 **/
	public java.math.BigDecimal getRentMonthly(){
		return this.rentMonthly;
	}

	/**
	 * @Author: alewu
	 * @return: void renterNum
	 * @Description: 设置入住人数
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setRenterNum(Integer renterNum){
		this.renterNum = renterNum;
	}

	/**
	 * @Author: alewu
	 * @return: return Integer
	 * @Description: 获取入住人数
	 * @date:2017年09月15日 19:59:49
	 **/
	public Integer getRenterNum(){
		return this.renterNum;
	}

	/**
	 * @Author: alewu
	 * @return: void remindDay
	 * @Description: 设置提醒天数
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setRemindDay(Integer remindDay){
		this.remindDay = remindDay;
	}

	/**
	 * @Author: alewu
	 * @return: return Integer
	 * @Description: 获取提醒天数
	 * @date:2017年09月15日 19:59:49
	 **/
	public Integer getRemindDay(){
		return this.remindDay;
	}

	/**
	 * @Author: alewu
	 * @return: void remindTime
	 * @Description: 设置提醒时间
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setRemindTime(Date remindTime){
		this.remindTime = remindTime;
	}

	/**
	 * @Author: alewu
	 * @return: return java.util.Date
	 * @Description: 获取提醒时间
	 * @date:2017年09月15日 19:59:49
	 **/
	public Date getRemindTime(){
		return this.remindTime;
	}

	/**
	 * @Author: alewu
	 * @return: void remindRemark
	 * @Description: 设置提醒备注
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setRemindRemark(String remindRemark){
		this.remindRemark = remindRemark;
	}

	/**
	 * @Author: alewu
	 * @return: return String
	 * @Description: 获取提醒备注
	 * @date:2017年09月15日 19:59:49
	 **/
	public String getRemindRemark(){
		return this.remindRemark;
	}

	/**
	 * @Author: alewu
	 * @return: void emergencyContact
	 * @Description: 设置紧急联系人
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setEmergencyContact(String emergencyContact){
		this.emergencyContact = emergencyContact;
	}

	/**
	 * @Author: alewu
	 * @return: return String
	 * @Description: 获取紧急联系人
	 * @date:2017年09月15日 19:59:49
	 **/
	public String getEmergencyContact(){
		return this.emergencyContact;
	}

	/**
	 * @Author: alewu
	 * @return: void emergencyMobile
	 * @Description: 设置紧急联系人号码
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setEmergencyMobile(String emergencyMobile){
		this.emergencyMobile = emergencyMobile;
	}

	/**
	 * @Author: alewu
	 * @return: return String
	 * @Description: 获取紧急联系人号码
	 * @date:2017年09月15日 19:59:49
	 **/
	public String getEmergencyMobile(){
		return this.emergencyMobile;
	}

	/**
	 * @Author: alewu
	 * @return: void contractPicture
	 * @Description: 设置合同图片
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setContractPicture(String contractPicture){
		this.contractPicture = contractPicture;
	}

	/**
	 * @Author: alewu
	 * @return: return String
	 * @Description: 获取合同图片
	 * @date:2017年09月15日 19:59:49
	 **/
	public String getContractPicture(){
		return this.contractPicture;
	}

	/**
	 * @Author: alewu
	 * @return: void renterStatus
	 * @Description: 设置状态：1：未绑定，2：已绑定,3:已退租
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setRenterStatus(Integer renterStatus){
		this.renterStatus = renterStatus;
	}

	/**
	 * @Author: alewu
	 * @return: return Integer
	 * @Description: 获取状态：1：未绑定，2：已绑定,3:已退租
	 * @date:2017年09月15日 19:59:49
	 **/
	public Integer getRenterStatus(){
		return this.renterStatus;
	}

	/**
	 * @Author: alewu
	 * @return: void createTime
	 * @Description: 设置创建时间
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	/**
	 * @Author: alewu
	 * @return: return java.util.Date
	 * @Description: 获取创建时间
	 * @date:2017年09月15日 19:59:49
	 **/
	public Date getCreateTime(){
		return this.createTime;
	}

	/**
	 * @Author: alewu
	 * @return: void updateTime
	 * @Description: 设置更新时间
	 * @date:2017年09月15日 19:59:49
	 **/
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}

	/**
	 * @Author: alewu
	 * @return: return java.util.Date
	 * @Description: 获取更新时间
	 * @date:2017年09月15日 19:59:49
	 **/
	public Date getUpdateTime(){
		return this.updateTime;
	}

}
