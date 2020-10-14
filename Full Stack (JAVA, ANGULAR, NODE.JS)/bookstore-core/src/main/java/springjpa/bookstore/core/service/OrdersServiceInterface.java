package springjpa.bookstore.core.service;

import springjpa.bookstore.core.domain.Orders;

import java.util.List;
import java.util.Set;

public interface OrdersServiceInterface {
    List<Orders> getAllOrders();

    Orders saveOrders(Orders order);

}
