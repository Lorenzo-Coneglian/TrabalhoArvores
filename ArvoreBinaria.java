public class ArvoreBinaria {
    No raiz;

    public ArvoreBinaria() {
        this.raiz = new No(null);
        System.out.println("Árvore criada com sucesso");
    }

    public void inserir(Integer conteudo) {
        No novoNo = new No(conteudo);
        if(estaVazia()) {
            this.raiz = novoNo;
            System.out.println("Raiz criada com sucesso com valor: " + novoNo.getConteudo());
        } else {
            inserirRecursivo(novoNo, this.raiz);
        }
    }

    public void inserirRecursivo(No novoNo, No aux) {
        if(aux.getConteudo() > novoNo.getConteudo()) {
            if(aux.getEsquerda() == null) {
                aux.setEsquerda(novoNo);
                System.out.println("Nó " + novoNo.getConteudo() + " inserido com sucesso.");
                return;
            } else {
                inserirRecursivo(novoNo, aux.getEsquerda());
            }
        } else if (aux.getConteudo() < novoNo.getConteudo()) {
            if(aux.getDireita() == null) {
                aux.setDireita(novoNo);
                System.out.println("Nó " + novoNo.getConteudo() + " inserido com sucesso.");
                return;
            } else {
                inserirRecursivo(novoNo, aux.getDireita());
            }
        } else {
            System.out.println("Não são permitidos nós repetidos na árvore binária. O " + novoNo.getConteudo() + " já existe na árvore.");
            return;
        }
    }

    private boolean estaVazia () {
        return this.raiz.getConteudo() == null;
    }

    public void percurso(String percurso) {
        if(estaVazia()) {
            System.out.println("A árvore não existe.");
            return;
        }

        switch (percurso) {
            case("Pre"):
                System.out.println("Executando a árvore em pré ordem.");
                this.preOrdem(this.raiz);
                break;
            case("Em"):
                System.out.println("Executando a árvore em ordem.");
                this.emOrdem(this.raiz);
                break;
            case("Pos"):
                System.out.println("Executando a árvore em pós ordem.");
                this.posOrdem(this.raiz);
                break;
            default:
                System.out.println("Percurso inexistente!");
                break;

        }
    }

    private void posOrdem(No no) {
        if(no == null) {
            return;
        }
        posOrdem(no.getEsquerda());
        posOrdem(no.getDireita());
        System.out.println(no.getConteudo());
    }

    private void preOrdem(No no) {
        if(no == null) {
            return;
        }
        System.out.println(no.getConteudo());
        preOrdem(no.getEsquerda());
        preOrdem(no.getDireita());
    }

    private void emOrdem(No no) {
        if(no == null) {
            return;
        }
        emOrdem(no.getEsquerda());
        System.out.println(no.getConteudo());
        emOrdem(no.getDireita());
    }

    public No buscar(int conteudo){
        if (estaVazia()){
            System.out.println("Árvore está vazia.");
        } else {
            No aux = this.raiz;
            do {
                if (aux.getConteudo()==conteudo){
                    return aux;
                } else if (aux.getConteudo()>conteudo){
                    aux = aux.getEsquerda();
                } else if (aux.getConteudo()<conteudo){
                    aux = aux.getDireita();
                }
            } while(aux!=null);
        }
        return null;
    }

    //Retorna o nó pai do nó procurado. Se ele não existir, retorna null. Se for a raiz, retorna ela própria.
    public No buscarPai(int conteudo){
        if (estaVazia()){
            System.out.println("Árvore está vazia.");
        } else if (conteudo == this.raiz.getConteudo()) {
            return this.raiz;
        } else if (buscar(conteudo)==null){
            return null;
        } else {
            No aux = this.raiz;
            while(aux.getEsquerda()!=null || aux.getDireita()!=null) {
                if (aux.getEsquerda().getConteudo()==conteudo || aux.getDireita().getConteudo()==conteudo){
                    return aux;
                } else if (aux.getConteudo()>conteudo){
                    aux = aux.getEsquerda();
                } else if (aux.getConteudo()<conteudo){
                    aux = aux.getDireita();
                }
            }
        }
        return null;
    }

    public void remover(int conteudo){
        if (estaVazia()){
            System.out.println("A Árvore está vazia.");
            return;
        } else {
            No noProcurado = buscar(conteudo);
            if (noProcurado == null) {
                System.out.println("Elemento não foi encontrado.");
                return;
            }
            switch (noProcurado.quantidadeFilhos()){
                case 0:
                    removerFolha(conteudo);
                    break;
                case 1:
                    removerUmFilho(conteudo);
                    break;
                case 2:
                    removerDoisFilhos(conteudo);
            }
        }
    }

    public void removerFolha(int conteudo){
        No noProcurado = buscar(conteudo);
        if (noProcurado==this.raiz){
            this.raiz.setConteudo(null);
            System.out.println("Raiz removida.");
        } else{
            No noPai = buscarPai(conteudo);
            if (noPai.getEsquerda()==noProcurado) noPai.setEsquerda(null);
            else noPai.setDireita(null);
            System.out.println("Nó folha removido.");
        }
    }

    public void removerUmFilho(int conteudo){
        No noProcurado = buscar(conteudo);
        if (noProcurado==this.raiz){
            if (this.raiz.getEsquerda()==null) this.raiz = this.raiz.getDireita();
            else this.raiz = this.raiz.getEsquerda();
            System.out.println("Raiz removida.");
        } else {
            No noPai = buscarPai(conteudo);
            No noFilho;
            if (noProcurado.getEsquerda()==null) noFilho = noProcurado.getDireita();
            else noFilho = noProcurado.getEsquerda();
            if (noPai.getConteudo() > noProcurado.getConteudo()) noPai.setEsquerda(noFilho);
            else noPai.setDireita(noFilho);
            System.out.println("Nó com 1 filho removido.");
        }
    }

    public void removerDoisFilhos(int conteudo){
        No noProcurado = buscar(conteudo);
        if (noProcurado==this.raiz){
            No noSubstituto = encontrarSubstituto(conteudo);
            remover(noSubstituto.getConteudo());
            noSubstituto.setEsquerda(noProcurado.getEsquerda());
            noSubstituto.setDireita(noProcurado.getDireita());
            this.raiz = noSubstituto;
            System.out.println("Raiz removida.");
        } else {
            No noPai = buscarPai(conteudo);
            No noSubstituto = encontrarSubstituto(conteudo);
            remover(noSubstituto.getConteudo());
            noSubstituto.setEsquerda(noProcurado.getEsquerda());
            noSubstituto.setDireita(noProcurado.getDireita());
            if (noPai.getEsquerda()==noProcurado) noPai.setEsquerda(noSubstituto);
            else noPai.setDireita(noSubstituto);
            System.out.println("Nó com 2 filhos removido");
        }
    }

    public No encontrarSubstituto(int conteudo){
        if (conteudo-encontrarPredecessor(conteudo).getConteudo()<encontrarSucessor(conteudo).getConteudo()-conteudo) return encontrarPredecessor(conteudo);
        else return encontrarSucessor(conteudo);
    }

    public No encontrarPredecessor(int conteudo){
        No aux = buscar(conteudo).getEsquerda();
        while (aux.getDireita()!=null){
            aux = aux.getDireita();
        }
        return aux;
    }

    public No encontrarSucessor(int conteudo){
        No aux = buscar(conteudo).getDireita();
        while (aux.getEsquerda()!=null){
            aux = aux.getEsquerda();
        }
        return aux;
    }
}
