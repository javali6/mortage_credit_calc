package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InputData {

    //...
    private LocalDate repaymentStartDate = LocalDate.of(2022, 4, 12);

    private BigDecimal wiborPercent = new BigDecimal("7.05");

    private BigDecimal amount = new BigDecimal("300000");

    private BigDecimal monthsDuration = BigDecimal.valueOf(180);

    private BigDecimal bankMargin = new BigDecimal("1.95");

    private RateType rateType = RateType.CONSTANT;


    //...
    public InputData withRepaymentStartDate(LocalDate repaymentStartDate) {
        this.repaymentStartDate = repaymentStartDate;
        return this;
    }

    public InputData withWiborPercent(BigDecimal wiborPercent) {
        this.wiborPercent = wiborPercent;
        return this;
    }

    public InputData withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public InputData withMonthsDuration(BigDecimal monthsDuration) {
        this.monthsDuration = monthsDuration;
        return this;
    }

    public InputData withBankMargin(BigDecimal bankMargin) {
        this.bankMargin = bankMargin;
        return this;
    }

    public InputData withRateType(RateType rateType) {
        this.rateType = rateType;
        return this;
    }


    //GETTERS
    public LocalDate getRepaymentStartDate() {
        return repaymentStartDate;
    }

    public BigDecimal getWiborPercent() {
        return wiborPercent;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getMonthsDuration() {
        return monthsDuration;
    }

    public BigDecimal getBankMargin() {
        return bankMargin;
    }

    public RateType getRateType() {
        return rateType;
    }
}
