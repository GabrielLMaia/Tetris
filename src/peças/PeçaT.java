package pe�as;
public class Pe�aT extends Pe�a {

	public Pe�aT(int coorX, int coorY, Bloco[][] matrizBlocos) {
		super(coorX,coorY,matrizBlocos);
	}
	
	public void AtualizarBlocosDaPe�a(){
		switch(rota��o){
		case NORMAL:			
			blocosDaPe�a[0]= matrizLocal[coorCentralX][coorCentralY];
			blocosDaPe�a[1]=matrizLocal[coorCentralX - 1][coorCentralY];
			blocosDaPe�a[2]=matrizLocal[coorCentralX][coorCentralY - 1];
			blocosDaPe�a[3]=matrizLocal[coorCentralX][coorCentralY + 1];
			break;
		case DIREIRA:
			blocosDaPe�a[0]= matrizLocal[coorCentralX][coorCentralY];
			blocosDaPe�a[1]=matrizLocal[coorCentralX - 1][coorCentralY];
			blocosDaPe�a[2]=matrizLocal[coorCentralX + 1][coorCentralY];
			blocosDaPe�a[3]=matrizLocal[coorCentralX][coorCentralY + 1];
			break;
		case BAIXO:
			blocosDaPe�a[0]= matrizLocal[coorCentralX][coorCentralY];
			blocosDaPe�a[1]=matrizLocal[coorCentralX + 1][coorCentralY];
			blocosDaPe�a[2]=matrizLocal[coorCentralX][coorCentralY - 1];
			blocosDaPe�a[3]=matrizLocal[coorCentralX][coorCentralY + 1];
			break;
		case ESQUERDA:
			blocosDaPe�a[0]= matrizLocal[coorCentralX][coorCentralY];
			blocosDaPe�a[1]=matrizLocal[coorCentralX - 1][coorCentralY];
			blocosDaPe�a[2]=matrizLocal[coorCentralX + 1][coorCentralY];
			blocosDaPe�a[3]=matrizLocal[coorCentralX][coorCentralY - 1];
			break;
			
		}
	}
}
    