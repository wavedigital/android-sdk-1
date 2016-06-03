package com.github.promisepay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Locale;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class PromisePayScanActivity extends AppCompatActivity {

    private final static Integer MY_SCAN_REQUEST_CODE = 100;

    public static final String PROMISE_PAY_SCAN_RESULT = "au.com.promisepay.scanResult";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startScan();
    }

    private void startScan() {
        Intent scanIntent = new Intent(this, CardIOActivity.class);

        // customize these values to suit your needs.
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, true); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, true); // default: false

        // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
        startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_SCAN_REQUEST_CODE) {
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {

                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

                PPCard card = new PPCard(scanResult.cardNumber,
                        scanResult.cardholderName,
                        String.format(Locale.getDefault(), "%d", scanResult.expiryMonth),
                        String.format(Locale.getDefault(), "%d", scanResult.expiryMonth),
                        scanResult.cvv);

                Intent intent = new Intent();
                intent.putExtra(PromisePayScanActivity.PROMISE_PAY_SCAN_RESULT, card);
                setResult(Activity.RESULT_OK, intent);
            }
            else {
                setResult(Activity.RESULT_CANCELED);
            }
        }

        finish();
    }

}
