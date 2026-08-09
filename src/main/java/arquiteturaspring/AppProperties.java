package arquiteturaspring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.config") // prefixo das propriedades no arquivo application.yml
public class AppProperties {
    
    private String variavel;
    private int valor;
    private String mensagem;

    public String getVariavel() {
        return variavel;
    }
    public void setVariavel(String variavel) {
        this.variavel = variavel;
    }
    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
