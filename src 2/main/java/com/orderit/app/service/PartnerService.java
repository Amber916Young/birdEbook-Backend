package com.orderit.app.service;

import com.orderit.common.entity.Partner;
import com.orderit.common.exception.ErrorReasonCode;
import com.orderit.common.exception.NotFoundRequestException;
import com.orderit.common.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PartnerService {

    private final PartnerRepository partnerRepository;

    public Partner getPartnerById(Long partnerId) {
        return partnerRepository.findById(partnerId).orElseThrow(()->new NotFoundRequestException(ErrorReasonCode.Not_Found_Entity));
    }

    public List<Partner> getAllPartners() {
        return partnerRepository.findAll();
    }
}
