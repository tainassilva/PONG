package A3Pong;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

public class KeyBoard implements KeyListener {
    private Cena cena;

    public KeyBoard(Cena cena) {
        this.cena = cena;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        System.out.println("Key pressed: " + e.getKeyCode());

        // Movimenta a barra para a esquerda quando a seta esquerda é pressionada
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            cena.translacaoBarra -= 0.1f; // Ajuste conforme necessário
        }

        // Movimenta a barra para a direita quando a seta direita é pressionada
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            cena.translacaoBarra += 0.1f; // Ajuste conforme necessário
        }

        // Verifica se a tecla ENTER foi pressionada para iniciar o jogo
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            cena.op = 1;  // Ou qualquer outra ação desejada para iniciar o jogo
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            cena.pauseJogo();
        }
        if (cena.gols == 5){
            cena.op = 2;
        }
        if (e.getKeyChar() == 'X' || e.getKeyChar() == 'x') {
            // Fecha o jogo
            closeGame();
        
    }
    if ((e.getKeyChar() == 'r' || e.getKeyChar() == 'R') && cena.op == 2) {
        cena.reiniciarJogo();
        cena.op = 1; // Mude para a fase de jogo
    }
    }
 

    @Override
    public void keyReleased(KeyEvent e) {
        // Código para lidar com a tecla liberada, se necessário
    }
    private void closeGame() {
        // Adicione aqui qualquer código adicional que você deseja executar antes de fechar o jogo
        // ...

        // Finaliza o programa
        System.exit(0);
    }

}
