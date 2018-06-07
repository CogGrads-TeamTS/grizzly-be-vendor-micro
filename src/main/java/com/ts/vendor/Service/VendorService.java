package com.ts.vendor.Service;

import com.ts.vendor.Model.Vendor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="vendor-service")
public interface VendorService {
    @GetMapping(value = "/page")
    Page<Vendor> findBySearchTerm(@RequestParam("search") String searchTerm, Pageable pageable);
}
