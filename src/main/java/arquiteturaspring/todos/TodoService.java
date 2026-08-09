package arquiteturaspring.todos;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private TodoRepository todoRepository;
    private TodoValidator validator;
    private MailSender mailSender;

    public TodoService(TodoRepository todoRepository, TodoValidator validator, MailSender mailSender) {
        this.todoRepository = todoRepository;
        this.validator = validator;
        this.mailSender = mailSender;
    }

    public TodoEntity salvar(TodoEntity todo) {
        validator.validar(todo);
        return todoRepository.save(todo);
    }

    public void atualizaStatus(TodoEntity todo) {
        todoRepository.save(todo);
        String status = todo.getConcluido() == Boolean.TRUE ? "Concluido" : "Não Concluido";
        mailSender.enviar("O status do TODO " + todo.getDescricao() + " foi atualizado para " + status);
    }

    public TodoEntity buscarPorID(Integer id) {
        return todoRepository.findById(id).orElse(null);
    }

}