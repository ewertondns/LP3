package example.a2_1_intentfiltera;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/** Demonstrates the simplest form of Activity switching: 
 *  invoking another Activity by class name. There are
 *  two variations here: invoking the other Activity
 *  with no data, and invoking the other Activity and
 *  sending data in the "extras" data. Also see IntentFilter2Activity,
 *  which demonstrates how to register an intent filter for a URI
 *  so that the Activity can be invoked in a more general-purpose manner,
 *  without having to know the class name.
 *  <p>
 *  From <a href="http://www.coreservlets.com/android-tutorial/">
 *  the coreservlets.com Android programming tutorial series</a>.
 */

public class IntentFilter1Activity extends Activity {
    /** Initializes the app when it is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    /** Switches to the LoanCalculatorActivity when the associated button is clicked. 
     *  Does not send any extra data, so the loan calculator will use its defaults.
     *  You must also register the new Activity in AndroidManifest.xml. 
     */
    public void showLoanPayments1(View clickedButton) {
        Intent activityIntent = 
                new Intent(this, LoanCalculatorActivity.class);
        startActivity(activityIntent);
    }
    
    /** Switches to the LoanCalculatorActivity when the associated button is clicked. 
     *  Sends extra data, so the loan calculator will use the values sent instead of its defaults. 
     */
    public void showLoanPayments2(View clickedButton) {
        Intent activityIntent = 
                new Intent(this, LoanCalculatorActivity.class);
        activityIntent.putExtras(LoanBundler.makeRandomizedLoanInfoBundle());
        startActivity(activityIntent);
    }
    
    /** Switches to the TabbedActivity when the associated button is clicked. */
    
    public void showTabs(View clickedButton) {          // Chamado no main.xml com o método OnClick
        Intent activityIntent = new Intent(this, TabbedActivity.class);     //instancia um novo Intent passando o TabbedActivity
                                                                            //como o activity chamado e também passando o this
                                                                            //significa que estamos passando o Context do
                                                                            //do activity que estamos agora
        startActivity(activityIntent);          // Inicia o intent construido acima
    }
}