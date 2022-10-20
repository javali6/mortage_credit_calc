package service;

import model.InputData;
import model.Rate;
import model.RateAmounts;

public interface AmonutsCalculateService {

        public RateAmounts calculate(InputData inputData);

        RateAmounts calculate(InputData inputData, Rate previousRate);
}
