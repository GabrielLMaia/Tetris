package pe�as;
public class Pe�aZ extends Pe�a {
	public Pe�aZ(){
		super();
		tipo='z';
	}
	public Pe�aZ(int coorX, int coorY, Bloco[][] matrizBlocos) {
		super(coorX,coorY,matrizBlocos);
	}
	
	public void AtualizarBlocosDaPe�a(){
		switch(rota��o){
		case NORMAL:			
		case BAIXO:	
			blocosDaPe�a[0]= matrizLocal[coorCentralX][coorCentralY];
			blocosDaPe�a[1]=matrizLocal[coorCentralX][coorCentralY + 1];
			blocosDaPe�a[2]=matrizLocal[coorCentralX - 1][coorCentralY];
			blocosDaPe�a[3]=matrizLocal[coorCentralX - 1][coorCentralY - 1];
			break;
		case DIREIRA:
		case ESQUERDA:
			blocosDaPe�a[0]= matrizLocal[coorCentralX][coorCentralY];
			blocosDaPe�a[1]=matrizLocal[coorCentralX][coorCentralY + 1];
			blocosDaPe�a[2]=matrizLocal[coorCentralX + 1][coorCentralY];
			blocosDaPe�a[3]=matrizLocal[coorCentralX - 1][coorCentralY + 1];
			break;
			
		}
	}
}