package arquiteturaspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import arquiteturaspring.todos.TodoEntity;
import arquiteturaspring.todos.TodoValidator;

@Component
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
