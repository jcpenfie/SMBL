package dam.pmdm.tarea2jcpf;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        bundle.putString("skills", currentCharacter.getSkills());

        //Navegar al CharacterDetailFragment
        Navigation.findNavController(view).navigate(R.id.characterDetailsFragment, bundle);
    }

    //Para que funcione el botón de "ir hacia atrás" en el toolbar
    @Override
    public boolean onSupportNavigateUp(){
        //Utilizo el método navigateUp del NavController
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            infoClicked();
        }
        return super.onOptionsItemSelected(item);
    }

    private void infoClicked() {
        // 1. Instanciamos el AlertDialog con su constructor.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Añdimos lo que va a mostrar.
        builder.setMessage(R.string.dialog_message).setTitle(R.string.acercade);

        // 3. Creamos el AlertDialog.
        AlertDialog dialog = builder.create();

        //4. Lo mostramos
        dialog.show();
    }
}