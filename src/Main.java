import model.InputData;
import service.*;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {


        InputData inputData = new InputData()
                .withAmount(new BigDecimal(350000))
                .withMonthsDuration(BigDecimal.valueOf(320));

        PrintingService printingService = new PrintingServiceImpl();
        RateCalculationService rateCalculationService = new RateCalculationServiceImpl(
                new TimePointServiceImpl(),
                new AmountsCalculateServiceImpl(),
                new ResidualCalculationServiceImpl(),
                new ReferenceCalculationServiceImpl(),
                new OverpaymentCalculationServiceImpl()

        );

        MortgageCalculationServiceImpl mortgageCalculationService = new MortgageCalculationServiceImpl(printingService
        ,rateCalculationService
        ,SummaryServiceFactory.create());
        mortgageCalculationService.calculate(inputData);


    }

}
