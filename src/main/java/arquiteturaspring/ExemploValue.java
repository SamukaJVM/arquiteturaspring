package arquiteturaspring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExemploValue {

    // o @Value é usado para injetar valores de configuração do arquivo application.yml
    @Value("${app.config.variavel}")
    private String variavel;

    // método para imprimir o valor da variável injetada
    public void imprimirVariavel() {
        System.out.println("Valor da variável: " + variavel);
    }

}
