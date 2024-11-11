package dam.pmdm.tarea2jcpf;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.Objects;

import dam.pmdm.tarea2jcpf.databinding.CharacterListFragmentBinding;

public class CharacterListFragment extends Fragment {

    private CharacterListFragmentBinding binding;
    private ArrayList<CharacterData> characters;
    private CharacterRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstaceState){

        //Inflo el layout utilizando el binding
        binding = CharacterListFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedIntanceState){
        super.onViewCreated(view, savedIntanceState);

        //Inicializamos la lista de personajes
        loadCharacters(); //Carga de personajes

        //Configuramos el RecyclerView
        adapter = new CharacterRecyclerViewAdapter(characters, getActivity());
        binding.characterListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.characterListRecyclerView.setAdapter(adapter);
    }

    //Método para cargar los datos de los personajes
    private void loadCharacters(){
        characters = new ArrayList<>();

        //Relleno los datos de los personajes
        characters.add(new CharacterData(R.drawable.mario,"Mario","Mario es un personaje creado por el diseñador de videojuegos Shigeru Miyamoto, de la compañía Nintendo y el protagonista de la franquicia de videojuegos homónima. "));
        characters.add(new CharacterData(R.drawable.peach,"Peach","La princesa Peach es un personaje de la franquicia de videojuegos de Super Mario de Nintendo. Originalmente creada por Shigeru Miyamoto, Peach es la princesa y gobernante del ficticio Reino Champiñón, donde reside en su castillo junto con Toads y que está constantemente bajo ataque del malvado Bowser."));
        characters.add(new CharacterData(R.drawable.luigi,"Luigi","Luigi es un personaje ficticio japonés de videojuegos que fue creado por el diseñador de videojuegos japonés Shigeru Miyamoto, que junto a su hermano Mario, forman los personajes principales de la compañía Nintendo."));
        characters.add(new CharacterData(R.drawable.toad,"Toad","Toad es un personaje ficticio de los videojuegos de la franquicia de Mario. Creado por el diseñador de videojuegos japonés, Shigeru Miyamoto, Toad es representado como un ciudadano del Reino Champiñón y es uno de los asistentes más fieles de la Princesa Peach; trabajando constantemente en su favor."));
    }

    @Override
    public void onStart() {
        super.onStart();
        //Cambio el título del ActionBar
        if (getActivity() != null) {
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(R.string.lista_de_personajes);
        }
    }
}
