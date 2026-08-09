package arquiteturaspring.todos;

import org.springframework.stereotype.Component;

@Component
public class TodoValidator {

    private TodoRepository repository;

    public TodoValidator(TodoRepository repository) {
        this.repository = repository;
    }

    public void validar(TodoEntity todo) {
        if (existeTodoComDecricao(todo.getDescricao())) {
            throw new IllegalArgumentException("Já existe um TODO com a mesma descrição");
        }
    }

    private boolean existeTodoComDecricao(String descricao) {
        return repository.existsByDescricao(descricao);
    }

}
