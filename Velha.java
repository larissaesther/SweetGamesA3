package Velha;

import javax.swing.*;
import java.awt.*;

public class Velha extends JDialog {
    Color background; // imagem de fundo
    JTabbedPane painel; // agrupador de abas
    JPanel[] aba; // abas
    JLabel[] tituloAba; // titulo de cada pagina de cada aba
    JTextField[] inputNome; // catando nome dos jogadores
    ButtonGroup[] bgImagens; // grupo de botoes das imagens
    JRadioButton[] rbImgJ1; // botoes das imagens do jogador 1
    JRadioButton[] rbImgJ2; // botoes das imagens do jogador 2
    ImageIcon[][] opcoesAvatar; // todas as opcoes de avatar
    JLabel[] moldura; // moldura para exibir as imagens dos jogadores
    JLabel[] opcoesAvatarJ1; // opcoes do jogador 1
    JLabel[] opcoesAvatarJ2; // opcoes do jogador 2
    JButton[] botaoContinuar; // botao de continuar para ir para a proxima aba
    String[][] imagensAvatar = {{"images/p1.png", "images/p2.png", "images/p3.png", "images/p4.png"}, {"images/p5.png", "images/p6.png", "images/p7.png", "images/p8.png"}}; // caminho das imagens
    String nome; // nome para criar novo jogador
    ImageIcon avatar; // avatar para criar novo jogador
    Jogador jogador1, jogador2; // jogadores
    String jogadorAtual; // para controlar as jogadas
    JLabel nomeJ1, nomeJ2; // para exibir os nomes
    JButton[] pos; // posicoes do tabuleiro
    int jogada; // jogada atual
    String simbAtual; // simbolo atual ('X' ou 'O')
    
