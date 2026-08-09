package arquiteturaspring.todos;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoEntity salvar(TodoEntity novotodo) {
        return todoRepository.save(novotodo);
    }

    public void atualizaStatus(TodoEntity todo) {
        todoRepository.save(todo);
    }

    public TodoEntity buscarPorID(Integer id) {
        return todoRepository.findById(id).orElse(null);
    }

}