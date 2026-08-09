package arquiteturaspring.todos;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public TodoEntity salvar(@RequestBody TodoEntity novotodo) {
        try {
            return this.todoService.salvar(novotodo);
        } catch (IllegalArgumentException e) {
            var mensagemERrro = e.getMessage();
            throw new ResponseStatusException(HttpStatus.CONFLICT, mensagemERrro);
        }
    }

    @PutMapping("{id}")
    public void atualizar(@PathVariable("id") Integer id, @RequestBody TodoEntity novotodo) {
        novotodo.setId(id);
        todoService.salvar(novotodo);
    }

    @GetMapping("{id}")
    public TodoEntity buscar(@PathVariable("id") Integer id) {
        return todoService.buscarPorID(id);
    }

}
