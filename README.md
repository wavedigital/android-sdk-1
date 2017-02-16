# PromisePay - Android SDK

#1. Installation

## Manual

Download the latest release from GitHub, then add the PromisePay package to your relevant Android project.

## Gradle

```Java
compile 'com.github.promisepay:promisepay:0.2.1'
```
## Maven

```Java
<dependency>
  <groupId>com.github.promisepay</groupId>
  <artifactId>promisepay</artifactId>
  <version>0.2.1</version>
</dependency>
```


#2. Usage
Import the package and create PromisePay object.
```Java
import com.github.promisepay.PromisePay;

public class PromisePayTestActivity extends Activity {
  
  private PromisePay promisePay = PromisePay.getInstance();
  
  @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        // Initialize the PromisePay with the Environment and PublickKey
        promisePay.initialize("prelive", "cbd748a608eda8635e1f325d914080b4");
        
        // Create the Card
        PPCard card = new PPCard("4111111111111111", "Bobby Buyer", "12", "2020", "123");
        promisePay.createCardAccount("da59a92f130dfc51719d13947330f4a2", card, new PromisePay.OnPromiseRequestListener() {
            @Override
            public void onSuccess(JSONObject response) {
                // TODO: process with the callback
            }

            @Override
            public void onError(Exception e) {
                // TODO: process with the callback
            }
        });
        
    }
}

```
#3. Examples
##Configuration
Initialize PromisePay using as following.
```Java
PromisePay promisePay = PromisePay.getInstance();
promisePay.initialize("prelive", "cbd748a608eda8635e1f325d914080b4");
```


##Create Card Account
Create the PPCard object with the information of card like this.
```Java
PPCard card = new PPCard("4111111111111111", "Bobby Buyer", "12", "2020", "123");
```

Now calling the createCardAccount method, you can get the callback of result.
````Java
promisePay.createCardAccount("da59a92f130dfc51719d13947330f4a2", card, new PromisePay.OnPromiseRequestListener() {
    @Override
    public void onSuccess(JSONObject response) {
        // TODO: process with the callback
    }

    @Override
    public void onError(Exception e) {
        // TODO: process with the callback
    }
});
```

##Using The Card Scanner
Define an instance level variable to hold the request code
```Java
private final static Integer PROMISE_PAY_REQUEST_CODE = 10;
```

Use an intent to start the card scanner
```Java
Intent myIntent = new Intent(this, PromisePayScanActivity.class);
startActivityForResult(myIntent, PROMISE_PAY_REQUEST_CODE);
```

Override onActivityResult to catch the result
```Java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == PROMISE_PAY_REQUEST_CODE) {
        if (data != null && data.hasExtra(PromisePayScanActivity.PROMISE_PAY_SCAN_RESULT)) {

            PPCard card = data.getParcelableExtra(PromisePayScanActivity.PROMISE_PAY_SCAN_RESULT);

            promisePay.createCardAccount("da59a92f130dfc51719d13947330f4a2", card, new PromisePay.OnPromiseRequestListener() {
                @Override
                public void onSuccess(JSONObject response) {
                    Log.d("MainActivity", "Successfully created new card");
                }

                @Override
                public void onError(Exception e) {
                    Log.d("MainActivity", "Error creating new card");
                }
            });
        }
        else {
            Log.d("MainActivity","scan was cancelled");
        }
    }
}
```


#4. Author

KevinHakans, kevin.hakans.it@gmail.com

#5.License

PromisePay is available under the MIT license. See the LICENSE file for more info.

#6. Requirements

Third-party open source libraries used within PromisePay:

1. [CardIO](https://github.com/card-io/card.io-Android-SDK) - Credit card scanning
