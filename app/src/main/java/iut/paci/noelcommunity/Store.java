package iut.paci.noelcommunity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.Point;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.layer.overlay.Marker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by youssouf on 27/02/2017.
 */
public class Store extends Place {

    private int treeCount;

    public static MapActivity mapA;

    public Store(int id, String name, double longitude, double latitude, Date openingTime, Date closingTime, int treeCount) {
        super(id, name, longitude, latitude, openingTime, closingTime);
        this.treeCount = treeCount;
    }

    public int getTreeCount() {
        return treeCount;
    }

    @Override
    public void drawMarker(MapActivity activity, LatLong geoPoint) {
        activity.drawMarker(R.drawable.sapin, geoPoint);
    }

    @Override
    public Marker getMarker(Context context, Bitmap bitmap) {
        return new StoreMarker(this, context, bitmap);
    }

    public Dialog getDialog(Context context) {
        return new StoreDialog(context, this);
    }

    @Override
    public String toString() {
        return "Store{\n" +
                super.toString() +
                "\ntreeCount=" + treeCount +
                '}';
    }

    public static class StoreMarker extends Marker {

        private final Store store;
        private final Context context;

        public StoreMarker(Store store, Context context, Bitmap bitmap) {
            super(new LatLong(store.getLatitude(), store.getLongitude()), bitmap, 0, -bitmap.getHeight() /2);
            this.store = store;
            this.context = context;
        }

        @Override
        public boolean onTap(LatLong tapLatLong, Point layerXY, Point tapXY) {
            if(contains(layerXY, tapXY)) {
                Dialog d = store.getDialog(context);
                d.show();
                return true;
            }
            return false;
        }

        @Override
        public boolean onLongPress(LatLong tapLatLong, Point layerXY, Point tapXY) {
            return super.onLongPress(tapLatLong, layerXY, tapXY);
        }
    }

    public static class StoreDialog extends Dialog {

        Store store;
        public StoreDialog(Context context, Store store) {
            super(context);
            this.store = store;
            setContentView(R.layout.dialog_map_store);
            setContent(store.getName(), store.getLongitude(), store.getLatitude(), store.getTreeCount(), store.getOpeningTime(), store.getClosingTime());
        }

        public void setContent(String nom,  double longitude, double latitude, int nbrSapins, Date open, Date close) {
            this.setTitle(nom);

            TextView tv_nbrSapins = (TextView) findViewById(R.id.textView3),
                    tv_horaire = (TextView) findViewById(R.id.textView6),
                    textView7 = (TextView) findViewById(R.id.textView7),
                    textView10 = (TextView) findViewById(R.id.textView10),
                    textView11 = (TextView) findViewById(R.id.textView11);

            DateFormat dateFormat = new SimpleDateFormat("HH:mm");

            tv_nbrSapins.setText(nbrSapins + "");
            tv_nbrSapins.setTextColor(getContext().getResources().getColor(nbrSapins > 0 ? R.color.colorPrimary : R.color.colorAccent));

            textView7.setText(nom);
            textView10.setText(longitude +"");
            textView11.setText(latitude +"");
            Calendar ouv = Calendar.getInstance(), clo = Calendar.getInstance(), now = Calendar.getInstance();
            ouv.setTime(open);
            ouv.set(Calendar.YEAR, 0);
            ouv.set(Calendar.MONTH, 0);
            ouv.set(Calendar.DAY_OF_MONTH, 0);

            clo.setTime(close);
            clo.set(Calendar.YEAR, 0);
            clo.set(Calendar.MONTH, 0);
            clo.set(Calendar.DAY_OF_MONTH, 0);

            now.setTime(new Date());
            now.set(Calendar.YEAR, 0);
            now.set(Calendar.MONTH, 0);
            now.set(Calendar.DAY_OF_MONTH, 0);

            tv_horaire.setText(dateFormat.format(open) + " - " + dateFormat.format(close));
            boolean ouvert = ouv.compareTo(now) <= 0 && clo.compareTo(now) > 0;
            tv_horaire.setTextColor(getContext().getResources().getColor(ouvert ? R.color.colorPrimary : R.color.colorAccent));

            Button button = (Button) findViewById(R.id.mapdial_but);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    MapActivity mapA = Deposite.mapA;
                    LatLong ll = new LatLong(store.getLatitude(), store.getLongitude());
                    mapA.path.add(ll);
                    mapA.drawPath(mapA.path);
                }
            });
        }
    }
}
