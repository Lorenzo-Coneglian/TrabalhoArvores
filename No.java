public class No {
    Integer conteudo;
    No esquerda;
    No direita;

    public No(Integer conteudo) {
        this.conteudo = conteudo;
        this.esquerda = null;
        this.direita = null;
    }

    public Integer getConteudo() {
        return conteudo;
    }

    public void setConteudo(Integer conteudo) {
        this.conteudo = conteudo;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }

    public int quantidadeFilhos(){
        int quantFilhos = 0;
        if (this.getEsquerda()!=null) quantFilhos+=1;
        if (this.getDireita()!=null) quantFilhos+=1;
        return quantFilhos;
    }

    @Override public String toString(){
        return String.valueOf(conteudo)
    }
}
