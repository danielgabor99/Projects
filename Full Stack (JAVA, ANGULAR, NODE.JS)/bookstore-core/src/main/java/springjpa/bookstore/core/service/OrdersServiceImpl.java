package springjpa.bookstore.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springjpa.bookstore.core.domain.Client;
import springjpa.bookstore.core.domain.Orders;
import springjpa.bookstore.core.repository.ClientRepository;
import springjpa.bookstore.core.repository.OrdersRepository;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersServiceInterface {
    public static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public List<Orders> getAllOrders() {
        log.trace("getAllOrders --- method entered");

        List<Orders> result = ordersRepository.findAll();

        log.trace("getAllOrders: result={}", result);

        return result;
    }

    @Override
    public Orders saveOrders(Orders order) {
        log.trace("saveOrders --- method entered");
        return ordersRepository.save(order);
    }
}
