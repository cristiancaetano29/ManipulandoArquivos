import java.util.HashMap;

public class Node implements Comparable<Node> {
    private int c;
    private int frequencia;
    private Node esquerda;
    private Node direita;

    public Node(int c, int frequencia) {
        this.c = c;
        this.frequencia = frequencia;
    }

    public Node(Node esquerda, Node direita) {
        this.esquerda = esquerda;
        this.direita = direita;
        this.frequencia = esquerda.frequencia + direita.frequencia;
    }

    public void gerarCodigos(HashMap<Integer, String> codigos, String codigo) {
        if (esquerda == null && direita == null) {
            codigos.put(c, codigo);
        } else {
            esquerda.gerarCodigos(codigos, codigo + "0");
            direita.gerarCodigos(codigos, codigo + "1");
        }
    }

    public int compareTo(Node no) {
        return frequencia - no.frequencia;
    }
}
