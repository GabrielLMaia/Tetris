package jogo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import lista.Lista;
import lista.No;

public class Placar {
	private static final String NOME_ARQUIVO = "placar.txt";

	private class Jogador {
		@SuppressWarnings("unused")//not really dude
		String nome;
		int pontos;

		public Jogador( String nome,int pontos) {
			this.nome = nome;
			this.pontos = pontos;
		}
	}

	Lista<Jogador> pontos = new Lista<Jogador>();

	public void armazenar(String nome, int pontos) {
		File f = new File(NOME_ARQUIVO);
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter(f, true);
			bw = new BufferedWriter(fw);

			bw.write(nome + "," + pontos);
			bw.newLine();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Lista<Jogador> pegarJogadores() {
		Lista<Jogador> j = new Lista<Jogador>();

		File f = new File(NOME_ARQUIVO);
		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);

			String linha = br.readLine();
			while (linha != null) {
				String[] dados = linha.split(",");
				j.addH(new Jogador(dados[0], Integer.parseInt(dados[1])));
				linha = br.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return j;
	}
	public Lista<Jogador> pegarOrdenado(){
		return ordenarSeparar(pegarJogadores());
	}
	public void imprimir(Lista<Jogador> j){
		No<Jogador>aux=j.getHead();
		while(aux!=null){
			System.out.println(aux.getDado().pontos);
			aux=aux.getProx();
		}
	}
	private Lista<Jogador> ordenarSeparar(Lista<Jogador> jogadores) {
		if (jogadores.getTam() <= 1)
			return jogadores;
		Lista<Jogador> esquerda = new Lista<Jogador>(), direita = new Lista<Jogador>();
		int meio = jogadores.getTam() / 2;
		No<Jogador> aux = jogadores.getHead();
		for (int i = 0; i < meio; i++) {
			esquerda.addH(aux.getDado());
			aux = aux.getProx();
		}
		for (int i = meio; i < jogadores.getTam(); i++) {
			direita.addH(aux.getDado());
			aux = aux.getProx();
		}
		esquerda = ordenarSeparar(esquerda);
		direita = ordenarSeparar(direita);
		return ordenarJuntar(esquerda, direita);
	}

	private Lista<Jogador> ordenarJuntar(Lista<Jogador> esquerda,Lista<Jogador> direita) {
		Lista<Jogador> junção = new Lista<Jogador>();
		while(esquerda.getHead()!=null||direita.getHead()!=null){
			if(esquerda.getHead()!=null&&direita.getHead()!=null){
				if(esquerda.getHead().getDado().pontos>direita.getHead().getDado().pontos){
					junção.addT(esquerda.getHead().getDado());
					esquerda.remH();
				}else{
					junção.addT(direita.getHead().getDado());
					direita.remH();
				}
					
			}else{
				while(esquerda.getHead()!=null){
					junção.addT(esquerda.getHead().getDado());
					esquerda.remH();
				}
				while(direita.getHead()!=null){
					junção.addT(direita.getHead().getDado());
					direita.remH();
				}
			}
		}
		return junção;
	}
	public String[][] matrizTabela(){
		Lista<Jogador> jogadores=pegarOrdenado();
		String[][] jogadoresMatriz =new String[jogadores.getTam()][2];
		No<Jogador> aux=jogadores.getHead();
		for (int i = 0; i < jogadoresMatriz.length; i++) {
				jogadoresMatriz[i][0]=aux.getDado().nome;
				jogadoresMatriz[i][1]=aux.getDado().pontos+"";
				aux=aux.getProx();
		}
		return jogadoresMatriz;
	} 
}