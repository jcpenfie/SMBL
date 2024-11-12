package dam.pmdm.tarea2jcpf;

import androidx.recyclerview.widget.RecyclerView;

import dam.pmdm.tarea2jcpf.databinding.CharacterCardviewBinding;

/*
* Creación del binding para los personajes
*/
public class CharacterViewHolder extends RecyclerView.ViewHolder {

    private final CharacterCardviewBinding binding;

    public CharacterViewHolder(CharacterCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind (CharacterData character){
        binding.name.setText(character.getName());
        binding.image.setImageResource(character.getImage());
        binding.executePendingBindings(); //Para aplicar los cambios de inmediato
    }
}
