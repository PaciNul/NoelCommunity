package iut.paci.noelcommunity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;

public class DistrictActivity extends AppCompatActivity {

    private static final ArrayList<District> districts = getDistricts();
    private static final ArrayList<District> districtsAffiches = new ArrayList<District>(districts);

    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);

        ActionBar aBar = getSupportActionBar();
        aBar.setCustomView(R.layout.head_district);
        aBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
                | ActionBar.DISPLAY_SHOW_HOME);

        GridView grid = (GridView) findViewById(R.id.grid);
        adapter = new MyAdapter(this, districtsAffiches);
        grid.setAdapter(adapter);

        EditText search = (EditText) findViewById(R.id.search);
        search.addTextChangedListener(new MyTextWatcher());

        System.out.println("DISTRICT ACTIVITY");
    }

    public void search(String str_searched) {

        districtsAffiches.clear();
        if(str_searched.isEmpty()) {
            districtsAffiches.addAll(districts);
        } else {
            str_searched = str_searched.toLowerCase();
            String a;
            for (District d : districts) {
                a = d.getNom();
                if (a.toLowerCase().contains(str_searched)) {
                    districtsAffiches.add(d);
                }
            }
        }

        adapter.clear();
        adapter.addAll(districtsAffiches);
        adapter.notifyDataSetChanged();
    }

    private static ArrayList<District> getDistricts()
    {
        String Descr1 = "Le 1e arrondissement de Paris est l'un des arrondissements les plus centraux du cœur historique de Paris. Il comprend notamment l'un des plus anciens quartiers de la ville, le quartier des Halles, qui date du tout début du Moyen Âge.";
        String Descr2 = "Le 2e arrondissement de Paris est le produit de l'extension de Paris aux XV et XVI siècles. Les premières habitations urbaines datent cependant du XIV siècle. Ainsi, l'enceinte de Charles V s'étendait déjà jusqu'à la rue d'Aboukir.";
        String Descr3 = "Le 3e arrondissement de Paris est le résultat de l'extension de la ville aux XIII et XIV siècles, enceinte de Charles V. Les plus vieux immeubles datent aujourd'hui du XVIII siècle.";
        String Descr4 = "Le 4e arrondissement de Paris se situe sur la rive droite de la Seine. Il est bordé à l'ouest par le 1e arrondissement, au nord par le 3e arrondissement, à l'est par les 11e et 12e arrondissements et au sud par la Seine et le 5e arrondissement.";
        String Descr5 = "Le 5e arrondissement de Paris est le plus ancien quartier de la ville : il recouvre la plus grande partie du quartier latin, construit par les Romains sous l'Antiquité. Il est situé sur la rive gauche, au bord de la Seine.";
        String Descr6 = "Le 6e arrondissement de Paris se situe au centre de la ville, sur la rive gauche de la Seine. Aux termes de l'article R2512-1 du Code général des collectivités territoriales, il porte également le nom d'arrondissement du Luxembourg.";
        String Descr7 = "Le 7e arrondissement de Paris est le résultat de l'extension de la ville de Paris au début du XIXe siècle.";
        String Descr8 = "Le 8e arrondissement de Paris est l'un des 20 arrondissements de Paris. Situé sur la rive droite de la Seine, il est bordé à l'ouest par le 16e arrondissement, au nord par le 17e arrondissement, à l'est par les 9e et 1e arrondissements, et au sud par la Seine et le 7e arrondissement.";
        String Descr9 = "Le 9e arrondissement de Paris est un des vingt arrondissements de Paris, situé sur la rive droite de la Seine. Avec une superficie de 217,5 hectares, il compte parmi les moins étendus de Paris.";
        String Descr10 = "Le 10e arrondissement de Paris est un des vingt arrondissements de Paris, situé sur la rive droite de la Seine.";
        String Descr11 = "Le 11e arrondissement de Paris est un des vingt arrondissements de Paris. Il est situé sur la rive droite de la Seine entre les places de la Nation, de la République et de la Bastille. Son maire est François Vauglin.";
        String Descr12 = "Le 12e arrondissement de Paris est l'un des vingt arrondissements de Paris, la capitale française.";
        String Descr13 = "Le 13e arrondissement de Paris est un des vingt arrondissements de Paris. Il est situé sur la rive gauche de la Seine. Il est entouré par le 14e arrondissement à l'ouest et le 5e arrondissement au nord.";
        String Descr14 = "Le 14e arrondissement de Paris est un des vingt arrondissements de la capitale. Il est situé sur la rive gauche de la Seine, dans le sud de la ville. Il englobe des quartiers qui faisaient autrefois partie de Gentilly et de Montrouge.";
        String Descr15 = "Le 15e arrondissement de Paris est l’un des vingt arrondissements de la capitale française. Il est situé sur la rive gauche de la Seine, dans le sud-ouest de la ville, et est le résultat de l’annexion des communes de Vaugirard et de Grenelle en 1860.";
        String Descr16 = "Le 16e arrondissement de Paris, l’un des vingt arrondissements de Paris, est situé sur la rive droite de la Seine, à l’ouest de la ville.";
        String Descr17 = "Le 17e arrondissement de Paris est l'un des vingt arrondissements de Paris. Il est situé sur la rive droite de la Seine au nord-ouest de la ville.";
        String Descr18 = "Le 18e arrondissement de Paris est un des vingt arrondissements de Paris. Situé sur la rive droite de la Seine, il comprend une partie de l'ancienne commune de Montmartre et de l'ancienne commune de la Chapelle, rattachées à Paris en 1860.";
        String Descr19 = "Le 19e arrondissement de Paris se situe à l'extrémité nord-est de la ville. Il est bordé au nord par la commune d'Aubervilliers, à l'est par les communes de Pantin, des Lilas et du Pré-Saint-Gervais, au sud par le 20e arrondissement et à l'ouest par les 10e et 18e arrondissements.";
        String Descr20 = "Le 20e arrondissement est le dernier des vingt arrondissements de Paris. Situé sur la rive droite de la Seine, il est bordé au nord par le 19e  arrondissement, à l'est par les communes des Lilas, de Bagnolet, de Montreuil et de Saint-Mandé, au sud par le 12e arrondissement, à l'ouest par le 11e.";

        return new ArrayList<District>(Arrays.asList(new District[] {
                new District(1, "Louvre", Descr1, 48.8616, 2.3346, R.drawable.img_district1),
                new District(2, "Bourse", Descr2, 48.86549, 2.34262, R.drawable.img_district2),
                new District(3, "Temple", Descr3, 48.8634, 2.3627, R.drawable.img_district3),
                new District(4, "Hôtel-de-Ville", Descr4, 48.8602, 2.3542, R.drawable.img_district4),
                new District(5, "Panthéon", Descr5, 48.8451, 2.3463, R.drawable.img_district5),
                new District(6, "Luxembourg", Descr6, 48.8496, 2.3308, R.drawable.img_district6),
                new District(7, "Palais-Bourbon", Descr7, 48.8564, 2.3219, R.drawable.img_district7),
                new District(8, "Élysée", Descr8, 48.876, 2.3195, R.drawable.img_district8),
                new District(9, "Opéra", Descr9, 48.8717, 2.3408, R.drawable.img_district9),
                new District(10, "Entrepôt", Descr10, 48.8753, 2.359, R.drawable.img_district10),
                new District(11, "Popincourt", Descr11, 48.8577, 2.382, R.drawable.img_district11),
                new District(12, "Reuilly", Descr12, 48.8399, 2.3899, R.drawable.img_district12),
                new District(13, "Gobelins", Descr13, 48.8317, 2.3542, R.drawable.img_district13),
                new District(14, "Observatoire", Descr14, 48.8331, 2.3274, R.drawable.img_district14),
                new District(15, "Vaugirard", Descr15, 48.8403, 2.2955, R.drawable.img_district15),
                new District(16, "Passy", Descr16, 48.8625, 2.2773, R.drawable.img_district16),
                new District(17, "Batignolles-Monceau", Descr17, 48.8837, 2.324, R.drawable.img_district17),
                new District(18, "Buttes-Montmartre", Descr18, 48.8889, 2.3463, R.drawable.img_district18),
                new District(19, "Buttes-Chaumont", Descr19, 48.881, 2.3833, R.drawable.img_district19),
                new District(20, "Ménilmontant", Descr20, 48.8645, 2.3991, R.drawable.img_district20)
        }));
    }

    private class MyTextWatcher implements TextWatcher {
        @Override
        public void afterTextChanged(Editable editable) {
            String str_searched = editable.toString();
            search(str_searched);
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
    }
}
