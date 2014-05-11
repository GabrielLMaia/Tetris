package peças;
public class PeçaI extends Peça {

	public PeçaI(int coorX, int coorY, Bloco[][] matrizBlocos) {
		super(coorX,coorY,matrizBlocos);
	}
	
	public void AtualizarBlocosDaPeça(){
		switch(rotação){
		case NORMAL:			
			blocosDaPeça[0]= matrizLocal[coorCentralX][coorCentralY];
			blocosDaPeça[1]=matrizLocal[coorCentralX][coorCentralY + 2];
			blocosDaPeça[2]=matrizLocal[coorCentralX][coorCentralY - 1];
			blocosDaPeça[3]=matrizLocal[coorCentralX][coorCentralY + 1];
			break;
		case DIREIRA:
			blocosDaPeça[0]= matrizLocal[coorCentralX][coorCentralY];
			blocosDaPeça[1]=matrizLocal[coorCentralX - 1][coorCentralY];
			blocosDaPeça[2]=matrizLocal[coorCentralX + 1][coorCentralY];
			blocosDaPeça[3]=matrizLocal[coorCentralX - 2][coorCentralY];
			break;
		case BAIXO:
			blocosDaPeça[0]= matrizLocal[coorCentralX][coorCentralY];
			blocosDaPeça[1]=matrizLocal[coorCentralX][coorCentralY - 2];
			blocosDaPeça[2]=matrizLocal[coorCentralX][coorCentralY - 1];
			blocosDaPeça[3]=matrizLocal[coorCentralX][coorCentralY + 1];
			break;
		case ESQUERDA:
			blocosDaPeça[0]= matrizLocal[coorCentralX][coorCentralY];
			blocosDaPeça[1]=matrizLocal[coorCentralX - 1][coorCentralY];
			blocosDaPeça[2]=matrizLocal[coorCentralX + 1][coorCentralY];
			blocosDaPeça[3]=matrizLocal[coorCentralX + 2][coorCentralY];
			break;
			
		}
	}
}