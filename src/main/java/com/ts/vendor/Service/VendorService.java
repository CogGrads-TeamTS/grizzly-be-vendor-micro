package com.ts.vendor.Service;

import com.ts.vendor.Model.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;


public interface VendorService {
    Page<Vendor> findBySearchTerm(@RequestParam("search") String searchTerm, Pageable pageable);
    Page<Vendor> findNameBySearchTerm(String searchTerm, Pageable pageable);
}
