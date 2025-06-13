package service.serviceimpl;

import dao.daoimpl.FeeDAOImpl;
import dao.interfaces.IFeeDAO;
import enums.CalculationType;
import model.Fee;
import utils.ConstantUtils;

/**
 *
 * @author manhphong
 */
public class FeeServiceImpl {

    private IFeeDAO feeDAO = new FeeDAOImpl();

    private double calculateFeeAmount(Fee fee, double basePrice, int passengers) {
        double amount = 0;

        if (fee.getCalculationType() == CalculationType.PERCENTAGE) {
            amount = (basePrice * fee.getValue() / 100) * passengers;
        } else if (fee.getCalculationType() == CalculationType.FIXED) {
            amount = fee.getValue() * passengers;
        }

        if (fee.getMinAmount() != null && amount < fee.getMinAmount()) {
            amount = fee.getMinAmount();
        }
        if (fee.getMaxAmount() != null && amount > fee.getMaxAmount()) {
            amount = fee.getMaxAmount();
        }
        return amount;
    }

    public double totalPriceForTicket(double basePrice) {
        return basePrice + calculateAmountOfFee(feeDAO.getFeeByName(ConstantUtils.SERVICE_FEE), basePrice)
                + calculateAmountOfFee(feeDAO.getFeeByName(ConstantUtils.PAYMENT_FEE), basePrice);
    }

    private double calculateAmountOfFee(Fee fee, double basePrice) {
        double amount = 0;
        if (fee.getCalculationType() == CalculationType.PERCENTAGE) {
            amount = (basePrice * fee.getValue() / 100);
        } else if (fee.getCalculationType() == CalculationType.FIXED) {
            amount = fee.getValue();
        }

        if (fee.getMinAmount() != null && amount < fee.getMinAmount()) {
            amount = fee.getMinAmount();
        }
        if (fee.getMaxAmount() != null && amount > fee.getMaxAmount()) {
            amount = fee.getMaxAmount();
        }
        return amount;
    }

    public static void main(String[] args) {

    }
}
