package controller;

import model.Customer;
import model.CustomerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.CustomerServiceImpl;


import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class CustomerController {

    CustomerServiceImpl customerService = new CustomerServiceImpl();

    @Autowired
    private Environment environment;

    @GetMapping("/")
    public ModelAndView list() {

        List<Customer> customerList = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("customers",customerList);
        return modelAndView;
    }

    @GetMapping("/customer/create")
    public ModelAndView create(Model model) {
        return new ModelAndView("/create", "customer", new CustomerForm());
    }

    @PostMapping(value = "/customer/create")
    public ModelAndView save(@ModelAttribute CustomerForm customerForm) {
        MultipartFile file = customerForm.getImg();
        String image = file.getOriginalFilename();
        String fileUpload = environment.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Customer customer = new Customer(customerForm.getId(), customerForm.getName(), customerForm.getEmail(), customerForm.getAddress(), image);
        customerService.save(customer);

        return list();
    }


}

