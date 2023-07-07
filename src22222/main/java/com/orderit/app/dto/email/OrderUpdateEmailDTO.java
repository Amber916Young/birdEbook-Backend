package com.orderit.app.dto.email;

import com.orderit.common.enums.OrderStatus;
import lombok.Data;

/**
 * Hi {Company Name},
 *
 * Your order {order id} has been {Updated / Confirmed / Rejected} By {Supplier Name}, click button to view the order.
 *
 * // view order button (url would be available later)
 *
 * Thank you.
 * Yours sincerely.
 * OrderIT
 */
@Data
public class OrderUpdateEmailDTO {
    private String supplierName;
    private Long orderId;
    private String companyName;
    private String orderStatus;
    private String viewLink;

}
