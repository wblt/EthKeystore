package wb.com.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.brewchain.cwv.wallet.entity.AccountEntity;
import org.brewchain.cwv.wallet.service.WalletETHService;

import wb.com.test.keystore.HexUtils;
import wb.com.test.keystore.KeyStore;
import wb.com.test.keystore.KeyStoreFile;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            WalletETHService service = new WalletETHService();
            AccountEntity entity = service.createAccount();
            byte[] prievate = HexUtils.fromHex(entity.getPrivateKey());
            KeyStoreFile light = KeyStore.createLight("123456", prievate,entity.getAddress());
            Log.i("======", light.toString());
            KeyStoreFile keyStoreFile = KeyStoreFile.parse(light.toString());
            byte[] privateykey = KeyStore.decrypt("12346",keyStoreFile);
            String key1 = HexUtils.toHex(prievate);
            String key2 = HexUtils.toHex(privateykey);
            Log.i("======", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
