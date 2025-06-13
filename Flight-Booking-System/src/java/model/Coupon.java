package model;

import java.time.LocalDateTime;

/**
 *
 * @author manhphong
 * @version 1.0
 */
public class Coupon {

    private Long couponId;
    private String couponCode;
    private Integer discountPercentage;
    private Integer maxUsage;
    private Integer usedCount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;
    private LocalDateTime createdAt;

    public Coupon() {
    }

    public Coupon(String couponCode, Integer discountPercentage, Integer maxUsage, Integer usedCount, LocalDateTime startDate, LocalDateTime endDate, String status, LocalDateTime createdAt) {
        this.couponCode = couponCode;
        this.discountPercentage = discountPercentage;
        this.maxUsage = maxUsage;
        this.usedCount = usedCount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.createdAt = createdAt;
    }

    
    public Coupon(Long couponId, String couponCode, Integer discountPercentage, Integer maxUsage, Integer usedCount, LocalDateTime startDate, LocalDateTime endDate, String status, LocalDateTime createdAt) {
        this.couponId = couponId;
        this.couponCode = couponCode;
        this.discountPercentage = discountPercentage;
        this.maxUsage = maxUsage;
        this.usedCount = usedCount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage (Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Integer getMaxUsage() {
        return maxUsage;
    }

    public void setMaxUsage(Integer maxUsage) {
        this.maxUsage = maxUsage;
    }

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Coupon{" + "couponId=" + couponId + ", couponCode=" + couponCode + ", discountPercentage=" + discountPercentage + ", maxUsage=" + maxUsage + ", usedCount=" + usedCount + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + ", createdAt=" + createdAt + '}';
    }
}
