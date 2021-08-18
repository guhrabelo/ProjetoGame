package dev.rabelo.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.rabelo.game.display.Display;
import dev.rabelo.game.grafix.Assets;
import dev.rabelo.game.states.Estado;
import dev.rabelo.game.states.EstadoGame;

public class Game implements Runnable{
	
	private Display display;
	public int width, height;
	public String titulo;
	
	private boolean rodando = false;
	private Thread thread;
	
	private BufferStrategy bufferStrategy;
	private Graphics graphics;
	
	//Estados
	private Estado gameState;
	
	
	
	public Game(String titulo, int width, int heigth) {
		this.width = width;
		this.height = heigth;
		this.titulo = titulo;
		
	}
	
	private void init() {
		display = new Display(titulo, width, height);
		Assets.init();
		
		gameState = new EstadoGame();
		Estado.setEstado(gameState);
	}
	
	
	private void marcacao() {
		if(Estado.getEstado() != null) {
			Estado.getEstado().marcacao();
		}
	}
	
	private void render() {
		bufferStrategy = display.getCanvas().getBufferStrategy(); //BufferStrategy é um jeito do pc desenhar coisas na tela
		if(bufferStrategy == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		graphics= bufferStrategy.getDrawGraphics();
		//limpando a tela
		graphics.clearRect(0, 0, width, height);
		//Desenha ai
		
		if(Estado.getEstado() != null) {
			Estado.getEstado().render(graphics);
		}
		
		//Termina o desenho
		bufferStrategy.show();
		graphics.dispose();
	}
	
	public void run() {
		init();
		
		int fps = 60;
		double tempoPorMarcacao = 1000000000 / fps;
		double delta = 0;
		long agora;
		long ultimaVez = System.nanoTime(); //devolve o tempo atual do pc em nanosegundo
		long temporizador = 0;
		int marcacoes = 0;
		
		while(rodando) {
			agora = System.nanoTime();
			delta += (agora - ultimaVez) / tempoPorMarcacao; //delta vai mostrar quanto tempo falta pra chamar a marcação e render de novo 
			temporizador +=  agora - ultimaVez;
			ultimaVez =  agora;
			
			if(delta >= 1) {
				marcacao();
				render();
				marcacoes++;
				delta--;
			}
			
			if(temporizador >= 1000000000) {
				System.out.println("marcacoes;"+ marcacoes);
				marcacoes = 0;
				temporizador = 0;
			}
		}
		
		stop();
	}
	
	public synchronized void start() {
		if(!rodando) {
			rodando = true;
			thread = new Thread(this); //roda essa classe independente do resto do programa
			thread.start();
		}		
	}
	
	public synchronized void stop() {
		if(rodando) {
			rodando = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
