package com.ts.vendor.Controller;

import com.netflix.ribbon.proxy.annotation.Http;
import com.ts.vendor.Client.CategoryClient;
import com.ts.vendor.Model.Category;
import com.ts.vendor.Model.Vendor;
import com.ts.vendor.Repository.VendorRepository;
import com.ts.vendor.Service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class VendorController {
    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private CategoryClient categoryClient;

    // TESTING PURPOSES
    @GetMapping(value = "/categories")
    Iterable<Category> getAllCategories() {
        // retrieves all categories from the category microservice using feign

        return categoryClient.getCategoryList();
    }

    //    Method used for the global search to find categories by name
    @GetMapping(value = "/allByLen")
    public List<Vendor> getAllCategoriesByLen(int size, String search){

        return vendorService.findNameBySearchTerm(search, new PageRequest(0, size)).getContent();
    }

    @GetMapping(value = "/page")
    public Page<Vendor> findBySearchTerm(@RequestParam("search") String searchTerm, Pageable pageable) {

        Page <Vendor> vendorPage = vendorService.findBySearchTerm(searchTerm, pageable);

        return vendorPage;
    }

    @PostMapping(value = "/add", headers = "Content-Type=application/json")
    public ResponseEntity addNewVendor (@RequestBody Vendor vendor) {

        vendorRepository.save(vendor);

        return new ResponseEntity<>(vendor, HttpStatus.CREATED);
    }



    @PostMapping(path="/add")
    public ResponseEntity addNewVendor (@RequestParam String name, @RequestParam String about, @RequestParam String email, @RequestParam String webpage,
                                        @RequestParam String contact, @RequestParam String address, @RequestParam String portfolioURL) {

        Vendor vendor = new Vendor();
        vendor.setName(name);
        vendor.setAbout(about);
        vendor.setEmail(email);
        vendor.setWebpage(webpage);
        vendor.setContact(contact);
        vendor.setAddress(address);
        vendor.setPortfolioURL(portfolioURL);
        vendorRepository.save(vendor);

        return new ResponseEntity<>(vendor, HttpStatus.CREATED);
    }

    @PutMapping(path="/edit/{id}")
    public ResponseEntity editVendor(@PathVariable long id, @RequestBody Vendor vendor) {

        Optional<Vendor> vendorOptional = vendorRepository.findById(id);

        vendor.setId(id);

        vendorRepository.save(vendor);

        return new ResponseEntity<>("Vendor @{" + id + "} updated successfully!", HttpStatus.OK);
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<Iterable<Vendor>> getAllUsers() {
        Iterable<Vendor> categories = vendorRepository.findAll();
        // This returns a JSON or XML with the users

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteVendor(@PathVariable long id) {
        vendorRepository.deleteById(id);

        return new ResponseEntity<>("Deleted vendor@{" + id + "} successfully", HttpStatus.OK);
    }
}