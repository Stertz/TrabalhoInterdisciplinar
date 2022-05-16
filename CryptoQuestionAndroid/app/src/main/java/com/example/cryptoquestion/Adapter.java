package com.example.cryptoquestion;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.cryptoquestion.R;

import java.text.SimpleDateFormat;
import java.util.List;

import modelDominio.Post;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<Post> listaPost;
    private PostOnClickListener postOnClickListener;

    public Adapter(List<Post> listaPost, PostOnClickListener postOnClickListener) {
        this.listaPost = listaPost;
        this.postOnClickListener = postOnClickListener;
    }

    @Override
    public Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Adapter.MyViewHolder holder, final int position) {
        Post meuPost = listaPost.get(position);
        holder.tvNomeUsuario.setText(meuPost.getUser().getNameUser());
        holder.tvIdUsuario.setText("@" + meuPost.getUser().getIdUser());
        String tx = " • ";
        SimpleDateFormat formato = new SimpleDateFormat("hh:mm"+tx+" dd/MM/yyyy");
        String form = formato.format(meuPost.getDatePost());
        holder.tvHoraPost.setText(form);
        holder.tvTextoPost.setText(meuPost.getTextPost());




        // clique no item do cliente
        if (postOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    postOnClickListener.onClickPost(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaPost.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeUsuario, tvIdUsuario, tvHoraPost, tvTextoPost, tvCurtir;
        ImageButton ibCurtir, ibMensagem, ibDoar, ibCompartilhar;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvNomeUsuario = (TextView) itemView.findViewById(R.id.tvNomeUsuario);
            tvIdUsuario = (TextView) itemView.findViewById(R.id.tvIdUsuario);
            tvHoraPost = (TextView) itemView.findViewById(R.id.tvHoraPost);
            tvTextoPost = (TextView) itemView.findViewById(R.id.tvTextoPost);
            tvCurtir = (TextView) itemView.findViewById(R.id.tvCurtir);
            ibCurtir = (ImageButton) itemView.findViewById(R.id.ibCurtir);
            ibMensagem = (ImageButton) itemView.findViewById(R.id.ibMensagem);
            ibDoar = (ImageButton) itemView.findViewById(R.id.ibDoar);
            ibCompartilhar = (ImageButton) itemView.findViewById(R.id.ibCompartilhar);

        }
    }

    public interface PostOnClickListener {
        public void onClickPost(View view, int position);
    }

}
