package lista;
//um simples nó genérico
public class No<T> {
	private T dado;
	private No<T> prox;
	public No(T dado){
		this.dado=dado;
		prox=null;
	}
	public T getDado() {
		return dado;
	}
	public void setDado(T dado) {
		this.dado = dado;
	}
	public No<T> getProx() {
		return prox;
	}
	public void setProx(No<T> prox) {
		this.prox = prox;
	}
}
