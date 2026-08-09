package arquiteturaspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import arquiteturaspring.todos.TodoEntity;
import arquiteturaspring.todos.TodoValidator;

/**
* O Escopo padrão dos beans é singleton, ou seja, uma única instância do bean é criada e compartilhada em toda a aplicação
* @Scope("singleton") // @Scope é para Definir o escopo manualmente como singleton
* @Scope ("request") // Denota que o bean é gerenciado pelo Spring e terá escopo request
* @Scope ("session") // Denota que o bean é gerenciado pelo Spring e terá escopo session
* @Scope("prototype") // Denota que o bean é gerenciado pelo Spring e terá escopo prototype
* @Scope("application") // Denota que o bean é gerenciado pelo Spring e terá escopo application
*/
@Component
@Scope("singleton")
public class BeanGerenciado {

    @Autowired
    private TodoValidator validator; 

    // fazendo injeção via propriedade
    public void utilizar(){
        var todo = new TodoEntity();
        validator.validar(todo);
    }

    // fazendo injeção via construtor (forma recomendada pelo spring)
    public BeanGerenciado(TodoValidator validator) {
        this.validator = validator;
    }

    // fazendo injeção via setter
    @Autowired
    public void setValidator(TodoValidator validator) {
        this.validator = validator;
    }
    
}
