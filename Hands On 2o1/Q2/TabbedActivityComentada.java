package example.a2_1_intentfiltera;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabbedActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {       // Padrão, cria no inicio da classe
        super.onCreate(savedInstanceState);                 // savedInstanceState serve para, se a activity estiver sendo
                                                            // renicializada após ter sido fechada, então o bundle conterá
                                                            // os dados mais recentemente postos em onSaveInstanceState(Bundle)

        Resources resources = getResources();           // Cria um objeto da classe Resource para que seja possível acessar métodos dentro dela, 
                                                        // como, por exemplo, o getDrawable que é usado pegar algumas imagens ao longo do resto 
                                                        // do código

        TabHost host = getTabHost();                // Cria um "Container" para as abas de janelas na parte superior do programa, utilizando
                                                    // o método getTabHost() do TabActivity para poder pegar o rótulo das abas

        Intent intent1 = new Intent(this, LoanCalculatorActivity.class);           // Cria um Intent declarado para poder chamar
                                                                                   // a LoanCalculatorActivity
        Bundle loanBundle1
                = LoanBundler.makeLoanInfoBundle(100000, 7.5, 120);                /* Cria um Bundle que chama um dos métodos da classe
                                                                                      LoanBundler, criada pelo professor aqui nesse projeto,
                                                                                      que, em linhas gerais, retorna um bundle com esses valores
                                                                                      postos já em seu interior com métodos padrões já do
                                                                                      própria classe Bundle (como putDouble, putLong, etc) com
                                                                                      suas respectivas chaves sendo incluídas no interior desse
                                                                                      método
                                                                                   */

        intent1.putExtras(loanBundle1);             //Serve para mandar dados para outra activity inserindo um bundle dentro da intent

        Drawable tabIcon = resources.getDrawable(R.drawable.calculator);            // Desenha a imagem de uma calculadora que, por algum motivo,
                                                                                    // não é exibida no programa final

        // A partir daqui é criado as especificações de cada aba superior, sendo a primeira abaixo a que possuí o texto "10 Year" em seu
        // "botão"
        TabSpec tab1Spec = host.newTabSpec("Tab One")               // Uma tab (aba) possuí um indicador de tab, seu conteúdo e uma etiqueta
                               .setIndicator("10 Year", tabIcon)    // ou tag que é usada para rastreá-la. O builder tabspec ajuda a escolher
                               .setContent(intent1);                // entre essas opções. Como é possível visualizar em seu uso o nos sets de content
                                                                    // Indicator e newTabSpec

        host.addTab(tab1Spec);                                          // Esse comando serve para adicionar a tab (aba superior "10 Year")

        // A partir daqui o código é relativo ao tab 2 que representa o "20 Year":
        Uri uriTwentyYear = Uri.parse("loan://coreservlets.com/calc");      // Cria um objeto da classe URI com o uri relativo ao
                                                                            // LoanCalculatorActivity
        Intent intent2 = new Intent(Intent.ACTION_VIEW, uriTwentyYear);     // Cria um intent que no construtor recebe uma ação de activity
                                                                            // para mostrar os dados para o usuário acerca do segundo parâmetro
                                                                            // o qual é um intent para o Activity LoanCalculatorActivity

        Bundle loanBundle2 =                                                // Cria um bundle tal qual no tab 1
            LoanBundler.makeLoanInfoBundle(100000, 7.5, 240);
        intent2.putExtras(loanBundle2);                                     // Manda o bundle para o intent igual ao tab 1
        tabIcon = resources.getDrawable(R.drawable.calculator);

        TabSpec tab2Spec = host.newTabSpec("Tab Two")                       // Cria as especificações do tab 2
                               .setIndicator("20 Year", tabIcon)
                               .setContent(intent2);
        host.addTab(tab2Spec);                                              // Adiciona a tab 2

        // A partir daqui é relativo ao tab 3 que representa o "30 Year":
        String baseAddress = "loan://coreservlets.com/calc";                // Cria uma String com o URI do LoanCalculatorActivity
        String address =
                String.format("%s?%s&%s&%s",                                // Cria um String formatada através de um método da classe String
                              baseAddress,                                  // recebendo todos os dados acerca do que deve ser transmitido ao
                              "loanAmount=100000",                          // ao activity LoanCalculatorActivitym (como, por exemplo, o loan
                              "annualInterestRateInPercent=7.5",            // Amount que valerá 100000), além de receber o endereço do próprio
                              "loanPeriodInMonths=360");                    // URI do LoanCalculatorActiviy através do baseAddress

        Uri uriThirtyYear = Uri.parse(address);                             // Passa o endereço dentro de um URI através do método parse que
                                                                            // recebe um string no formato URI codificado
        Intent intent3 = new Intent(Intent.ACTION_VIEW, uriThirtyYear);     // Cria uma intent que possuí como um dos parâmetros uma ação
                                                                            // para exibir o activity no endereço URI do segundo parâmetro
        tabIcon = resources.getDrawable(R.drawable.calculator);             // Invoca uma imagem com o resources para colocá-la na tab

        TabSpec tab3Spec = host.newTabSpec("Tab Three")                     // Define as informações acerca da tab3 "30 Year"
                               .setIndicator("30 Year", tabIcon)
                               .setContent(intent3);
        host.addTab(tab3Spec);                                              // Faz a tab ser exibida
    }
}
