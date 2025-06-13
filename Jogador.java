package Velha;

import javax.swing.*;

public class Jogador {
    private String nome;
    private ImageIcon avatar;

    public Jogador(String nome, ImageIcon avatar) {
        this.nome = nome;
        this.avatar = avatar;
    }

    public String getNome() {
        return nome;
    }
    public ImageIcon getAvatar() {
        return avatar;
    }
}
