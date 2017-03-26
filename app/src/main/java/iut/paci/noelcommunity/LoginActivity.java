package iut.paci.noelcommunity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    EditText ipMdp, ipPseudo;
    String Pseudo, Mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ipPseudo = (EditText) findViewById(R.id.ipPseudo);
        ipMdp = (EditText) findViewById(R.id.ipMdp);

        final Button inscriptionIci = (Button) findViewById(R.id.bInscrire);
        inscriptionIci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister = new Intent(LoginActivity.this, InscriptionActivity.class);
                startActivity(intentRegister);
            }
        });
    }

    public void main_login(View v) {
        Pseudo = ipPseudo.getText().toString();
        Mdp = ipMdp.getText().toString();
        BackGround b = new BackGround();
        b.execute(Pseudo, Mdp);
    }

    class BackGround extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String Nom = params[0];
            String Pseudo = params[1];
            String data = "";
            int tmp;

            try {
                URL url = new URL("https://noelcommunity.000webhostapp.com/login.php");
                String urlParams = "Nom=" + Nom + "&Pseudo=" + Pseudo;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }

                is.close();
                httpURLConnection.disconnect();

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {

            if (Pseudo.isEmpty() || Mdp.isEmpty())
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("ERREUR");
                builder.setMessage("Veuillez remplir tout les champs")
                        .setNegativeButton("Réessayez", null)
                        .create()
                        .show();
            }
            else
            {
                Toast.makeText(LoginActivity.this, "Vous etes connecté", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(LoginActivity.this, DistrictActivity.class);
                startActivity(i);
            }
        }
    }



}
