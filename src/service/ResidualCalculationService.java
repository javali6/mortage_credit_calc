package service;

import model.InputData;
import model.MortgageResidual;
import model.Rate;
import model.RateAmounts;

public interface ResidualCalculationService {

   public MortgageResidual calculate(RateAmounts rateAmounts, InputData inputData);

   public MortgageResidual calculate(RateAmounts rateAmounts, Rate previousRate);


}
