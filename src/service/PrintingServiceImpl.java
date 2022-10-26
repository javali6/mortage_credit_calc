package service;

import model.InputData;
import model.Overpayment;
import model.Rate;
import model.Summary;
import model.exeption.MortgageException;

import java.util.List;
import java.util.Optional;

public class PrintingServiceImpl implements PrintingService {

    @Override
    public void printInputDataInfo(InputData inputData) {
        StringBuilder message = new StringBuilder(NEW_LINE);
        message.append(MORTGAGE_AMOUNT).append(inputData.getAmount()).append(CURRENCY);
        message.append(NEW_LINE);
        message.append(MORTGAGE_PERIOD).append(inputData.getMonthsDuration()).append(MONTHS);
        message.append(NEW_LINE);
        message.append(INTEREST).append(inputData.getInterestDisplay()).append(PERCENT);
        message.append(NEW_LINE);

        Optional.of(inputData.getOverpaymentSchema())
                        .filter(schema -> schema.size() > 0)
                .ifPresent(schema -> logOverpayment(message, inputData));

        printMessage(message);

    }

    private void logOverpayment(StringBuilder message, InputData inputData) {
        switch (inputData.getOverpaymentReduceWay()) {
            case Overpayment.REDUCE_PERIOD -> message.append(OVERPAYMENT_REDUCE_PERIOD);
            case Overpayment.REDUCE_RATE -> message.append(OVERPAYMENT_REDUCE_RATE);
            default -> throw new MortgageException();
        }

        message.append(NEW_LINE);
        message.append(OVERPAYMENT_FREQUENCY).append(inputData.getOverpaymentSchema());
        message.append(NEW_LINE);

    }

    @Override
    public void printRates(List<Rate> rates) {
        for (Rate rate : rates) {
            String format = "%4s%3s  " +
                    "%4s%2s  " +
                    "%4s%2s  " +
                    "%4s%2s  " +
                    "%4s%10s%s  " +
                    "%4s%10s%s  " +
                    "%4s%10s%s  " +
                    "%4s%10s%s  " +
                    "%4s%2s  ";

            String message = String.format(format,
                    RATE_NUMBER, rate.getRateNumber(),
                    DATE, rate.getTimePoint().getDate(),
                    YEAR, rate.getTimePoint().getYear(),
                    MONTH, rate.getTimePoint().getMonth(),
                    RATE, rate.getRateAmounts().getRateAmount(), CURRENCY,
                    INTEREST, rate.getRateAmounts().getInterestAmount(),CURRENCY,
                    CAPITAL, rate.getRateAmounts().getCapitalAmount(),CURRENCY,
                    OVERPAYMENT, rate.getRateAmounts().getOverpayment().getAmount(), CURRENCY,
                    LEFT_AMOUNT, rate.getMortgageResidual().getAmount(),CURRENCY,
                    LEFT_MONTHS, rate.getMortgageResidual().getDuration());

            printMessage(new StringBuilder(message));

            if (rate.getRateNumber().intValue() % 12 == 0) {
                System.out.println();
                System.out.println("---------------------------------" +
                        "--------------------------------------------");

            }

        }

    }

    @Override
    public void printSummary(Summary summary) {
        StringBuilder message = new StringBuilder(NEW_LINE);
        message.append(INTEREST_SUM).append(summary.getInterestSum()).append(CURRENCY);
        message.append(NEW_LINE);
        message.append(OVERPAYMENT_PROVISION).append(summary.getOverpaymentProvisions()
                ).append(CURRENCY);
        message.append(NEW_LINE);
        message.append(LOSTS_SUM).append(summary.getTotalLosts()).append(CURRENCY);
        message.append(NEW_LINE);



        printMessage(message);
    }

    private void printMessage(StringBuilder sb) {
        System.out.println(sb.toString());
    }
}
