package dam.pmdm.tarea2jcpf;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import java.util.Locale;
import dam.pmdm.tarea2jcpf.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavController navController;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NavigationView navigationView;
        super.onCreate(savedInstanceState);

        //Obtenemos con SharedPreferences la variable Settings
        SharedPreferences preferences = getSharedPreferences("Settings", MODE_PRIVATE);

        //Con la variable settings de preferences obtenemos el Lenguage, por defecto en español (es)
        String languageCode = preferences.getString("Language", "es");

        //Aplicamos los cambios del idioma
        setLocale(this, languageCode);

        //Para las pantallas con bordes redondos
        EdgeToEdge.enable(this);

        //Configuración del layout
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Seteamos la toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Setamos nuestros menús
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        //Fragmento inicial
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // Configura el AppBarConfiguration con el ID de nuestra home
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.characterListFragment)
                .setOpenableLayout(drawer)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Configurar el switch con el idioma actual
        Menu menu = navigationView.getMenu();
        MenuItem settingsItem = menu.findItem(R.id.nav_settings);
        if (settingsItem != null) {
            View actionView = getLayoutInflater().inflate(R.layout.switch_item, null);
            @SuppressLint("UseSwitchCompatOrMaterialCode") Switch languageSwitch = actionView.findViewById(R.id.language_switch);

            // Establecer estado inicial del switch
            languageSwitch.setChecked(languageCode.equals("en"));

            //Listener para el switch
            languageSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> changeLanguage(isChecked));

            //Para mostrar el switch correctamente
            settingsItem.setActionView(actionView);
        }

        navigationView.setNavigationItemSelectedListener(this);
    }

    //Método que se usa cuando se hace click a un item de la lista de personajes para llevarlo a la vista de detalles de dicho personaje
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.characterListFragment) {  // Asegúrate de que este ID coincide con tu menú
            navController.navigate(R.id.characterListFragment);  // Usamos el ID correcto del fragmento
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
    public boolean onSupportNavigateUp() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, drawer) || super.onSupportNavigateUp();
    }

    //Método  para mostrar el menú en el header
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //Cuando se hace click a un item de la lista de personajes en el home se llama a este método
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            infoClicked();
        }
        return super.onOptionsItemSelected(item);
    }

    //Cuando se haga click en el botón de inforamación del header se llama a esta función
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

    //Metodo para cambiar el lenguaje cuando se hace click en el switch
    private void changeLanguage(boolean isEnglish) {
        String languageCode = isEnglish ? "en" : "es";

        // Guardar la preferencia
        SharedPreferences preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        preferences.edit().putString("Language", languageCode).apply();

        // Reiniciar la actividad de manera limpia
        Intent refresh = new Intent(this, MainActivity.class);
        refresh.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(refresh);
        finish();
    }

    //Método que setea el idioma al iniciar la aplicación
    public static void setLocale(Context context, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}