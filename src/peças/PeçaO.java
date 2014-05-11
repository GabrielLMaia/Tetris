package peças;
public class PeçaO extends Peça {
	public PeçaO(){
		super();
	}
	public PeçaO(int coorX, int coorY, Bloco[][] matrizBlocos) {
		super(coorX,coorY,matrizBlocos);
	}
	
	public void AtualizarBlocosDaPeça(){
			blocosDaPeça[0]= matrizLocal[coorCentralX][coorCentralY];
			blocosDaPeça[1]=matrizLocal[coorCentralX + 1][coorCentralY];
			blocosDaPeça[2]=matrizLocal[coorCentralX][coorCentralY + 1];
			blocosDaPeça[3]=matrizLocal[coorCentralX + 1][coorCentralY + 1];
	}
}