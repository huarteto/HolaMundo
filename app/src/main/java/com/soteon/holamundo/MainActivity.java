package com.soteon.holamundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.google.common.util.concurrent.ListenableFuture;

public class MainActivity extends AppCompatActivity {


    private MobileServiceClient conexionServidorApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        conexionServidorApi = new MobileServiceClient(
                        "https://nombreappservice.azurewebsites.net",
                        this);

        final PeticionRegistro peticionRegistro = new PeticionRegistro();
        peticionRegistro.setMail("alvaroSATD@hotmail.com");
        peticionRegistro.setPass("HAYQUEPONERLA");

        final ListenableFuture<ResultadoRegistro> resultado =
                conexionServidorApi.invokeApi("Registro", peticionRegistro, ResultadoRegistro.class);
        Futures.addCallback(resultado, new FutureCallback< ResultadoRegistro>() {
            //@Override
            public void onFailure(Throwable exc) {
// Acciones a realizar si la llamada devuelve un error
            }
            //@Override
            public void onSuccess(ResultadoRegistro resultadoCorrecto) {
// Acciones a realizar si la llamada devuelve un ok
                Toast.makeText(getApplicationContext(), resultadoCorrecto.getResultado(),
                        Toast.LENGTH_LONG).show();
            }
        });

        setContentView(R.layout.activity_main);
    }
}
