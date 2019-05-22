package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    TodoRepository todoRepository;

    @RequestMapping("/2")
    public String page2(){
        return "page2";
    }
    @RequestMapping("/3")
    public String page3(){
        return "page3";
    }

    @RequestMapping("/index")
    public String listTodo(Model model){
        model.addAttribute("todos",todoRepository.findAll());
        return "index";
    }
    @GetMapping("/add")
    public String todoForm(Model model){
        model.addAttribute("todo",new Todo());
        return "todoform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todoform";
        }
        todoRepository.save(todo);
        return "redirect:/";

    }
    @RequestMapping("/detail/{id}")
    public String showTodo(@PathVariable("id") long id, Model model){
        model.addAttribute("todo",todoRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateTodo(@PathVariable("id") long id,Model model){
        model.addAttribute("todo",todoRepository.findById(id).get());
        return "todoform";
    }
    @RequestMapping("/delete/{id}")
    public String delTodo(@PathVariable("id") long id){

        todoRepository.deleteById(id);
        return "redirect:/";
    }
    @RequestMapping("/edit/{id}")
    public String editTodo(@PathVariable("id") long id,Model model){
        model.addAttribute("todo",todoRepository.findById(id).get());
        return "todoform";
    }
}
