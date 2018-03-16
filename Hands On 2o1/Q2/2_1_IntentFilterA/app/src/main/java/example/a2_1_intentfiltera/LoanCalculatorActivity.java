package example.a2_1_intentfiltera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

/** Calculates and displays the monthly payments and total payment amount
 *  for loans with a given principal, interest rate, and period. Used
 *  as the target Activity of a series of examples of invoking
 *  Activities by class name, URI, and via a tabbed window.
 *  <p>
 *  From <a href="http://www.coreservlets.com/android-tutorial/">
 *  the coreservlets.com Android programming tutorial series</a>.
 */

public class LoanCalculatorActivity extends Activity {
    private double mLoanAmount=100000, mAnnualInterestRateInPercent=5.0;
    private long mLoanPeriodInMonths=360; // 30 years = 360 months
    private String mCurrencySymbol = "$";
    
    /** Initializes the app when it is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loan_payments);
        setInputsFromExtras();
        setInputsFromUri();
        calculateAndSetOutputValues();
    }
    
    /** Sets the instance variables for loan amount, interest rate, loan period and currency
     *  symbol from the "extras" Bundle if it exists. If it exists, that means it was
     *  passed in from another Activity that invoked this one.
     */
    private void setInputsFromExtras() {
        Intent intent = getIntent();
        Bundle loanInfo = intent.getExtras();
        if (loanInfo != null) {
            double loanAmount = loanInfo.getDouble("loanAmount");
            double annualInterestRateInPercent = 
                    loanInfo.getDouble("annualInterestRateInPercent");
            long loanPeriodInMonths = 
                    loanInfo.getLong("loanPeriodInMonths");
            String currencySymbol = 
                    loanInfo.getString("currencySymbol");
            setInputs(loanAmount, annualInterestRateInPercent,
                      loanPeriodInMonths,  currencySymbol);
        }
    }
    
    private void setInputs(double loanAmount, 
                           double annualInterestRateInPercent,
                           long loanPeriodInMonths, 
                           String currencySymbol) {
        if (loanAmount > 0) {
            mLoanAmount = loanAmount;
        }
        if (annualInterestRateInPercent > 0) {
            mAnnualInterestRateInPercent = 
                    annualInterestRateInPercent;
        }
        if (loanPeriodInMonths > 0) {
            mLoanPeriodInMonths = loanPeriodInMonths;
        }
        if (currencySymbol != null) {
            mCurrencySymbol = currencySymbol;
        }
    }
    
    /** Sets the instance variables for loan amount, interest rate, loan period and currency
     *  symbol from the URI query parameters, if they exist. If they exist, that means they
     *  were passed in on the end of the URI from another Activity that invoked this one.
     */
    private void setInputsFromUri() {
        Uri uri = getIntent().getData(); // null when Activity invoked by class name
        if (uri != null) {
            double loanAmount = getDoubleParam(uri, "loanAmount");
            double annualInterestRateInPercent = 
                    getDoubleParam(uri, "annualInterestRateInPercent");
            long loanPeriodInMonths = getLongParam(uri, "loanPeriodInMonths");
            String currencySymbol = uri.getQueryParameter("currencySymbol");
            setInputs(loanAmount, annualInterestRateInPercent,
                      loanPeriodInMonths, currencySymbol);
        }
    }
    
    private double getDoubleParam(Uri uri, String queryParamName) {
        String rawValue = uri.getQueryParameter(queryParamName);
        double value = 0.0;
        try {
            value = Double.parseDouble(rawValue);
        } catch(Exception e) { } // NumberFormatException or NullPointerException
        return(value);
    }
    
    private long getLongParam(Uri uri, String queryParamName) {
        String rawValue = uri.getQueryParameter(queryParamName);
        long value = 0;
        try {
            value = Long.parseLong(rawValue);
        } catch(Exception e) { } // NumberFormatException or NullPointerException
        return(value);
    }
    
    private void calculateAndSetOutputValues() {
        PaymentInfo paymentInfo = 
                new PaymentInfo(mLoanAmount, mAnnualInterestRateInPercent, 
                                mLoanPeriodInMonths, mCurrencySymbol);
        TextView loanAmountDisplay = (TextView)findViewById(R.id.loan_amount);
        loanAmountDisplay.setText(paymentInfo.getFormattedLoanAmount());
        TextView interestRateDisplay = (TextView)findViewById(R.id.interest_rate);
        interestRateDisplay.setText(paymentInfo.getFormattedAnnualInterestRateInPercent());
        TextView loanPeriodDisplay = (TextView)findViewById(R.id.loan_period);
        loanPeriodDisplay.setText(paymentInfo.getFormattedLoanPeriodInMonths());
        TextView monthlyPaymentDisplay = (TextView)findViewById(R.id.monthly_payment);
        monthlyPaymentDisplay.setText(paymentInfo.getFormattedMonthlyPayment());
        TextView totalPaymentsDisplay = (TextView)findViewById(R.id.total_payments);
        totalPaymentsDisplay.setText(paymentInfo.getFormattedTotalPayments());
    }
}