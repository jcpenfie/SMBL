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

import java.util.Objects;

import dam.pmdm.tarea2jcpf.databinding.CharacterDetailFragmentBinding;

public class CharacterDetailsFragment extends Fragment {
    private CharacterDetailFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstaceState){
        //Inflo el layout para este fragmento
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedIntanceState){
        super.onViewCreated(view, savedIntanceState);

        //Obtengo los datos de los argumentos que inicia el fragmento
        if(getArguments() != null){
            String name = getArguments().getString("name");
            String description = getArguments().getString("description");
            int image = getArguments().getInt("image");

            binding.name.setText(name);
            binding.description.setText(description);
            binding.image.setImageResource(image);
            Toast.makeText(getContext(),"Se ha seleccionado el personaje " + name, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //Cambio el título del ActionBar
        if (getActivity() != null) {
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(R.string.detalles_del_personaje);
        }
    }
}
