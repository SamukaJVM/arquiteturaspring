package arquiteturaspring;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@EnableConfigurationProperties // denota que a aplicação irá utilizar classes de configuração com propriedades
public class ArquiteturaspringApplication {

	public static void main(String[] args) {
		// SpringApplication.run(Application.class, args);

		SpringApplicationBuilder builder = new SpringApplicationBuilder(ArquiteturaspringApplication.class);

		builder.bannerMode(Banner.Mode.OFF);
		builder.profiles("producao", "homologacao");

		// definindo a inicialização lazy para todos os beans da aplicação
		builder.lazyInitialization(false);

		builder.run(args);

		// contexto da aplicação já iniciada:
		ConfigurableApplicationContext applicationContext = builder.context();
		// var produtoRepository = applicationContext.getBean("produtoRepository");

		ConfigurableEnvironment environment = applicationContext.getEnvironment();
		String applicationName = environment.getProperty("spring.application.name");
		System.out.println("Nome da aplicação: " + applicationName);

		// exemplo de uso do @Value para injetar valores de configuração do arquivo application.yml
		applicationContext.getBean(ExemploValue.class).imprimirVariavel();

		System.out.println(applicationContext.getBean(AppProperties.class).getValor()); 
		System.out.println(applicationContext.getBean(AppProperties.class).getMensagem()); 
	}

}
