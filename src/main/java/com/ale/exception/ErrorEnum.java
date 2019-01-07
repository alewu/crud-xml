package com.ale.exception;


public enum ErrorEnum {

	//401
	UserIdNull("用户id为空",10001),
	UserNameNull("用户电话为空",10002),
	UserPhoneNull("用户手机号码为空",10003),
	UserExistence("手机号已被注册",10011),
	SidNull("短信sid为空",10013),
	CodeError("短信验证码不正确",10014),
	UserStatusError("用户状态不正确",10015),
	AreaIdNull("展区id为空",10020),
	AreaIdsNull("展区ids为空",10021),
	AreaRemakesNull("展区备注为空",10023),
	AreaNameNull( "展区名称为空",10024),
	AreaNull("展区不存在",10025),
	DeviceIdNull("设备Id为空",10030),
	DeviceTypeNull("设备类型为空",10031),
	DeviceNameNull("设备名称为空",10032),
	DeviceIpNull("设备Ip为空",10033),
	SampleIdNull("样品id为空",10090),
	SampleNameNull("样品名称为空",10091),
	SampleFileNull("样品图片空",10092),
	CompanyIdNull("公司ID为空", 10100),
	CompanyNameNull("公司名称为空", 10101),
	CompanypNameNull("公司法人名称为空", 10103),
	CompanypIdCardNull("公司法人身份证为空", 10104),
	CompanyLicenseImgNull("公司营业执照为空", 10105),
	CompanypCertified("该用户已经认证了公司", 10106),
	CompanyIdError("公司ID不正确", 10107),
	IllegalParameter("非法参数", 30000)	,
	ImageUpNull("图片上传失败", 30100),
	//展品
	ResourceIdNull("展品id为空",10060),
	ResourceRetIdNull("展品retId为空",10061),
	ResourceNameNull("展品名称为空",10062),
	ResourceCodeExsit("展品编号己存在",10063),
	
	//位置
	FloorIdNull("楼id为空",10200),
	LayerIdNull("层id为空",10210),
	RoomIdNull("房id为空",10220),
	UnitIdNull("位置id为空",10230),
	UnitNameNull("位置名称为空",10231),
	UnitCoordinateNull("位置坐标为空",10232),
	
	//资产名称
	AssetsCategoryIdNull("分类id为空",10240),
	AssetsCategoryTypeNull("分类类别为空",10241),
	AssetsCategoryNameNull("分类名称为空",10242),
	AssetsCategoryNameIlegal("分类名称非法",12043),
	
	//资产
	AssetsIdNull("资产id为空",10250),
	AssetsNameNull("资产名称为空",10251),
	AssetsNumberNull("资产编号为空",10252),
	AssetsStroageManNull("资产入库人员为空",10253),
	AssetsStorageNumberNull("资产数量为空",10254),
	AssetsRoomNull("房间信息为空",10255),
	AssetsNumberExsit("资产编号己存在",10256),
	AssetsNotExsit("资产不存在",10257),
	
	//展品分类
	ResourceCategoryIdNull("展品类别Id为空",10260),
	ResourceCategoryTypeNull("展品类别为空",10261),
	ResourceCategoryNameNull("展品类别名称为空",10262),
	
	
	//rfid
	BaseStationIdNull("基站id不能为空",10300),
	
	//404
	NotFound("数据不存在",404404),
	UserNotFound("用户不存在",404000),
	CompanyNotFound("公司不存在",404010),
	RfidBaseStationNotFound("rfid基站不存在",404020),
	RfidControllerNotFound("rfid控制器不存在",404021),
	
	//400 权限不足
	UserDisrble("该用户已被禁用",400000),
	CompanyNotCertified("公司未认证成功",400100),
	
	//500
	ServerInternalError("服务器内部错误",500000),
	;
	
	// 成员变量  
    private String message;  
    private int code;  
    // 构造方法  
    private ErrorEnum(String message, int code) {  
        this.message = message;  
        this.code = code;  
    }  
    // 普通方法  
    public static String getName(int code) {  
        for (ErrorEnum errorEnum : ErrorEnum.values()) {  
            if (errorEnum.getCode() == code) {  
                return errorEnum.message;  
            }  
        }  
        return null;  
    }
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}  
    
    

	
}
