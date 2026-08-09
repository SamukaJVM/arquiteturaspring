package arquiteturaspring.todos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class TudoController {

    private TodoService todoService;

    public TudoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public TodoEntity salvar(@RequestBody TodoEntity novotodo) {
        return this.todoService.salvar(novotodo);
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
