package model;

import enums.CalculationType;
import java.util.Date;

/**
 *
 * @author manhphong
 */
public class Fee {

    private long configId;
    private String feeName;
    private CalculationType calculationType;
    private double value;
    private Double minAmount;
    private Double maxAmount;
    private Date createdAt;


    // Constructors
    public Fee() {
    }

    public Fee(String feeName, CalculationType calculationType, double value) {
        this.feeName = feeName;
        this.calculationType = calculationType;
        this.value = value;
        this.createdAt = new Date();
    }

    public Fee(long configId, String feeName, CalculationType calculationType,
            double value, Double minAmount, Double maxAmount) {
        this.configId = configId;
        this.feeName = feeName;
        this.calculationType = calculationType;
        this.value = value;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.createdAt = new Date();
    }

    public long getConfigId() {
        return configId;
    }

    public void setConfigId(long configId) {
        this.configId = configId;
    }

    public String getFeeName() {
        return feeName;
    }

    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    public CalculationType getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(CalculationType calculationType) {
        this.calculationType = calculationType;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
