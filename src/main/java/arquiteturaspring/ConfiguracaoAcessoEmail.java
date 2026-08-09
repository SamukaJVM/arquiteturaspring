package arquiteturaspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracaoAcessoEmail {

    @Autowired
    private AppProperties appProperties; // injetando a classe de configuração com propriedades do application.yml

    public String getEmail() {
        return appProperties.getVariavel(); // acessando a propriedade "variavel" do arquivo application.yml
    }

}
