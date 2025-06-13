package Memoria;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;

public class Memoria extends JDialog {
    int pontos = 100;
    Random numAleatorio = new Random();
    int[] aleatorio = new int[16];
    int[] posAleatorio = new int[16];

    private JToolBar toolBar = new JToolBar();
    private JButton btnNovoJogo = new JButton("Novo jogo");
    private JButton btnRestart = new JButton("Reiniciar partida");
    private JButton btnEstatisticas = new JButton("Estatísticas");
    private JButton btnSalvar = new JButton("Salvar Estatísticas");        
 
    private JPanel panel = new JPanel();
    private GridLayout layout = new GridLayout(4, 4);
    private JButton escolha[] = new JButton[16];

    private JPanel statusBar = new JPanel();
    private JLabel pontosJogador = new JLabel("Pontos: 100");

    private ImageIcon[] imagens = new ImageIcon[8];
    private ImageIcon imagemVerso = new ImageIcon(getClass().getResource("/Memoria/images/verso.png"));

    private eventosJogo listener = new eventosJogo();

    public Memoria() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 8; i++) {
            imagens[i] = new ImageIcon(getClass().getResource("/Memoria/images/p" + (i + 1) + ".png"));
        }

        btnNovoJogo.setBackground(new Color(245, 224, 255));
        btnRestart.setBackground(new Color(245, 224, 255));
        btnEstatisticas.setBackground(new Color(245, 224, 255));
        btnSalvar.setBackground(new Color(245, 224, 255));
        btnNovoJogo.setFocusPainted(false);
        btnRestart.setFocusPainted(false);
        btnEstatisticas.setFocusPainted(false);
        btnNovoJogo.setBorderPainted(false);
        btnRestart.setBorderPainted(false);
        btnEstatisticas.setBorderPainted(false);
        btnSalvar.setBorderPainted(false);
        this.setIconImage(new ImageIcon(getClass().getResource("./icon.png")).getImage()); // icone da janela

        toolBar.add(btnNovoJogo);
        toolBar.add(btnRestart);
        toolBar.add(btnEstatisticas);
        toolBar.add(btnSalvar);
        toolBar.setBackground(Color.white);
        add(toolBar, BorderLayout.NORTH);

        panel.setLayout(layout);
        for (int i = 0; i < 16; i++) {
            escolha[i] = new JButton();
            escolha[i].setBackground(Color.white);
            escolha[i].setIcon(imagemVerso);
            escolha[i].setDisabledIcon(imagemVerso);
            panel.add(escolha[i]);
            escolha[i].setVisible(true);
        }
        add(panel, BorderLayout.CENTER);

        statusBar.add(pontosJogador);
        add(statusBar, BorderLayout.SOUTH);

        for (int i = 0; i < 16; ++i) {
            escolha[i].addActionListener(listener);
        }

        btnNovoJogo.addActionListener(listener);
        btnRestart.addActionListener(listener);
        btnEstatisticas.addActionListener(listener);
        btnSalvar.addActionListener(listener);

        this.setTitle("Jogo da Memória");
        this.setResizable(false);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setModal(true);

        iniciarNovoJogo(false);  // Inicia o jogo automaticamente
    }

    private class eventosJogo implements ActionListener {
        int contAcertos, primeiroClick, segundoClick;
        int numeroClick, pos, cont, pontosAnterior, maiorPontuacao;
        int partidasJogadas = 0, numVitorias = 0;

        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == btnNovoJogo) {
                iniciarNovoJogo(false);
            }

            if (event.getSource() == btnRestart) {
                iniciarNovoJogo(true);
            }

            if (event.getSource() == btnEstatisticas) {
                estatisticasJogo(partidasJogadas, numVitorias, maiorPontuacao);
            }

            if (event.getSource() == btnSalvar) {
                salvarEstatisticas(partidasJogadas, numVitorias, maiorPontuacao);
            }

            for (int i = 0; i < 16; ++i) {
                if (event.getSource() == escolha[i]) {
                    escolha[i].setIcon(imagens[aleatorio[i]]);
                    escolha[i].setDisabledIcon(imagens[aleatorio[i]]);
                    escolha[i].setEnabled(false);
                    numeroClick++;

                    if (numeroClick == 1) {
                        primeiroClick = i;
                    }

                    if (numeroClick == 2) {
                        segundoClick = i;

                        Timer t = new Timer(800, new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (aleatorio[primeiroClick] != aleatorio[segundoClick]) {
                                    pontos -= 2;
                                    JOptionPane.showMessageDialog(Memoria.this, "Imagens incompativeis! Tente Novamente =)");
                                    escolha[primeiroClick].setIcon(imagemVerso);
                                    escolha[primeiroClick].setDisabledIcon(imagemVerso);
                                    escolha[segundoClick].setIcon(imagemVerso);
                                    escolha[segundoClick].setDisabledIcon(imagemVerso);
                                    escolha[primeiroClick].setEnabled(true);
                                    escolha[segundoClick].setEnabled(true);
                                } else {
                                    contAcertos++;
                                    pontos += 10;
                                }

                                numeroClick = 0;

                                if (contAcertos == 8) {
                                    numVitorias++;
                                    contAcertos = 0;
                                    if (pontos > pontosAnterior)
                                        maiorPontuacao = pontos;

                                    estatisticasJogo(partidasJogadas, numVitorias, maiorPontuacao);

                                    int opcao = JOptionPane.showConfirmDialog(
                                            Memoria.this,
                                            "Você venceu!\nDeseja continuar jogando?",
                                            "Fim de Jogo",
                                            JOptionPane.YES_NO_OPTION
                                    );

                                    if (opcao == JOptionPane.YES_OPTION) {
                                        iniciarNovoJogo(false);
                                    } else {
                                        salvarEstatisticas(partidasJogadas, numVitorias, maiorPontuacao);
                                        System.exit(0);
                                    }
                                }

                                if (pontos < 0)
                                    pontos = 0;

                                pontosJogador.setText("Pontos: " + pontos);
                            }
                        });
                        t.setRepeats(false);
                        t.start();
                    }
                }
            }
        }
    }

    private void iniciarNovoJogo(boolean reiniciar) {
        listener.contAcertos = 0;
        listener.partidasJogadas++;
        listener.pontosAnterior = pontos;
        pontos = 100;
        listener.numeroClick = 0;
        listener.pos = 0;
        listener.cont = 16;
        listener.primeiroClick = 0;
        listener.segundoClick = 0;

        if (!reiniciar) {
            for (int i = 0; i < 16; ++i) {
                posAleatorio[i] = i;
            }

            for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 2; ++j) {
                    int pos = numAleatorio.nextInt(listener.cont);
                    aleatorio[posAleatorio[pos]] = i;
                    for (int q = pos + 1; q < listener.cont; ++q) {
                        posAleatorio[q - 1] = posAleatorio[q];
                    }
                    listener.cont--;
                }
            }
        }

        for (int i = 0; i < 16; ++i) {
            escolha[i].setIcon(imagens[aleatorio[i]]);
            escolha[i].setDisabledIcon(imagens[aleatorio[i]]);
            escolha[i].setEnabled(false);
        }

        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 16; ++i) {
                    escolha[i].setIcon(imagemVerso);
                    escolha[i].setDisabledIcon(imagemVerso);
                    escolha[i].setEnabled(true);
                }
                ((Timer) e.getSource()).stop();
            }
        });
        timer.setRepeats(false);
        timer.start();

        pontosJogador.setText("Pontos: " + pontos);
    }

    void estatisticasJogo(int partidasJogadas, int numeroDeVitorias, int maiorPontuacaoDoJogador) {
        JOptionPane.showMessageDialog(Memoria.this,
                "Partidas jogadas: " + partidasJogadas + "\nVitórias: " + numeroDeVitorias
                        + "\nMaior Pontuação do Jogador: " + maiorPontuacaoDoJogador,
                "Status", JOptionPane.INFORMATION_MESSAGE);
    }

    private void salvarEstatisticas(int partidasJogadas, int numVitorias, int maiorPontuacao) {
        try {
            String userHome = System.getProperty("user.home");
            java.io.File dir = new java.io.File(userHome, "MemoriaJogo");
            if (!dir.exists()) {
                dir.mkdir();
            }
            java.io.File file = new java.io.File(dir, "estatisticas.txt");

            FileWriter writer = new FileWriter(file, true);
            writer.write("Partidas jogadas: " + partidasJogadas + ", Vitórias: " + numVitorias + ", Maior Pontuação: " + maiorPontuacao + "\n");
            writer.close();
            JOptionPane.showMessageDialog(this, "Estatísticas salvas em:\n" + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar estatísticas: " + e.getMessage());
        }
    }
}