    public Velha() {
        // configs
        inicializarComponentes();
        this.setTitle("Jogo da velha");
        this.setLayout(null);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(new ImageIcon(getClass().getResource("./images/icon.png")).getImage());
        this.setModal(true);
        this.getContentPane().setBackground(Color.WHITE);        
        
        // painel para as abas
        painel = new JTabbedPane();
        painel.setBounds(0, 0, 400, 400);
        painel.setBackground(background);
        
        // inicializando abas
        for (int i = 0; i < aba.length; i++) {
            aba[i] = new JPanel();
        }

        // aba do jogador 1
        aba[0].setLayout(null);

        tituloAba[0] = new JLabel("Nome do jogador 1:");
        tituloAba[0].setBounds(130, 50, 120, 20);

        inputNome[0] = new JTextField();
        inputNome[0].setBounds(130, 70, 120, 20);
        
        for (int i = 0; i < rbImgJ1.length; i++) { rbImgJ1[i] = new JRadioButton(); }
        rbImgJ1[0].setBounds(45, 170, 20, 10);
        rbImgJ1[1].setBounds(135, 170, 20, 10);
        rbImgJ1[2].setBounds(225, 170, 20, 10);
        rbImgJ1[3].setBounds(315, 170, 20, 10);
        bgImagens[0] = new ButtonGroup();
        for (JRadioButton radioButton : rbImgJ1) {
            bgImagens[0].add(radioButton);
            aba[0].add(radioButton);
        }

        // mostrando todas as opcoes de avatar para o jogador 1
        opcoesAvatar[0][0] = new ImageIcon(getClass().getResource("./images/p1.png"));
        opcoesAvatarJ1[0] = new JLabel("", opcoesAvatar[0][0], SwingConstants.CENTER);
        opcoesAvatarJ1[0].setBounds(30, 110, 50, 50);
        opcoesAvatar[0][1] = new ImageIcon(getClass().getResource("./images/p2.png"));
        opcoesAvatarJ1[1] = new JLabel("", opcoesAvatar[0][1], SwingConstants.CENTER);
        opcoesAvatarJ1[1].setBounds(120, 110, 50, 50);
        opcoesAvatar[0][2] = new ImageIcon(getClass().getResource("./images/p3.png"));
        opcoesAvatarJ1[2] = new JLabel("", opcoesAvatar[0][2], SwingConstants.CENTER);
        opcoesAvatarJ1[2].setBounds(210, 110, 50, 50);
        opcoesAvatar[0][3] = new ImageIcon(getClass().getResource("./images/p4.png"));
        opcoesAvatarJ1[3] = new JLabel("", opcoesAvatar[0][3], SwingConstants.CENTER);
        opcoesAvatarJ1[3].setBounds(300, 110, 50, 50);

        botaoContinuar[0] = new JButton("Continuar");
        botaoContinuar[0].setBounds(140, 200, 100, 20);
        
        // adicionando componentes da aba do jogador 1
        aba[0].add(tituloAba[0]);
        aba[0].add(inputNome[0]);
        for (int i = 0; i < opcoesAvatarJ1.length; i++) { aba[0].add(opcoesAvatarJ1[i]); }
        aba[0].add(botaoContinuar[0]);
        
        botaoContinuar[0].addActionListener(e -> {
            // acessando nome e imagem escolhidos
            nome = inputNome[0].getText();
            for (int i = 0; i < rbImgJ1.length; i++) {
                if (rbImgJ1[i].isSelected()) {
                    avatar = new ImageIcon(getClass().getResource(imagensAvatar[0][i]));
                }
            }
            // criando jogador
            jogador1 = new Jogador(nome, avatar);
            // indo para a proxima aba
            painel.setSelectedIndex(1);
        });

        // aba do jogador 2
        aba[1].setLayout(null);

        tituloAba[1] = new JLabel("Nome do jogador 2:");
        tituloAba[1].setBounds(130, 50, 120, 20);

        inputNome[1] = new JTextField();
        inputNome[1].setBounds(130, 70, 120, 20);

        for (int i = 0; i < rbImgJ1.length; i++) { rbImgJ2[i] = new JRadioButton(); }
        rbImgJ2[0].setBounds(45, 170, 20, 10);
        rbImgJ2[1].setBounds(135, 170, 20, 10);
        rbImgJ2[2].setBounds(225, 170, 20, 10);
        rbImgJ2[3].setBounds(315, 170, 20, 10);
        bgImagens[1] = new ButtonGroup();
        for (JRadioButton radioButton : rbImgJ2) {
            bgImagens[1].add(radioButton);
            aba[1].add(radioButton);
        }

        // mostrando todas as opcoes de avatar para o jogador 2
        opcoesAvatar[1][0] = new ImageIcon(getClass().getResource("./images/p5.png"));
        opcoesAvatarJ2[0] = new JLabel("", opcoesAvatar[1][0], SwingConstants.CENTER);
        opcoesAvatarJ2[0].setBounds(30, 110, 50, 50);
        opcoesAvatar[1][1] = new ImageIcon(getClass().getResource("./images/p6.png"));
        opcoesAvatarJ2[1] = new JLabel("", opcoesAvatar[1][1], SwingConstants.CENTER);
        opcoesAvatarJ2[1].setBounds(120, 110, 50, 50);
        opcoesAvatar[1][2] = new ImageIcon(getClass().getResource("./images/p7.png"));
        opcoesAvatarJ2[2] = new JLabel("", opcoesAvatar[1][2], SwingConstants.CENTER);
        opcoesAvatarJ2[2].setBounds(210, 110, 50, 50);
        opcoesAvatar[1][3] = new ImageIcon(getClass().getResource("./images/p8.png"));
        opcoesAvatarJ2[3] = new JLabel("", opcoesAvatar[1][3], SwingConstants.CENTER);
        opcoesAvatarJ2[3].setBounds(300, 110, 50, 50);

        botaoContinuar[1] = new JButton("Continuar");
        botaoContinuar[1].setBounds(140, 200, 100, 20);

        // adicionando componentes da aba do jogador 2
        aba[1].add(tituloAba[1]);
        aba[1].add(inputNome[1]);
        for (int i = 0; i < opcoesAvatarJ2.length; i++) { aba[1].add(opcoesAvatarJ2[i]); }
        aba[1].add(botaoContinuar[1]);

        botaoContinuar[1].addActionListener(e -> {
            // acessando nome e imagem escolhidos
            nome = inputNome[1].getText();
            for (int i = 0; i < rbImgJ1.length; i++) {
                if (rbImgJ2[i].isSelected()) {
                    avatar = new ImageIcon(getClass().getResource(imagensAvatar[1][i]));
                }
            }
            // criando jogador
            jogador2 = new Jogador(nome, avatar);
            // iniciando o jogo
            iniciarJogo();
            // indo para a proxima aba
            painel.setSelectedIndex(2);
            painel.setEnabledAt(0, false);
            painel.setEnabledAt(1, false);
        });
        // adicionando abas ao painel
        painel.addTab("Jogador 1", aba[0]);
        painel.addTab("Jogador 2", aba[1]);
        // plano de fundo
        for (int i = 0; i < aba.length - 1; i++) {
            ImageIcon img = new ImageIcon(getClass().getResource("./images/background.png"));
            JLabel backgroundAba = new JLabel("", img, SwingConstants.CENTER);
            backgroundAba.setBounds(0, 0, 400, 400);
            aba[i].add(backgroundAba);
        }
        // adicionando painel
        add(painel);
    }

    private void inicializarComponentes() {
        background = new Color(245, 224, 255);
        aba = new JPanel[3];
        tituloAba = new JLabel[3];
        inputNome = new JTextField[2];
        bgImagens = new ButtonGroup[2];
        rbImgJ1 = new JRadioButton[4];
        rbImgJ2 = new JRadioButton[4];
        opcoesAvatar = new ImageIcon[2][4];
        moldura = new JLabel[2];
        opcoesAvatarJ1 = new JLabel[4];
        opcoesAvatarJ2 = new JLabel[4];
        botaoContinuar = new JButton[2];
        pos = new JButton[9];
        jogada = 0;
    }

