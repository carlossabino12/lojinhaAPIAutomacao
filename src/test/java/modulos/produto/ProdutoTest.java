package modulos.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ComponentePojo;
import pojo.ProdutoPojo;
import pojo.UsuarioPojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Teste de API Rest do módulo de Produto")
public class ProdutoTest {

    private String token;

    @BeforeEach
    public void BeforeEach(){

        //Configurando os dados da API Rest da lojinha
        baseURI = "http://165.227.93.41";
        //port = 8080;
        basePath = "/lojinha";

        //Obter o token do usuário admin
        this.token = given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.criarUsuarioAdmin())
                        .when()
                .post("v2/login")
            .then()
                .extract()
                    .path("data.token");

        System.out.println(token);

    }

    @Test
    @DisplayName("Validar que o valor do produto igual a 0.00 não é permitido")
    public void testeValidarLimitesZeradoProibidoValorProdutos(){

        //Tentar inserir um produto com o valor 0.00 e validar se a mensagem de erro foi apresentada
        //e o status cod retornado foi 422
         given()
                    .contentType(ContentType.JSON)
                    .header("token", this.token)
                    .body(ProdutoDataFactory.cirarProdutoComumComValorIgualA(0.00))
            .when()
                    .post("/v2/produtos")
            .then()
                    .assertThat()
                        .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                        .statusCode(422);
    }

    @Test
    @DisplayName("Validar que o valor do produto igual a 7000.01 não é permitido")
    public void testeValidarLimitesSeteMilProibidoValorProdutos(){

        //Tentar inserir um produto com o valor 7000.01 e validar se a mensagem de erro foi apresentada
        //e o status cod retornado foi 422
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.cirarProdutoComumComValorIgualA(7000.01))
            .when()
                .post("/v2/produtos")
            .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);
    }

}
