package peças;

public abstract class Peças {
	Bloco [] blocos;
	enum sentido{NORMAL,DIREIRA,BAIXO,ESQUERDA};
	sentido s;
	public Peças() {
		blocos=new Bloco[4];
		s=sentido.NORMAL;
	}	
	public abstract boolean colidirB();
	public abstract boolean colidirD();
	public abstract boolean colidirE();
	public abstract void moverB();
	public abstract void moverD();
	public abstract void moverE();
	public abstract void girar();
	public abstract void pintar();
	
}
