package com.ts.vendor.Client;

import com.ts.vendor.Model.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
@FeignClient(name = "category-service")
public interface CategoryClient {
    @GetMapping(value = "categorylist")
    Iterable<Category> getCategoryList();
}
