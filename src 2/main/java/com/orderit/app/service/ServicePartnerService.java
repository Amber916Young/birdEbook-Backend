package com.orderit.app.service;

import com.google.common.collect.Lists;
import com.orderit.app.dto.CategoryServicePartnerDTO;
import com.orderit.app.dto.ServicePartnerDTO;
import com.orderit.app.mapper.ServicePartnerMapper;
import com.orderit.common.entity.ServicePartner;
import com.orderit.common.repository.ServicePartnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ServicePartnerService {

    private final ServicePartnerRepository servicePartnerRepository;
    private final ServicePartnerMapper servicePartnerMapper;

    public  List<CategoryServicePartnerDTO>  getAllPartnerServicesAndServices() {
        List<ServicePartner> servicePartnerList = servicePartnerRepository.findAll();
        HashMap<String,  List<ServicePartnerDTO>> categoryMap = new HashMap<>();

        servicePartnerList.forEach(servicePartner ->  {
            String category = servicePartner.getCategory();
            ServicePartnerDTO servicePartnerDTO = servicePartnerMapper.toDTO(servicePartner);

            List<ServicePartnerDTO> servicePartnerDTOList = categoryMap.getOrDefault(category, Lists.newArrayList());
            servicePartnerDTOList.add(servicePartnerDTO);
            categoryMap.putIfAbsent(category, servicePartnerDTOList);
        });

        List<CategoryServicePartnerDTO> result = new ArrayList<>();

        categoryMap.forEach((key, value) -> {
            CategoryServicePartnerDTO categoryServicePartnerDTO = new CategoryServicePartnerDTO();
            categoryServicePartnerDTO.setCategory(key);
            categoryServicePartnerDTO.setServicePartners(value);
            result.add(categoryServicePartnerDTO);
        });

        return result;
    }
}
