package service;

import model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

public class RateCalculationServiceImpl implements RateCalculationService {

    private final TimePointService timePointService;

    private final AmountsCalculateService amountsCalculateService;

    private final ResidualCalculationService residualCalculationService;

    private final OverpaymentCalculationService overpaymentCalculationService;

    private final ReferenceCalculationService referenceCalculationService;

    public RateCalculationServiceImpl(TimePointService timePointService,
                                      AmountsCalculateService amountsCalculateService,
                                      ResidualCalculationService residualCalculationService,
                                      ReferenceCalculationService referenceCalculationService,
                                      OverpaymentCalculationService overpaymentCalculationService) {
        this.timePointService = timePointService;
        this.amountsCalculateService = amountsCalculateService;
        this.residualCalculationService = residualCalculationService;
        this.overpaymentCalculationService = overpaymentCalculationService;
        this.referenceCalculationService = referenceCalculationService;
    }

    @Override
    public List<Rate> calculate(InputData inputData) {
        List<Rate> rates = new LinkedList<>();

        BigDecimal rateNumber = BigDecimal.ONE;

        Rate firstRate = calculateRate(rateNumber, inputData);
        rates.add(firstRate);

        Rate previousRate = firstRate;

        for (BigDecimal index=rateNumber.add(BigDecimal.ONE);
             index.compareTo(inputData.getMonthsDuration()) <= 0;
             index = index.add(BigDecimal.ONE)
        ) {
            Rate nextRate = calculateRate(index, inputData, previousRate);
            rates.add(nextRate);
            previousRate = nextRate;

            if (BigDecimal.ZERO.equals(nextRate.getMortgageResidual().getAmount()
                    .setScale(0, RoundingMode.HALF_UP))) {
                break;
            }

        }

        return rates;

    }

    private Rate calculateRate(BigDecimal rateNumber, InputData inputData) {

        TimePoint timePoint = timePointService.calculate(rateNumber, inputData);
        Overpayment overpayment = overpaymentCalculationService.calculate(rateNumber, inputData);
        RateAmounts rateAmounts = amountsCalculateService.calculate(inputData, overpayment);
        MortgageResidual mortgageResidual = residualCalculationService.calculate(rateAmounts, inputData);

        MortgageReference mortgageReference = referenceCalculationService.calculate(inputData);

        return new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual, mortgageReference);

    }

    private Rate calculateRate(BigDecimal rateNumber, InputData inputData, Rate previousRate) {
        TimePoint timePoint = timePointService.calculate(rateNumber, inputData);
        Overpayment overpayment = overpaymentCalculationService.calculate(rateNumber, inputData);
        RateAmounts rateAmounts = amountsCalculateService.calculate(inputData, overpayment, previousRate);
        MortgageResidual mortgageResidual = residualCalculationService.calculate(rateAmounts, previousRate);


        MortgageReference mortgageReference = referenceCalculationService.calculate(inputData);

        return new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual, mortgageReference);

    }
}