    private void iniciarJogo() {
        // jogo
        aba[2].setLayout(null);

        tituloAba[2] = new JLabel("O jogo foi iniciado");
        tituloAba[2].setBounds(135, 30, 110, 20);

        // infos do jogador 1
        moldura[0] = new JLabel("", jogador1.getAvatar(), SwingConstants.CENTER);
        moldura[0].setBounds(30, 90, 50, 50);
        nomeJ1 = new JLabel(jogador1.getNome(), SwingConstants.CENTER);
        nomeJ1.setBounds(10, 150, 90, 50);
        nomeJ1.setBackground(Color.WHITE);

        // infos do jogador 2
        moldura[1] = new JLabel("", jogador2.getAvatar(), SwingConstants.CENTER);
        moldura[1].setBounds(300, 90, 50, 50);
        nomeJ2 = new JLabel(jogador2.getNome(), SwingConstants.CENTER);
        nomeJ2.setBounds(280, 150, 90, 50);
        nomeJ2.setBackground(Color.WHITE);

        // tabuleiro do jogo
        for (int i = 0; i < pos.length; i++) {
            pos[i] = new JButton(" ");
            pos[i].setBackground(Color.white);
            pos[i].setFocusPainted(false);
        }
        pos[0].setBounds(115, 70, 50, 50);
        pos[1].setBounds(165, 70, 50, 50);
        pos[2].setBounds(215, 70, 50, 50);
        pos[3].setBounds(115, 120, 50, 50);
        pos[4].setBounds(165, 120, 50, 50);
        pos[5].setBounds(215, 120, 50, 50);
        pos[6].setBounds(115, 170, 50, 50);
        pos[7].setBounds(165, 170, 50, 50);
        pos[8].setBounds(215, 170, 50, 50);

        // adicionando componentes do jogo
        aba[2].add(tituloAba[2]);
        aba[2].add(moldura[0]);
        aba[2].add(moldura[1]);
        aba[2].add(nomeJ1);
        aba[2].add(nomeJ2);
        for (int i = 0; i < pos.length; i++) { aba[2].add(pos[i]); }

        // alternando vezes dos jogadores
        jogar();

        // plano de fundo
        ImageIcon img = new ImageIcon(getClass().getResource("./images/background.png"));
        JLabel backgroundAba = new JLabel("", img, SwingConstants.CENTER);
        backgroundAba.setBounds(0, 0, 400, 400);
        aba[2].add(backgroundAba);

        painel.addTab("Partida", aba[2]);
        add(painel);
    }

    private void jogar() {
        for (int i = 0; i < pos.length; i++) {
            int j = i;
            pos[i].addActionListener(e -> {
                if (jogada % 2 == 0) {
                    pos[j].setText("X");
                    pos[j].setEnabled(false);
                    simbAtual = "X";
                    jogadorAtual = jogador1.getNome();
                } else {
                    pos[j].setText("O");
                    pos[j].setEnabled(false);
                    simbAtual = "O";
                    jogadorAtual = jogador2.getNome();
                }
                // verificando se houve alguma vitoria ou empate
                verificarJogadas();
                // indo para a proxima jogada
                jogada++;
            });
        }
    }

    private void verificarJogadas() {
        if ( (pos[0].getText().equals(simbAtual) && pos[1].getText().equals(simbAtual) && pos[2].getText().equals(simbAtual)) ||
             (pos[3].getText().equals(simbAtual) && pos[4].getText().equals(simbAtual) && pos[5].getText().equals(simbAtual)) ||
             (pos[6].getText().equals(simbAtual) && pos[7].getText().equals(simbAtual) && pos[8].getText().equals(simbAtual)) ||
             (pos[0].getText().equals(simbAtual) && pos[3].getText().equals(simbAtual) && pos[6].getText().equals(simbAtual)) ||
             (pos[1].getText().equals(simbAtual) && pos[4].getText().equals(simbAtual) && pos[7].getText().equals(simbAtual)) ||
             (pos[2].getText().equals(simbAtual) && pos[5].getText().equals(simbAtual) && pos[8].getText().equals(simbAtual)) ||
             (pos[0].getText().equals(simbAtual) && pos[4].getText().equals(simbAtual) && pos[8].getText().equals(simbAtual)) ||
             (pos[2].getText().equals(simbAtual) && pos[4].getText().equals(simbAtual) && pos[6].getText().equals(simbAtual)) ) {

            JOptionPane.showMessageDialog(null, jogadorAtual + " ganhou!");
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja jogar novamente?", "Continuar?", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                reiniciarJogo();
            } else {
                System.exit(0);
            }

        } else if (jogada == 8) {
            JOptionPane.showMessageDialog(null, "Empate!");
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja jogar novamente?", "Continuar?", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                reiniciarJogo();
            } else {
                System.exit(0);
            }
        }
    }

    private void reiniciarJogo() {
        for (int i = 0; i < pos.length; i++) {
            pos[i].setText("");
            pos[i].setEnabled(true);
        }
        jogada = 0;
        jogadorAtual = jogador1.getNome();
        simbAtual = "X";
    }


}

