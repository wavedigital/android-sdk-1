# PromisePay - Android SDK

#1. Installation

## Manual

Download the latest release from GitHub, then add the PromisePay package to your relevant Android project.

## Gradle

```Java
compile 'com.github.promisepay:promisepay:0.1.1'
```
## Maven

```Java
<dependency>
  <groupId>com.github.promisepay</groupId>
  <artifactId>promisepay</artifactId>
  <version>0.1.1</version>
</dependency>
```


#2. Usage
Import the package and create PromisePay object.
```Java
import com.github.promisepay.PromisePay;

public class PromisePayTestActivity extends Activity {
  
  @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        // Initialize the PromisePay with the Environment and PublickKey
        PromisePay promisePay = PromisePay.getInstance();
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



#4. Author

KevinHakans, kevin.hakans.it@gmail.com

#5.License

PromisePay is available under the MIT license. See the LICENSE file for more info.
