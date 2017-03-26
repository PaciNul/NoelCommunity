package iut.paci.noelcommunity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by youssouf on 10/02/2017.
 */
public class District implements Serializable {

    private final int id;

    @SerializedName("name")
    private final String nom;

    private final String description;
    private final double longitude;
    private final double latitude;
    private final int idImageRessource;
    private final List<Deposite> deposites;
    private final List<Store> stores;

    public District(int id, String nom, String description, double latitude, double longitude) {
        this(id, nom, description, latitude, longitude, R.drawable.img_district1);
    }

    public District(int id, String nom, String description, double latitude, double longitude, int idImageRessource) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.idImageRessource = idImageRessource;
        this.deposites = new ArrayList();
        this.stores = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getIdImageRessource() {
        return idImageRessource;
    }

    public List<Deposite> getDeposites() {
        return deposites;
    }

    public List<Store> getStores() {
        return stores;
    }

    public String toDialog() {
        return description;
    }

    public String toDialog2()
    {
        return nom;
    }

    public String toDialog3()
    {
        return "Coordonnées GPS : " + "["+ longitude +" ; " +latitude +"]";
    }

    @Override
    public String toString() {
        return "District{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", idImageRessource=" + idImageRessource +
                '}';
    }

    public static District fromJson(String json){
        Gson gson = new GsonBuilder().setDateFormat("HH:mm:ss").create();
        District district = gson.fromJson(json, District.class);
        return district;
    }

}
