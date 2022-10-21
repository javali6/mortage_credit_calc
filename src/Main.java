import model.InputData;
import service.*;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {


        InputData inputData = new InputData()
                .withAmount(new BigDecimal(245000))
                .withMonthsDuration(BigDecimal.valueOf(180));

        PrintingService printingService = new PrintingServiceImpl();
        RateCalculationService rateCalculationService = new RateCalculationServiceImpl(
                new TimePointServiceImpl(),
                new AmountsCalculateServiceImpl(),
                new ResidualCalculationServiceImpl()

        );

        MortgageCalculationServiceImpl mortgageCalculationService = new MortgageCalculationServiceImpl(printingService
        ,rateCalculationService);
        mortgageCalculationService.calculate(inputData);


    }

}
