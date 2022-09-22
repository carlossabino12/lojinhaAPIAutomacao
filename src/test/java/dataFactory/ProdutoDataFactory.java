package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {

    public static ProdutoPojo cirarProdutoComumComValorIgualA (double valor){

        ProdutoPojo produto = new ProdutoPojo();
        produto.setProdutoNome("Samsung notebook");
        produto.setProdutoValor(valor);

        List<String> cores = new ArrayList<>();
        cores.add("preto");
        cores.add("branco");

        produto.setProdutoCores(cores);
        produto.setProdutoUrlMock("");

        List<ComponentePojo> componentes = new ArrayList<>();

        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome("S PEN");
        componente.setComponenteQuantidade(1);

        componentes.add(componente);

        produto.setComponente(componentes);

        return produto;

    }
}
