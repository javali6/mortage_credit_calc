package service;

import model.InputData;
import model.Rate;

import java.util.List;

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

        printMessage(message);

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
                    LEFT_AMOUNT, rate.getMortgageResidual().getAmount(),CURRENCY,
                    LEFT_MONTHS, rate.getMortgageResidual().getDuration());

            printMessage(new StringBuilder(message));
        }


    }

    private void printMessage(StringBuilder sb) {
        System.out.println(sb.toString());
    }
}
