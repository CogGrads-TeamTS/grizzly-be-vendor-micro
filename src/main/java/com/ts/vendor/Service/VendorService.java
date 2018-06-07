package com.ts.vendor.Service;

import com.ts.vendor.Model.Vendor;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@FeignClient(name="vendor-service")
public interface VendorService {

    Page<Vendor> findBySearchTerm(String searchTerm, Pageable pageable);
}
