package example.a2_1_intentfiltera;

import android.os.Bundle;

import java.util.Random;

/** Contains some static methods for making Bundles appropriate for use
 *  in the LoanCalculatorActivity.
 *  <p>
 *  From <a href="http://www.coreservlets.com/android-tutorial/">
 *  the coreservlets.com Android programming tutorial series</a>.
 */

public class LoanBundler {
    /** Creates and returns a Bundle that stores values under the names 
     *  used in the LoanCalculatorActivity. 
     */
    public static Bundle makeLoanInfoBundle(double loanAmount, 
                                            double annualInterestRateInPercent,
                                            long loanPeriodInMonths, 
                                            String currencySymbol) {
        Bundle loanInfo = new Bundle();
        loanInfo.putDouble("loanAmount", loanAmount);
        loanInfo.putDouble("annualInterestRateInPercent", annualInterestRateInPercent);
        loanInfo.putLong("loanPeriodInMonths", loanPeriodInMonths);
        loanInfo.putString("currencySymbol", currencySymbol);
        return(loanInfo);
    }
    
    /** Creates and returns a Bundle that stores values under the names 
     *  used in the LoanCalculatorActivity. Assumes that the currency symbol is "$".
     */
    public static Bundle makeLoanInfoBundle(double loanAmount, double annualInterestRateInPercent,
                                            long loanPeriodInMonths) {
        return(makeLoanInfoBundle(loanAmount, annualInterestRateInPercent, loanPeriodInMonths, "$"));
    }
    
    /** Creates and returns a Bundle for the LoanCalculatorActivity that has randomized values. */
    
    public static Bundle makeRandomizedLoanInfoBundle() {
        Random randomizer = new Random();
        double loanAmount = 25000 * (1 + randomizer.nextInt(10));
        double interestRate = 0.25 * (1 + randomizer.nextInt(60));
        long loanPeriod = 12 * (1 + randomizer.nextInt(30));
        return(LoanBundler.makeLoanInfoBundle(loanAmount, 
                                              interestRate, loanPeriod));
    }
}
