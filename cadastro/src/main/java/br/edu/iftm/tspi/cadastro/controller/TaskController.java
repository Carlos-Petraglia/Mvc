package br.edu.iftm.tspi.cadastro.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.iftm.tspi.cadastro.model.Task;

@Controller
public class TaskController {

    List<Task> tasks = new ArrayList<>();

    @GetMapping("/create")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("create");
        mv.addObject("task", new Task());
        return mv;
    }

    private Long getNextTaskId() {
        Long maxId = tasks.stream()
                .map(Task::getId)
                .max(Comparator.nullsLast(Long::compareTo))
                .orElse(0L);
        return maxId + 1;
    }

    @PostMapping("/create")
    public String create(Task task) {
        System.out.println(" Get ID" + task.getId());

        if (task.getId() != null) {
            Task taskFind = tasks.stream().filter(taskItem -> task.getId().equals(taskItem.getId())).findFirst()
                    .get();
            tasks.set(tasks.indexOf(taskFind), task);
        } else {
            Long id = getNextTaskId();
            tasks.add(new Task(id, task.getName(), task.getEmail(), task.getNick()));
        }

        return "redirect:/list";
    }

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("list");
        mv.addObject("tasks", tasks);
        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("create");

        Task taskFind = tasks.stream().filter(task -> id.equals(task.getId())).findFirst().get();

        mv.addObject("task", taskFind);
        return mv;

    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        tasks.removeIf(task -> id.equals(task.getId()));
        return "redirect:/list";
    }
}
