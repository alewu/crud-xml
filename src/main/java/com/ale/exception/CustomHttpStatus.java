package com.ale.exception;

/**
 * @author chenkai
 * @date 2017年9月18日 下午2:22:31
 * @Description CustomHttpStatus
 */
public enum CustomHttpStatus {
    /**
     * 系统用户验证
     **/
    SysUserIdNull("用户ID不能为空", 20401), SysUserNameNull("姓名不能为空", 20402), SysUserUserNameNull(
            "用户名不能为空", 20403), SysUserUserNameError("用户名错误", 20404), SysUserPasswordNull(
            "密码不能为空", 20405), SysUserMobileNull("手机号不能为空", 20406), SysUserEmailNull(
            "邮箱不能为空", 20407), SysUserNameExists("用户名已经存在", 20408), SysUserMobileExists(
            "手机号已经存在", 20409), SysUserCaptchaNull("验证码不为能空", 204010), SysUserCaptchaError(
            "验证码不正确", 20411), OldPasswordNull("旧密码不能为空", 20412), NewPasswordNull(
            "新密码不能为空", 20413), OldPasswordError("原密码不正确", 20414), EditPasswordError(
            "密码修改失败", 20415), EditByUserIdRoleError("分配角色失败", 20416), AdminNoRemove(
            "系统管理员不能删除", 20417), NowUserNoRemove("当前用户不能删除", 20418),
    /**
     * 系统角色验证
     **/
    SysRoleIdNull("角色ID不能为空", 20419), SysRoleNameNull("角色名称不能为空", 20420), SysRoleNameExists(
            "角色名称已经存在", 20421),
    /**
     * 系统配置验证
     **/
    SysConfigIdNull("系统配置ID不能为空", 20422), SysConfigKeyNull("key值不能为空", 20423), SysConfigNameNull(
            "名称值不能为空", 20424), SysConfigValueNull("value不能为空", 20425), SysConfigKeyExists(
            "key已经存在", 20426),
    /**
     * 其他格式验证
     **/
    JsonCheck("非json字符串", 20427), TokenNull("查询不到token", 20428),
    /**
     * 业务异常
     **/
    AddError("添加失败", 50001), EditError("修改失败", 50002), RemoveError("删除失败",
            50003),
    /**
     * 验证码
     **/
    AuthCodeError("发送验证码失败", 10001), AuthCodeVerify("验证码不一致", 10002),
    /**
     * 房东端验证
     **/
    MobileExists("该手机号已经注册", 30001), RealNameNull("姓名不能为空", 30002), AddressNull("地址不能为空", 30003),
    UserNameOrPassword("用户名或密码错误", 30004),
    /**
     * 租客端验证
     **/
    RenterStatusVerify("该租客未通过审核", 40001), CharterExists("已签约成功，不用重复签约", 40002), CharterError("签约失败", 40003),
    BindError("绑定失败", 40004), BindExists("已经绑定成功，不需要重复绑定", 40005), MobileNotExists("查不到该手机号的信息", 40006),
    /**
     * 通用校验
     **/
    checkMobile("手机号码格式有误", 60001);

    private String message;

    private int status;

    /**
     *
     */
    private CustomHttpStatus() {
        // TODO Auto-generated constructor stub
    }

    private CustomHttpStatus(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
