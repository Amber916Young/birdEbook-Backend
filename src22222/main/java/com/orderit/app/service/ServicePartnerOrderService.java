package com.orderit.app.service;

import com.orderit.app.dto.ServicePartnerOrderDTO;
import com.orderit.common.entity.ServicePartnerOrder;
import com.orderit.common.repository.ServicePartnerOrderRepository;
import com.orderit.common.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ServicePartnerOrderService {
    private final ServicePartnerOrderRepository servicePartnerOrderRepository;

    public void createServicePartnerOrder(ServicePartnerOrderDTO servicePartnerOrderDTO) {
        ServicePartnerOrder servicePartnerOrder = new ServicePartnerOrder();
        servicePartnerOrder.setCreatedBy(SecurityUtil.getCurrentUserName());
        servicePartnerOrder.setCompanyId(SecurityUtil.getCurrentUserCompanyId());
        servicePartnerOrder.setServicePartnerId(servicePartnerOrderDTO.getServicePartnerId());
        servicePartnerOrderRepository.save(servicePartnerOrder);
    }
}
