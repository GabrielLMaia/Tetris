package pe�as;
public class Pe�aO extends Pe�a {
	public Pe�aO(){
		super();
	}
	public Pe�aO(int coorX, int coorY, Bloco[][] matrizBlocos) {
		super(coorX,coorY,matrizBlocos);
	}
	
	public void AtualizarBlocosDaPe�a(){
			blocosDaPe�a[0]= matrizLocal[coorCentralX][coorCentralY];
			blocosDaPe�a[1]=matrizLocal[coorCentralX + 1][coorCentralY];
			blocosDaPe�a[2]=matrizLocal[coorCentralX][coorCentralY + 1];
			blocosDaPe�a[3]=matrizLocal[coorCentralX + 1][coorCentralY + 1];
	}
}