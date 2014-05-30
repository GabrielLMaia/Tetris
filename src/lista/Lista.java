package lista;
//uma lista simplesmente encadiada,utilizada na lista de peças.Ela possuí o adicionar e remover na cabeça e no rabo 
public class Lista<T> {
	private No<T> head;
	private No<T> tail;
	private int tam = 0;

	public int getTam() {
		return tam;
	}

	public No<T> getHead() {
		return head;
	}

	public void addH(T dado) {
		No<T> n = new No<T>(dado);
		if (head == null) {
			head = tail = n;
		} else {
			n.setProx(head);
			head = n;
		}
		tam++;
	}

	public void addT(T dado) {
		No<T> n = new No<T>(dado);
		if (head == null) {
			head = tail = n;
		} else {
			tail.setProx(n);
			tail = n;
		}
		tam++;
	}

	public void remH() {
		if (tam != 0) {
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				head = head.getProx();
			}
			tam--;
		}
	}

	public void remT() {
		if (tam != 0) {
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				No<T> aux = head;
				while (aux.getProx() != tail) {
					aux = aux.getProx();
				}
				tail = aux;
				aux.setProx(null);
			}
			tam--;
		}
	}
}
