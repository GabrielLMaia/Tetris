package jogo;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lista.Lista;
import peças.*;
public class ListaPeças extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Lista<Peça> lista=new Lista<Peça>();
	private char[]bag=new char[]{'t','l','s','z','o','j','i'};
	private boolean[] repetido=new boolean[7];
	public ListaPeças(){
	    setLayout(null);
	    
	    JLabel divLista = new JLabel("");
		divLista.setIcon(new ImageIcon(Principal.class.getResource("/imagens/divLinha.png")));
		divLista.setBounds(0,120,120,30);
		this.add(divLista);
	    
	    JPanel j =new JPanel();
	    j.setBounds(0,30,120,480);
	    j.setBackground(Color.BLACK);
	    add(j);
		
		JLabel imagemLista = new JLabel("");
		imagemLista.setIcon(new ImageIcon(Principal.class.getResource("/imagens/List.png")));
		imagemLista.setBounds(0,0,150,600);
		this.add(imagemLista);
	}
	public void gerarPeça(){
		
		
	}
	public static Peça traduzir(char c){
		switch(c){
		case 't':
			return new PeçaT();
		}
		return null;
	}
}
