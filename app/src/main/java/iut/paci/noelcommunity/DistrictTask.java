package iut.paci.noelcommunity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by youssouf on 27/02/2017.
 */
public class DistrictTask extends AsyncTask<String, Void, String> {


    private final MapActivity activity;
    private final String url;
    private ProgressDialog pDialog;

    public DistrictTask(MapActivity activity, String url){
        this.activity = activity;
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Tentation de récupération des informations...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... args) {

        InputStream stream = null;
        HttpURLConnection con = null;
        String result = "", line = "";

        try {
            URL url = new URL(args[0]);
            con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(10000); con.setConnectTimeout(10000);
            con.setRequestMethod("GET"); con.setDoInput(true);
            con.connect();
            if (con.getResponseCode() != HttpURLConnection.HTTP_OK)
                throw new IOException("HTTP error code: " + con.getResponseCode());
            stream = con.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            while ((line = buffer.readLine()) != null) {
                result += line + "\n";
            }
            pDialog.setMessage(result);
        } catch (IOException e) {
            pDialog.setMessage(e.toString());
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch(IOException ex) {
            }
            con.disconnect();
            return result;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        pDialog.dismiss();
        Log.d("MapActivity", s);

        try
        {
            District district = District.fromJson(this.get());
            for(Store store : district.getStores())
            {
                activity.drawMarker(R.drawable.sapin, store);
            }
            for(Deposite deposite : district.getDeposites())
                activity.drawMarker(R.drawable.ic_android_black_24dp, deposite);
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
    }
}
