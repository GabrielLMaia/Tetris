package peças;
public class PeçaZ extends Peça {
	public PeçaZ(){
		super();
		tipo='z';
	}
	public PeçaZ(int coorX, int coorY, Bloco[][] matrizBlocos) {
		super(coorX,coorY,matrizBlocos);
	}
	
	public void AtualizarBlocosDaPeça(){
		switch(rotação){
		case NORMAL:			
		case BAIXO:	
			blocosDaPeça[0]= matrizLocal[coorCentralX][coorCentralY];
			blocosDaPeça[1]=matrizLocal[coorCentralX][coorCentralY + 1];
			blocosDaPeça[2]=matrizLocal[coorCentralX - 1][coorCentralY];
			blocosDaPeça[3]=matrizLocal[coorCentralX - 1][coorCentralY - 1];
			break;
		case DIREIRA:
		case ESQUERDA:
			blocosDaPeça[0]= matrizLocal[coorCentralX][coorCentralY];
			blocosDaPeça[1]=matrizLocal[coorCentralX][coorCentralY + 1];
			blocosDaPeça[2]=matrizLocal[coorCentralX + 1][coorCentralY];
			blocosDaPeça[3]=matrizLocal[coorCentralX - 1][coorCentralY + 1];
			break;
			
		}
	}
}