package com.example;

import com.example.util.HibernateUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/counter")
public class CounterController {

    private CounterDAO dao = new CounterDAO();

    @RequestMapping(method = RequestMethod.GET)
    public Counter getCounter(@RequestParam(value = "id", defaultValue = "1") int id) {
        return dao.getCounter(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Counter increaseCounter(@RequestParam(value = "id", defaultValue = "1") int id) {
        return dao.increase(id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Counter decreaseCounter(@RequestParam(value = "id", defaultValue = "1") int id) {
        return dao.decrease(id);
    }

    @RequestMapping(value = "/connect", method = RequestMethod.GET)
    public Counter checkConnection(@RequestParam(value = "name", defaultValue = "root") String name, @RequestParam(value = "password", defaultValue = "root") String password) {
        HibernateUtil.getSessionFactory(name, password);
        return dao.getCounter(1);
    }
}
