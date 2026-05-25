public class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvoreBinaria = new ArvoreBinaria();
        arvoreBinaria.inserir(10);
        arvoreBinaria.inserir(5);
        arvoreBinaria.inserir(1);
        arvoreBinaria.inserir(7);
        arvoreBinaria.inserir(13);
        arvoreBinaria.inserir(12);
        arvoreBinaria.inserir(14);
        System.out.println(arvoreBinaria.buscarPai(10));
        arvoreBinaria.remover(10);
        arvoreBinaria.percurso("Pre");
    }
}
