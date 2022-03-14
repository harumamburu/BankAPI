package org.kowalsky.bankingapi.model.alpha;

public class AlphaCurrencyRate {

    private Integer buyCode;
    private String buyIso;
    private Double buyRate;
    private String date;
    private String name;
    private Integer quantity;
    private Integer sellCode;
    private String sellIso;
    private Double sellRate;

    public Integer getBuyCode() {
        return buyCode;
    }

    public void setBuyCode(Integer buyCode) {
        this.buyCode = buyCode;
    }

    public String getBuyIso() {
        return buyIso;
    }

    public void setBuyIso(String buyIso) {
        this.buyIso = buyIso;
    }

    public Double getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(Double buyRate) {
        this.buyRate = buyRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSellCode() {
        return sellCode;
    }

    public void setSellCode(Integer sellCode) {
        this.sellCode = sellCode;
    }

    public String getSellIso() {
        return sellIso;
    }

    public void setSellIso(String sellIso) {
        this.sellIso = sellIso;
    }

    public Double getSellRate() {
        return sellRate;
    }

    public void setSellRate(Double sellRate) {
        this.sellRate = sellRate;
    }
}
