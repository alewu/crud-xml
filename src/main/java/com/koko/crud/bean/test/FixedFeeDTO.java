package com.koko.crud.bean.test;

import java.math.BigDecimal;

/**
 * @author alewu
 * @date 2017/10/24 16:03
 * @description 更新费用DTO
 */
public class FixedFeeDTO {

    /**固定费用id**/
    private Long fixedFeeId;

    /**费用单价**/
    private BigDecimal feePrice;


    public Long getFixedFeeId() {
        return fixedFeeId;
    }


    public void setFixedFeeId(Long fixedFeeId) {
        this.fixedFeeId = fixedFeeId;
    }


    public BigDecimal getFeePrice() {
        return feePrice;
    }


    public void setFeePrice(BigDecimal feePrice) {
        this.feePrice = feePrice;
    }

    @Override
    public String toString() {
        return "FixedFeeDTO{" +
                "fixedFeeId=" + fixedFeeId +
                ", feePrice=" + feePrice +
                '}';
    }
}
