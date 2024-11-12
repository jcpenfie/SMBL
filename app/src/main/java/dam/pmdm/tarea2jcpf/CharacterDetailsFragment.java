package dam.pmdm.tarea2jcpf;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import dam.pmdm.tarea2jcpf.databinding.CharacterDetailFragmentBinding;

public class CharacterDetailsFragment extends Fragment {
    private CharacterDetailFragmentBinding binding;

    //Método para cuando se cree la vista
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstaceState){
        //Inflo el layout para este fragmento
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    //Método para cuando la vista se haya creado
    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedIntanceState){
        super.onViewCreated(view, savedIntanceState);

        //Obtengo los datos de los argumentos que inicia el fragmento
        if(getArguments() != null){
            String name = getArguments().getString("name");
            String description = getArguments().getString("description");
            String skills = getArguments().getString("skills");
            int image = getArguments().getInt("image");

            binding.name.setText(name);
            binding.description.setText(description);
            binding.image.setImageResource(image);
            binding.skills.setText(skills);

            //Mensaje cada vez que se entre para indicar al usuario que personaje se ha seleccionado
            Toast.makeText(getContext(),"Se ha seleccionado el personaje " + name, Toast.LENGTH_SHORT).show();

        }
    }

    //Método para cuando se entre en la vista
    @Override
    public void onStart() {
        super.onStart();
        //Cambio el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.detalles_del_personaje);
        }
    }
}
