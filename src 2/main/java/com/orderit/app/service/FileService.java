package com.orderit.app.service;

import com.orderit.common.entity.Company;
import com.orderit.common.utils.SecurityUtil;
import com.orderit.common.utils.s3.S3FileManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FileService {
    private final S3FileManager s3FileManager;
    private final CompanyService companyService;

    @SneakyThrows
    public String uploadCompanyLogo(MultipartFile imageFile) throws IOException {
        Long companyId = SecurityUtil.getCurrentUserCompanyId();
        Company company = companyService.getCompanyById(companyId);
        String fileName = generateUniqueFileName(imageFile.getOriginalFilename());
        String url = s3FileManager.uploadCompanyLogoImage(fileName, imageFile);
        String original_logo = company.getCompanyLogoUrl();
        deleteOriginalCompanyImage(original_logo);
        companyService.updateCompanyLogo(url, company);
        return url;
    }

    @SneakyThrows
    public String uploadProductImage(MultipartFile imageFile, String oldUrl) {
        String fileName = generateUniqueFileName(imageFile.getOriginalFilename());
        String url = s3FileManager.uploadProductImageUrl(fileName, imageFile);
        deleteOriginalProductImage(oldUrl);
        return url;
    }

    public void deleteOriginalProductImage(String original_logo) {
        if (original_logo != null && !original_logo.isEmpty()) {
            String keyName = original_logo.substring(original_logo.lastIndexOf("/") + 1);
            s3FileManager.deleteProductImages(keyName);
        }
    }

    private void deleteOriginalCompanyImage(String original_logo) {
        if (original_logo != null && !original_logo.isEmpty()) {
            String keyName = original_logo.substring(original_logo.lastIndexOf("/") + 1);
            s3FileManager.deleteCompanyLogos(keyName);
        }
    }

    private String generateUniqueFileName(String originalFileName) {
        String uniqueId = UUID.randomUUID().toString();
        String fileExtension = StringUtils.getFilenameExtension(originalFileName);
        return uniqueId + "." + fileExtension;
    }


}
