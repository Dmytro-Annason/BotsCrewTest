package nekoder.controllers;

import nekoder.entities.Department;
import nekoder.entities.Lector;
import nekoder.entities.SearchQuery;
import nekoder.entities.enums.Degree;
import nekoder.repositories.DepartmentRepository;
import nekoder.repositories.LectorRepository;
import nekoder.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    LectorRepository lectorRepository;

    @ModelAttribute("query")
    public SearchQuery createQuery() {
        return new SearchQuery();
    }

    @ModelAttribute("department")
    public Department createDepartment() {
        return new Department();
    }

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @PostMapping(value = "/addLector")
    public String addLectorSubmit(@ModelAttribute Lector lector, Model model) {
        lectorRepository.save(lector);
        model.addAttribute("result", lector.getLastName() + " successfully added");
        return "result";
    }

    @GetMapping(value = "/addLector")
    public String addLector(Model model) {
        model.addAttribute("lector", new Lector());
        return "addLector";
    }

    @GetMapping(value = "/addDepartment")
    public String addDepartment(Model model) {
        model.addAttribute("lectors", lectorRepository.findAllWithoutDepartments());
        return "addDepartment";
    }

    @PostMapping(value = "/addDepartment")
    public String addDepartmentSubmit(@ModelAttribute Department department, Model model) {
        department.getLectors().add(department.getHead());
        departmentRepository.save(department);
        model.addAttribute("result", department.getName() + " successfully added");
        return "result";
    }

    @GetMapping(value = "/showLectors")
    public String showLectors(Model model) {
        model.addAttribute("lectors", lectorRepository.findAll());
        return "showLectors";
    }

    @GetMapping(value = "/showDepartments")
    public String showDepartments(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        return "showDepartments";
    }

    @GetMapping(value = "/headOfDepartments")
    public String headOfDepartments(Model model) {
        model.addAttribute("title", "head of department");
        model.addAttribute("action", "headOfDepartments");
        model.addAttribute("departments", departmentRepository.findAll());
        return "chooseDepartment";
    }

    @PostMapping(value = "/headOfDepartments")
    public String headOfDepartmentsSubmit(@ModelAttribute Department department, Model model) {
        Department searchDep = departmentRepository.findById(department.getId());
        model.addAttribute("result", searchDep.getHead().toString() + " - is head of " + searchDep.getName());
        return "result";
    }

    @GetMapping(value = "/statistic")
    public String statistic(Model model) {
        model.addAttribute("title", "statistic of department");
        model.addAttribute("action", "statistic");
        model.addAttribute("departments", departmentRepository.findAll());
        return "chooseDepartment";
    }

    @PostMapping(value = "/statistic")
    public String statisticSubmit(@ModelAttribute Department department, Model model) {
        Department searchDep = departmentRepository.findById(department.getId());
        model.addAttribute("result", "Statistic of " + searchDep.getName() + " : "
                + DepartmentService.getStatisticByDegree(searchDep));
        return "result";
    }

    @GetMapping(value = "/salaryAverage")
    public String salaryAverage(Model model) {
        model.addAttribute("title", "salary average of department");
        model.addAttribute("action", "salaryAverage");
        model.addAttribute("departments", departmentRepository.findAll());
        return "chooseDepartment";
    }

    @PostMapping(value = "/salaryAverage")
    public String salaryAverageSubmit(@ModelAttribute Department department, Model model) {
        Department searchDep = departmentRepository.findById(department.getId());
        model.addAttribute("result", "Average salary of " + searchDep.getName() + " : "
                + DepartmentService.getAverageSalary(searchDep));
        return "result";
    }

    @GetMapping(value = "/countEmployee")
    public String countEmployee(Model model) {
        model.addAttribute("title", "count employee of department");
        model.addAttribute("action", "countEmployee");
        model.addAttribute("departments", departmentRepository.findAll());
        return "chooseDepartment";
    }

    @PostMapping(value = "/countEmployee")
    public String countEmployeeSubmit(@ModelAttribute Department department, Model model) {
        Department searchDep = departmentRepository.findById(department.getId());
        model.addAttribute("result", "Count employee of " + searchDep.getName() + " : "
                + searchDep.getLectors().size());
        return "result";
    }

    @PostMapping(value = "/find")
    public String findSubmit(@ModelAttribute("query") SearchQuery query, Model model) {
        model.addAttribute("search", true);
        model.addAttribute("lectors", lectorRepository.freeSearch(query.getQuery()));
        return "showLectors";
    }
}
