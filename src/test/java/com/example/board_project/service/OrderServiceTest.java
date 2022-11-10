package com.example.board_project.service;

import com.example.board_project.domain.Address;
import com.example.board_project.domain.Member;
import com.example.board_project.domain.Order;
import com.example.board_project.domain.OrderStatus;
import com.example.board_project.domain.item.Book;
import com.example.board_project.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    private Member createMember(){
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }

    private Book createBook(){
        Book book = new Book();
        book.setName("JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        return book;
    }

    @Test
    public void 상품주문() throws Exception{

        //given
        Member member = createMember();

        Book book = createBook();


        //when
        Long orderId = orderService.order(member.getId(), book.getId(), 2);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus());
        assertEquals(1, getOrder.getOrderItems().size());

    }



    @Test
    public void 주문취소() throws Exception{

        //given
        Member member = createMember();

        Book book = createBook();

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);


        //when

        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals(10,book.getStockQuantity());

    }

    @Test
    public void 상품주문_재고수량초과() throws Exception{

        //given

        //when

        //then

    }


}