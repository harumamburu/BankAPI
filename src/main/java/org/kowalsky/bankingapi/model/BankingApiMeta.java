package org.kowalsky.bankingapi.model;

public class BankingApiMeta {

    private String bankCode;
    private String apiBase;
    private String version;
    private String currencyEndpoint;

    public BankingApiMeta() {
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getApiBase() {
        return apiBase;
    }

    public void setApiBase(String apiBase) {
        this.apiBase = apiBase;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCurrencyEndpoint() {
        return currencyEndpoint;
    }

    public void setCurrencyEndpoint(String currencyEndpoint) {
        this.currencyEndpoint = currencyEndpoint;
    }
}
