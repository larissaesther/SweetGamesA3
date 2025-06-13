package Menu;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import Memoria.Memoria;
import Velha.Velha;

public class Menu extends JFrame {

    Color background = new Color(245, 224, 255);
    public Menu() {
    	this.setExtendedState(JFrame.MAXIMIZED_BOTH); // deixa o tamanho em tela cheia
        this.getContentPane().setBackground(background); // cor de fundo
        this.setTitle("Projetinhos"); // titulo da janela
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE); // se apertar no x fecha a janela
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("./icon.png")).getImage()); // icone da janela

        JMenu menuVelha = new JMenu("Jogo da Velha");
        JMenu menuMemoria = new JMenu("Jogo da Memoria");
        JMenu menuSair = new JMenu("Sair");

        JMenuItem itemVelha = new JMenuItem("Jogo da Velha");
        JMenuItem itemMemoria = new JMenuItem("Jogo da Memoria");
        JMenuItem itemSair = new JMenuItem("Sair");

        menuVelha.add(itemVelha);
        menuMemoria.add(itemMemoria);
        menuSair.add(itemSair);

        JMenuBar bar = new JMenuBar();
        bar.setBackground(background);
        setJMenuBar(bar);
        bar.add(menuVelha);
        bar.add(menuMemoria);
        bar.add(menuSair);


        itemVelha.addActionListener(e -> {
            Velha velha = new Velha();
            velha.setVisible(true);
        });
        
        itemMemoria.addActionListener(e -> {
        	Memoria memoria = new Memoria();
            memoria.setVisible(true);
        });

        itemSair.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public static void main(String[] args) {
        new Menu();
    }
}
