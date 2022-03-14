package org.kowalsky.bankingapi.model;

public class CurrencyRate {

    private Integer buyCode;
    private Double buyRate;
    private Integer sellCode;
    private Double sellRate;
    private Integer quantity;
    private String date;

    public Integer getBuyCode() {
        return buyCode;
    }

    public void setBuyCode(Integer buyCode) {
        this.buyCode = buyCode;
    }

    public Double getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(Double buyRate) {
        this.buyRate = buyRate;
    }

    public Integer getSellCode() {
        return sellCode;
    }

    public void setSellCode(Integer sellCode) {
        this.sellCode = sellCode;
    }

    public Double getSellRate() {
        return sellRate;
    }

    public void setSellRate(Double sellRate) {
        this.sellRate = sellRate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
