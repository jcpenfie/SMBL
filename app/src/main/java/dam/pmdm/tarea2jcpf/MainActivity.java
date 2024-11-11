package dam.pmdm.tarea2jcpf;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import dam.pmdm.tarea2jcpf.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        //Inflado de la pantalla ActivytiMain
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Obtengo el controlador de navegacion y lo configuro
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController);

    }

    //Método para manejar el click en el personaje
    public void characterClicked(CharacterData currentCharacter, View view) {
        //Creamos un Bundle para pasar los datos al CharacterDetailFragment
        Bundle bundle = new Bundle();
        bundle.putString("name", currentCharacter.getName());
        bundle.putInt("image", currentCharacter.getImage());
        bundle.putString("description", currentCharacter.getDescription());
        //todo:skills

        //Navegar al CharacterDetailFragment
        Navigation.findNavController(view).navigate(R.id.characterDetailsFragment, bundle);
    }

    @Override
    public boolean onSupportNavigateUp(){
        //Utilizo el método navigateUp del NavController
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}