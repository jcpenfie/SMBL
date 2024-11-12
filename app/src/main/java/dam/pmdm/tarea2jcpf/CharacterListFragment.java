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

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import dam.pmdm.tarea2jcpf.databinding.CharacterListFragmentBinding;

public class CharacterListFragment extends Fragment {

    private CharacterListFragmentBinding binding;
    private ArrayList<CharacterData> characters;

    //Método para cuando se cree la vista
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState){

        //Inflo el layout utilizando el binding
        binding = CharacterListFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    //Método para cuando la vista se haya creado
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedIntanceState){
        super.onViewCreated(view, savedIntanceState);

        //Inicializamos la lista de personajes
        loadCharacters(); //Carga de personajes

        //Configuramos el RecyclerView
        CharacterRecyclerViewAdapter adapter = new CharacterRecyclerViewAdapter(characters, getActivity());
        binding.characterListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.characterListRecyclerView.setAdapter(adapter);

        //Mensaje al cargar todos los personajes
        Snackbar.make(requireContext(), view, getString(R.string.wellcomming), 2000).show();
    }

    //Método para cargar los datos de los personajes
    private void loadCharacters(){
        characters = new ArrayList<>();

        //Relleno los datos de los personajes
        characters.add(new CharacterData(R.drawable.mario,getString(R.string.mario_name),getString(R.string.mario_desc), getString(R.string.mario_skills) ));
        characters.add(new CharacterData(R.drawable.peach,getString(R.string.peach_name),getString(R.string.peach_desc), getString(R.string.peach_skills)));
        characters.add(new CharacterData(R.drawable.luigi,getString(R.string.luigi_name),getString(R.string.luigi_desc), getString(R.string.luigi_skills)));
        characters.add(new CharacterData(R.drawable.toad,getString(R.string.toad_name),getString(R.string.toad_desc), getString(R.string.toad_skills)));
    }

    //Método para cuando se entre en la vista
    @Override
    public void onStart() {
        super.onStart();
        //Cambio el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.lista_de_personajes);
        }
    }
}
