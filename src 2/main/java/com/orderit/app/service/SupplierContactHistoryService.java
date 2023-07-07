package com.orderit.app.service;

import com.orderit.common.entity.SupplierContactHistory;
import com.orderit.common.repository.SupplierContactHistoryRepository;
import com.orderit.common.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SupplierContactHistoryService {
    private final SupplierContactHistoryRepository supplierContactHistoryRepository;
    public void createSupplerContactHistory(Long supplierId) {
        SupplierContactHistory contactHistory = new SupplierContactHistory();
        contactHistory.setSupplierId(supplierId);
        contactHistory.setCompanyId(SecurityUtil.getCurrentUserCompanyId());
        supplierContactHistoryRepository.save(contactHistory);
    }
}
