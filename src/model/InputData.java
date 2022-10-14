package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InputData {

    private LocalDate repaymentStartDate = LocalDate.of(2022, 4, 12);

    private BigDecimal wiborPercent = new BigDecimal("7.05");

    private BigDecimal amount = new BigDecimal("300000");

    private BigDecimal monthsDuration = BigDecimal.valueOf(180);


}
