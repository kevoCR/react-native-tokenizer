
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.payment.android.controller.PaymentsSDK;
import com.payment.android.model.Token;

import static com.payment.android.model.PaymentType.PAYMENT_CARD;

public class RNTokenizerModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNTokenizerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;

    }

    @Override
    public String getName() {
        return "RNTokenizer";
    }


    @ReactMethod
    public String fetchToken(String cardNumber, String expirationYear, String expirationMonth) {

        //PaymentsSDK paymentsSDK = new PaymentsSDK(apiEndpoint, applicationId);
        PaymentsSDK paymentsSDK = new PaymentsSDK("https://api-staging.finix.io", "APiAbi6kS3pMq552BpUcfYHZ");

        try {
            Token token = paymentsSDK.tokenize("4111111111111111", PAYMENT_CARD,
                    12, 2022);
            return token.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}