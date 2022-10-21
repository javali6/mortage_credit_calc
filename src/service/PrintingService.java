package service;

import model.InputData;
import model.Rate;

import java.util.List;

public interface PrintingService {

    String INTEREST_SUM = "SUMA ODSETEK";
    String RATE_NUMBER = "NR: ";
    String YEAR = "ROK: ";
    String MONTH = "MIESIĄC : ";
    String DATE = "DATA: ";
    String MONTHS = " MIESIĘCY";
    String RATE = "RATA: ";
    String INTEREST = "ODSETKI: ";
    String CAPITAL = "KAPITAŁ: ";
    String LEFT_AMOUNT = "POZOSTAŁA KWOTA: ";

    String LEFT_MONTHS = "POZOSTAŁO MIESIĘCY: ";
    String MORTGAGE_AMOUNT = "KWOTA KREDYTU: ";
    String MORTGAGE_PERIOD = "OKRES KREDYTOWANIA: ";

    String CURRENCY = " ZL ";
    String NEW_LINE = "\n";
    String PERCENT = "% ";


    void printInputDataInfo (InputData inputData);

    void printRates(List<Rate>rates);
}
