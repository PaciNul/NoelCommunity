package iut.paci.noelcommunity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class InscriptionActivity extends Activity {
    EditText ipMdp, ipPseudo;
    String Pseudo, Mdp;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        ipPseudo = (EditText) findViewById(R.id.etPseudo);
        ipMdp = (EditText) findViewById(R.id.etPsw);

        Button seConnecter = (Button) findViewById(R.id.bCoonect);

        seConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentConnect = new Intent(InscriptionActivity.this, LoginActivity.class);
                startActivity(intentConnect);
            }
        });

    }
    public void register_register(View v){
        Pseudo = ipPseudo.getText().toString();
        Mdp = ipMdp.getText().toString();
        String type ="inscription";
        BackGround b = new BackGround();
        b.execute(type,Pseudo, Mdp);
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            String data="";
            String type = params[0];
            int tmp;
            if (type.equals("inscription")) {
                String Nom = params[1];
                String Pseudo = params[2];
                try {
                    URL url = new URL("https://noelcommunity.000webhostapp.com/register.php");
                    //String urlParams = "Nom=" + Nom + "&Pseudo=" + Pseudo;

                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    String donne = URLEncoder.encode("Nom", "UTF-8") + " = " + URLEncoder.encode(Nom, "UTF-8") + "&" +
                            URLEncoder.encode("Pseudo", "UTF-8") + " = " + URLEncoder.encode(Pseudo, "UTF-8");
                    bufferedWriter.write(donne);
                    bufferedWriter.flush();
                    bufferedWriter.close();

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
                    //return "Exception: " + e.getMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                    //return "Exception: " + e.getMessage();
                }
            }
            return "Reussie";
        }

        @Override
        protected void onPostExecute(String s) {
            if (Pseudo.isEmpty() || Mdp.isEmpty())
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(InscriptionActivity.this);
                builder.setTitle("ERREUR");
                builder.setMessage("Les champs ne sont pas renseignés")
                        .setNegativeButton("Réessayez", null)
                        .create()
                        .show();
            }
            else
            {
                Toast.makeText(InscriptionActivity.this, "Inscription réussie, vous pouvez vous connetez !", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ctx, LoginActivity.class);
                startActivity(i);
            }
        }
    }
}
