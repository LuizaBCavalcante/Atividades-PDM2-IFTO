package com.example.tarefa28_02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import com.example.tarefa28_02.databinding.ItemAgendaBinding;

import java.util.List;

public class AgendaAdapter extends ArrayAdapter<Agenda> {

    public AgendaAdapter(Context context, List<Agenda> objetos) {
        super(context, 0, objetos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Agenda contato = getItem(position);
        ItemAgendaBinding binding;
        if(convertView == null){
            binding = ItemAgendaBinding.inflate(
                    LayoutInflater.from(getContext()), parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else{
            binding = (ItemAgendaBinding) convertView.getTag();
        }
        if(contato != null){
            binding.setContato(contato);
        }
        return convertView;
    }
}
