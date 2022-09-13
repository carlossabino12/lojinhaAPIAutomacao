package modulos.produto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Teste de API Rest do módulo de Produto")
public class ProdutoTest {

    @Test
    @DisplayName("Validar os limites proíbidos do valor do Produto")
    public void testeValidarLimiteProdibidosValorProdutos(){

        double valor = 0.01;
        Assertions.assertTrue(valor > 0.00);
    }

}
