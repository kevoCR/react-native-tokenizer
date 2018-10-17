
package com.reactlibrary;

import com.facebook.react.bridge.Promise;
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
    public void fetchToken(String cardNumber, int expirationYear, int expirationMonth, final Promise promise) {

        //PaymentsSDK paymentsSDK = new PaymentsSDK(apiEndpoint, applicationId);
        PaymentsSDK paymentsSDK = new PaymentsSDK("https://api-staging.finix.io", "APiAbi6kS3pMq552BpUcfYHZ");

        Token token = null;
        try {
            token = paymentsSDK.tokenize(cardNumber,PAYMENT_CARD,expirationMonth,expirationYear);
        } catch (Exception e) {
            promise.reject("TOKENIZER_ERROR", "Failed to fetch Finix Token");
        }
        if(token.getId() != null)
            promise.resolve(token.getId());
        else
         promise.reject("TOKENIZER_ERROR", "Failed to fetch Finix Token");

    }
}