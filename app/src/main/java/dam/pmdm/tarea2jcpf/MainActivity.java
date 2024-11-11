package dam.pmdm.tarea2jcpf;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import dam.pmdm.tarea2jcpf.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inflado del layout usando View Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Definir el layoutManager, en este caso un LinearLayoutManager para una lista
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Lista de items
        List<CharacterData> characters = Arrays.asList(
                new CharacterData(R.drawable.mario,"Mario","Mario es un personaje creado por el diseñador de videojuegos Shigeru Miyamoto, de la compañía Nintendo y el protagonista de la franquicia de videojuegos homónima. "),
                new CharacterData(R.drawable.peach,"Peach","La princesa Peach es un personaje de la franquicia de videojuegos de Super Mario de Nintendo. Originalmente creada por Shigeru Miyamoto, Peach es la princesa y gobernante del ficticio Reino Champiñón, donde reside en su castillo junto con Toads y que está constantemente bajo ataque del malvado Bowser."),
                new CharacterData(R.drawable.luigi,"Luigi","Luigi es un personaje ficticio japonés de videojuegos que fue creado por el diseñador de videojuegos japonés Shigeru Miyamoto, que junto a su hermano Mario, forman los personajes principales de la compañía Nintendo."),
                new CharacterData(R.drawable.toad,"Toad","Toad es un personaje ficticio de los videojuegos de la franquicia de Mario. Creado por el diseñador de videojuegos japonés, Shigeru Miyamoto, Toad es representado como un ciudadano del Reino Champiñón y es uno de los asistentes más fieles de la Princesa Peach; trabajando constantemente en su favor.")
        );

        //Asignamos el adaptador al RecyclerView
        binding.recyclerView.setAdapter(new CharacterRecyclerViewAdapter(characters, this));
    }

    public void characterClicked(CharacterData currentCharacter) {
    }
}