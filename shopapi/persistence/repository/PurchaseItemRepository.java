package com.shop.shopapi.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shop.shopapi.persistence.model.OrderDetails;
import com.shop.shopapi.persistence.model.PurchaseItem;

public interface PurchaseItemRepository extends CrudRepository<PurchaseItem, Long> {

    List<PurchaseItem> findByOrderDetails(OrderDetails orderDetails);

}
