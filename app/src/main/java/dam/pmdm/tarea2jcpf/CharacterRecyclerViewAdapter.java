package dam.pmdm.tarea2jcpf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dam.pmdm.tarea2jcpf.databinding.CharacterCardviewBinding;

public class CharacterRecyclerViewAdapter extends RecyclerView.Adapter<CharacterViewHolder> {
    private final List<CharacterData> chracters;
    private final Context context;

    public CharacterRecyclerViewAdapter (List<CharacterData> chracters, Context context){
        this.chracters = chracters;
        this.context = context;
    }


    //Método para crear el el ViewHolder
    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CharacterCardviewBinding binding = CharacterCardviewBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new CharacterViewHolder(binding);
    }

    //Método para enlazar datos con el ViewHolder
    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        CharacterData currentCharacter = this.chracters.get(position);
        holder.bind(currentCharacter);

        //Manejar evento click
        holder.itemView.setOnClickListener(view -> itemClicked(currentCharacter));
    }

    private void itemClicked(CharacterData currentCharacter) {
        ((MainActivity) context).characterClicked(currentCharacter);
    }

    @Override
    public int getItemCount() {
        return chracters.size();
    }
}
