package A3Pong;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.Color;
import java.awt.Font;

public class Cena implements GLEventListener {
    private float xMin, xMax, yMin, yMax, zMin, zMax;
    private GLU glu;
    public float translacaoBarra = 0;
    private float angulo = 0;
    private float bolaInicialX = 0;
    private float bolaInicialY = 0;
    private float velocidadeBolaX = 0.09f;
    private float velocidadeBolaY = 0.09f;
    private float raioBola = 0.1f;
    private TextRenderer textRenderer;
    private TextRenderer textRenderer1;
    public int gols = 0;
    public int pontuacao = -20;
    private float anguloBracos = 0;
    private float alturaBraços = 0;
    private boolean braçosSubindo = true;
    public boolean jogoPausado = false;
    public int op;
    private boolean barraOponenteMovendo = false;
    private float translacaoBarraOponente = 0.0f;
    private float velocidadeBarraOponente = 0.02f; // Ajuste conforme necessário


    @Override
    public void init(GLAutoDrawable drawable) {
        glu = new GLU();
        xMin = yMin = zMin = -1;
        xMax = yMax = zMax = 1;
        // Inicializa a posição inicial da bola
        bolaInicialX = 0;
        bolaInicialY = -0.8f;
        
        // Inicialização do TextRenderer
        textRenderer = new TextRenderer(new Font("Comic Sans MS", Font.PLAIN, 20));
        textRenderer1 = new TextRenderer(new Font("Comic Sans MS", Font.PLAIN, 30));

    }
    
    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.8f, 0.8f, 0.8f, 1.0f);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();
        
         desenhoRetanguloCampoVerdeClaro(gl,0.0f);
         desenhoRetanguloCampoVerdeEscuro(gl,0.2f);
         desenhoRetanguloCampoVerdeClaro(gl,0.4f);
         desenhoRetanguloCampoVerdeEscuro(gl,0.6f);
         desenhoRetanguloCampoVerdeClaro(gl,0.8f);
         desenhoRetanguloCampoVerdeEscuro(gl,1.0f);
         desenhoRetanguloCampoVerdeClaro(gl,1.2f);
         desenhoRetanguloCampoVerdeEscuro(gl,1.4f);
         desenhoRetanguloCampoVerdeClaro(gl,1.6f);
         desenhoRetanguloCampoVerdeEscuro(gl,1.8f);
               
        // Exemplo de texto para a tela inicial
        desenhaTextoGrande(gl, 500, 600, Color.YELLOW, "PONG FOOTBALL!");
        desenhaTexto(gl, 400, 525, Color.WHITE, "Use a tecla <- para mover à esquerda");
        desenhaTexto(gl, 400, 500, Color.WHITE, "Use a tecla -> para mover à direita");
        desenhaTexto(gl, 400, 475, Color.WHITE, "Pressione a tecla 'ESPAÇO' para pausar o jogo");
        desenhaTexto(gl, 400, 450, Color.WHITE, "Após atingir 200 pontos, vai para segunda fase.");
        desenhaTexto(gl, 400, 425, Color.WHITE, "Se levar 5 gols, o oponente vence.");
        desenhaTexto(gl, 400, 400, Color.WHITE, "Pressione a tecla 'R' para reiniciar o jogo");
        desenhaTexto(gl, 400, 375, Color.WHITE, "Use a tecla 'X' para encerrar o jogo.");
        
        desenhaTexto(gl, 400, 330, Color.WHITE, "Pressione a tecla 'ENTER' para Iniciar.");
       
        gl.glPushMatrix();
        gl.glTranslatef(0.5f, 0.3f, 0);
        gl.glColor3f(0.647f, 0.165f, 0.165f);
        bola(gl, raioBola, 20);
        gl.glPopMatrix();
        
        if (jogoPausado) {
        // Exibir tela de pausa
         desenhoRetanguloCampoVerdeClaro(gl,0.0f);
         desenhoRetanguloCampoVerdeEscuro(gl,0.2f);
         desenhoRetanguloCampoVerdeClaro(gl,0.4f);
         desenhoRetanguloCampoVerdeEscuro(gl,0.6f);
         desenhoRetanguloCampoVerdeClaro(gl,0.8f);
         desenhoRetanguloCampoVerdeEscuro(gl,1.0f);
         desenhoRetanguloCampoVerdeClaro(gl,1.2f);
         desenhoRetanguloCampoVerdeEscuro(gl,1.4f);
         desenhoRetanguloCampoVerdeClaro(gl,1.6f);
         desenhoRetanguloCampoVerdeEscuro(gl,1.8f);
         
         desenhaTextoGrande(gl, 600, 500, Color.YELLOW, "JOGO PAUSADO");
         desenhaTexto(gl, 550, 400, Color.WHITE, "Pressione a tecla 'ESPAÇO' para continuar");
    } else {

        switch(op){            
           case 1 -> {
               desenhoRetanguloLateral(gl, -2.0f, 1.0f,0.7f, 0.7f, 0.7f);
               desenhoRetanguloLateral(gl, 1.0f, 1.0f, 0.7f, 0.7f, 0.7f);
               // Exemplo de como chamar o método para desenhar o texto
               desenhaTexto(gl, 100, 860, Color.BLACK, "Gols sofridos : " +gols );
               desenhaTexto(gl, 1100, 860, Color.BLACK, "Pontuação: " + pontuacao);
               
               desenhaBoneco(gl, -1.5f, -0.5f);
               desenhaBoneco(gl, 1.4f, -0.5f);
               desenhaBoneco(gl, -1.4f, -0.0f);
               desenhaBoneco(gl, 1.6f, -0.5f);
               desenhaBoneco(gl, -1.7f, -0.7f);
               desenhaBoneco(gl, 1.2f, 0.5f);
               
               
               //desenhando o retangulo verde claro para campo
               desenhoRetanguloCampoVerdeClaro(gl,0.0f);
               desenhoRetanguloCampoVerdeEscuro(gl,0.2f);
               desenhoRetanguloCampoVerdeClaro(gl,0.4f);
               desenhoRetanguloCampoVerdeEscuro(gl,0.6f);
               desenhoRetanguloCampoVerdeClaro(gl,0.8f);
               desenhoRetanguloCampoVerdeEscuro(gl,1.0f);
               desenhoRetanguloCampoVerdeClaro(gl,1.2f);
               desenhoRetanguloCampoVerdeEscuro(gl,1.4f);
               desenhoRetanguloCampoVerdeClaro(gl,1.6f);
               desenhoRetanguloCampoVerdeEscuro(gl,1.8f);
               
               gl.glPushMatrix();
               // Ajusta a translacao da barra dentro dos limites laterais
               translacaoBarra = Math.max(xMin + 0.2f , Math.min(xMax - 0.2f, translacaoBarra));
               
               gl.glTranslatef(translacaoBarra, 0.1f, 0);
               gl.glColor3f(1, 1, 0);
               
               // Desenha a barra
               gl.glBegin(GL2.GL_QUADS);
               gl.glVertex2f(-0.2f , -0.8f);
               gl.glVertex2f(-0.2f, -0.9f);
               gl.glVertex2f(0.2f, -0.9f);
               gl.glVertex2f(0.2f, -0.8f);
               gl.glEnd();
               
               gl.glPopMatrix();
               
               // Move a bola
               bolaInicialX += velocidadeBolaX;
               bolaInicialY += velocidadeBolaY;
               
               // Detecta colisão com as laterais e faz a bola quicar
               if (bolaInicialX + raioBola > xMax || bolaInicialX - raioBola< xMin) {
                   velocidadeBolaX = -velocidadeBolaX;
               }
               if (bolaInicialY + raioBola > yMax || bolaInicialY - raioBola < yMin) {
                   velocidadeBolaY = -velocidadeBolaY;
               }
               // Detecta colisão com a barra
               if (bolaInicialY - raioBola < -0.8f && bolaInicialX > translacaoBarra - 0.3f && bolaInicialX < translacaoBarra + 0.3f) {
                   // Calcula o ponto de impacto na barra
                   float pontoImpacto = (bolaInicialX - translacaoBarra) / 0.3f;
                   
                   // Verifica se o ponto de impacto está dentro do SRU
                   if (pontoImpacto >= -1 && pontoImpacto <= 1) {
                       // Define o ângulo de rebatido no final do SRU baseado no ponto de impacto
                       angulo = pontoImpacto * 45; // Ele bate no fim do SRU e vai 45 graus pra uma determinada direção
                       
                       // Ajuste para evitar que a bola entre na barra
                       
                       // Atualiza a direção da bola com base no novo ângulo
                       velocidadeBolaX = (float) (Math.cos(Math.toRadians(angulo)) * 0.02f);
                       velocidadeBolaY = (float) (Math.sin(Math.toRadians(angulo)) * 0.02f);
                       
                      // Incrementa a pontuação
                      pontuacao += 10;
                   }
               }
               // Detecta colisão com a parte inferior da janela
               if (bolaInicialY - raioBola < yMin) {
                   velocidadeBolaY = -velocidadeBolaY;
               }
               // Desenha a bola
               gl.glPushMatrix();
               gl.glTranslatef(bolaInicialX, bolaInicialY, 0);
               gl.glColor3f(0.647f, 0.165f, 0.165f);
               bola(gl, raioBola, 20);
               gl.glPopMatrix();
               
               
               // Verifica se a bola passou do limite no eixo y . Se passou, perder uma vida... São 5 vidas pra perder o jogo
               if (bolaInicialY < -1.0f) {
                    gols++;
                   // Inicializa a posição inicial da bola
                   bolaInicialX = 0;
                   bolaInicialY = -0.8f;
                   velocidadeBolaX = (float) (Math.cos(Math.toRadians(angulo)) * 0.0f);
                   velocidadeBolaY = (float) (Math.sin(Math.toRadians(angulo)) * 0.0f);
               }   
               
        if (pontuacao >= 200) {
            barraOponenteMovendo = true;
        }

        if (barraOponenteMovendo) {
         // Desenha a barra do oponente
            gl.glPushMatrix();
            gl.glTranslatef(translacaoBarraOponente, 0.5f, 0);
            gl.glColor3f(0, 0, 0);
            gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(-0.2f, 0.0f);
            gl.glVertex2f(-0.2f, -0.1f);  
            gl.glVertex2f(0.2f, -0.1f);   
            gl.glVertex2f(0.2f, 0.0f); 
            gl.glEnd();
            gl.glPopMatrix();
            
       translacaoBarraOponente += velocidadeBarraOponente;

    // Lógica de limites do movimento da barra do oponente
    if (translacaoBarraOponente > xMax - 0.2f) {
        translacaoBarraOponente = xMax - 0.2f;
        velocidadeBarraOponente = -velocidadeBarraOponente; // Inverte a direção ao atingir a borda direita
    } else if (translacaoBarraOponente < xMin + 0.2f) {
        translacaoBarraOponente = xMin + 0.2f;
        velocidadeBarraOponente = -velocidadeBarraOponente; // Inverte a direção ao atingir a borda esquerda
    }

    // Lógica de detecção de colisão com a bola (ajustada para detectar colisão em toda a barra)
    if (bolaInicialY - raioBola < 0.5f && bolaInicialY + raioBola > 0.6f && bolaInicialX > translacaoBarraOponente - 0.2f && bolaInicialX < translacaoBarraOponente + 0.2f) {
        // Calcula o ponto de impacto na barra do oponente
        float pontoImpacto = (bolaInicialX - translacaoBarraOponente) / 0.2f;

        // Verifica se o ponto de impacto está dentro do SRU
        if (pontoImpacto >= -1 && pontoImpacto <= 1) {
            // Define o ângulo de rebatido no final do SRU baseado no ponto de impacto
            angulo = pontoImpacto * 45; // Pode ajustar conforme necessário

            // Atualiza a direção da bola com base no novo ângulo
            velocidadeBolaX = (float) (Math.cos(Math.toRadians(angulo)) * 0.03f);
            velocidadeBolaY = (float) (Math.sin(Math.toRadians(angulo)) * 0.03f);
        }
    }
               break;   
        
        }
           }
           case 2 -> {
                  desenhoRetanguloCampoVerdeClaro(gl,0.0f);
                  desenhoRetanguloCampoVerdeEscuro(gl,0.2f);
                  desenhoRetanguloCampoVerdeClaro(gl,0.4f);
                  desenhoRetanguloCampoVerdeEscuro(gl,0.6f);
                  desenhoRetanguloCampoVerdeClaro(gl,0.8f);
                  desenhoRetanguloCampoVerdeEscuro(gl,1.0f);
                  desenhoRetanguloCampoVerdeClaro(gl,1.2f);
                  desenhoRetanguloCampoVerdeEscuro(gl,1.4f);
                  desenhoRetanguloCampoVerdeClaro(gl,1.6f);
                   desenhoRetanguloCampoVerdeEscuro(gl,1.8f);
                   
                  // Desenhe uma tela de fim de jogo 
                   desenhaTextoGrande(gl, 600, 500, Color.red, "FIM DE JOGO");
                   // Adicione uma mensagem para reiniciar o jogo
                   desenhaTexto(gl, 550, 275, Color.RED, "Pressione 'R' para jogar novamente");
                   //desenhaTexto(gl, 500, 300, Color.RED, "Pressione enter para jogar novamente");
                   translacaoBarra = 0;
                   translacaoBarraOponente = 0;
                   bolaInicialX = 0;
                   bolaInicialY = -1.0f;
                   desenhoRetanguloLateral(gl, -2.0f, 1.0f, 0.0f, 0.0f, 0.0f); // Barra lateral preta
                   desenhoRetanguloLateral(gl, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f); // Barra lateral preta
              
                  
              }
               
        }
        gl.glFlush();
           }
        }
    
 
    public void pauseJogo() {
       jogoPausado = !jogoPausado;
    }

            // Método para desenhar texto na tela
    private void desenhaTextoGrande(GL2 gl, int xPosicao, int yPosicao, Color cor, String frase) {
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glOrtho(0, Renderer.screenWidth, 0, Renderer.screenHeight, -1, 1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glLoadIdentity();

        textRenderer1.setColor(cor);
        textRenderer1.beginRendering(Renderer.screenWidth, Renderer.screenHeight);
        textRenderer1.draw(frase, xPosicao, yPosicao);
        textRenderer1.endRendering();

        gl.glPopMatrix();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPopMatrix();
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    private void desenhaTexto(GL2 gl, int xPosicao, int yPosicao, Color cor, String frase) {
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glOrtho(0, Renderer.screenWidth, 0, Renderer.screenHeight, -1, 1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glLoadIdentity();

        textRenderer.setColor(cor);
        textRenderer.beginRendering(Renderer.screenWidth, Renderer.screenHeight);
        textRenderer.draw(frase, xPosicao, yPosicao);
        textRenderer.endRendering();

        gl.glPopMatrix();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glPopMatrix();
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }
     private void desenhoRetanguloLateral(GL2 gl, float translateX, float translateY, float r, float g, float b) {
        gl.glPushMatrix();
        gl.glTranslatef(translateX, translateY, 0.0f);
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(r, g, b);
        gl.glVertex2f(0.0f, 0.0f);
        gl.glVertex2f(1.0f, 0.0f);
        gl.glVertex2f(1.0f, -2.0f);
        gl.glVertex2f(0.0f, -2.0f);
        gl.glEnd();
        gl.glPopMatrix();
    }
     
      private void desenhoRetanguloCampoVerdeClaro(GL2 gl, float translateX) {
        gl.glPushMatrix();
        gl.glTranslatef(translateX, 0.0f, 0.0f);
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0.0f, 0.4f, 0.0f);
        gl.glVertex2f(-1.0f, 1.0f);
        gl.glVertex2f(-0.8f, 1.0f);
        gl.glVertex2f(-0.8f, -1.0f);
        gl.glVertex2f(-1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();
    }
      private void desenhoRetanguloCampoVerdeEscuro(GL2 gl, float translateX) {
        gl.glPushMatrix();
        gl.glTranslatef(translateX, 0.0f, 0.0f);
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0.0f, 0.3f, 0.0f);
        gl.glVertex2f(-1.0f, 1.0f);
        gl.glVertex2f(-0.8f, 1.0f);
        gl.glVertex2f(-0.8f, -1.0f);
        gl.glVertex2f(-1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();
    }
    // Função para desenhar um círculo
    private void bola(GL2 gl, float radius, int segments) {
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glVertex2f(0, 0);
        for (int i = 0; i <= segments; i++) {
            double angle = 2.0 * Math.PI * i / segments;
            float x = (float) (radius * Math.cos(angle));
            float y = (float) (radius * Math.sin(angle));
            gl.glVertex2f(x, y);
        }
        
        gl.glEnd();
    }
    
        private void desenhaBoneco(GL2 gl, float x, float y) {
        gl.glPushMatrix();
        gl.glTranslatef(x, y, 0);

         // Corpo (linha vertical)
        gl.glLineWidth(2.0f);
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(0, 0.1f);
        gl.glVertex2f(0, -0.2f);
        gl.glEnd();

        // Perna esquerda (linha diagonal)
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(0, -0.2f);
        gl.glVertex2f(-0.1f, -0.3f);
        gl.glEnd();

        // Perna direita (linha diagonal)
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(0, -0.2f);
        gl.glVertex2f(0.1f, -0.3f);
        gl.glEnd();

        // Braço esquerdo (linha diagonal)
        gl.glPushMatrix();
        gl.glTranslatef(-0.001f, -0.1f + alturaBraços, 0); // Ajuste para a oscilação
        gl.glRotatef(anguloBracos, 0, 0, 1); // Rotação dos braços
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(0, 0);
        gl.glVertex2f(-0.1f, 0);
        gl.glEnd();
        gl.glPopMatrix();

        // Braço direito (linha diagonal)
        gl.glPushMatrix();
        gl.glTranslatef(-0.001f, -0.1f + alturaBraços, 0); // Ajuste para a oscilação
        gl.glRotatef(-anguloBracos, 0, 0, 1); // Rotação dos braços
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(0, 0);
        gl.glVertex2f(0.1f, 0);
        gl.glEnd();
        gl.glPopMatrix();
  
       
        // Cabeça (círculo)
        gl.glColor3f(0.647f, 0.165f, 0.165f); // Cor branca para a cabeça
        gl.glTranslatef(0, 0.1f, 0); // Ajuste para a cabeça ficar acima do corpo
        bola(gl, 0.06f, 20);
        gl.glPopMatrix();

        // Atualiza o ângulo e a altura dos braços para o próximo frame
        if (braçosSubindo) {
            anguloBracos += 1.0f;
            if (anguloBracos >= 45.0f) {
                braçosSubindo = false;
            }
        } else {
            anguloBracos -= 1.0f;
            if (anguloBracos <= -45.0f) {
                braçosSubindo = true;
            }
        }

        // Ajusta a altura dos braços para a oscilação
        alturaBraços = 0.05f * (float) Math.sin(Math.toRadians(anguloBracos));
    }

    public void reiniciarJogo() {
    // Coloque aqui qualquer lógica necessária para reiniciar o jogo
    gols = 0;
    pontuacao = -20;
    translacaoBarra = 0;
    translacaoBarraOponente = 0;
    bolaInicialX = 0;
    bolaInicialY = -0.8f;
    velocidadeBolaX = 0.09f;
    velocidadeBolaY = 0.09f;
    angulo = 0;
}
   
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        if (height == 0) height = 1;
        float aspect = (float) width / height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        if (width >= height)
            gl.glOrtho(xMin * aspect, xMax * aspect, yMin, yMax, zMin, zMax);
        else
          gl.glOrtho(xMin, xMax, yMin / aspect, yMax / aspect, zMin, zMax);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }
}



