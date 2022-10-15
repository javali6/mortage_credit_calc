package service;

import model.InputData;

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

    private void printMessage(StringBuilder sb) {
        System.out.println(sb.toString());
    }

}
