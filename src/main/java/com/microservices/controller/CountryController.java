package com.microservices.controller;

import com.microservices.cacheAndJPArepo.CountryCacheMaintain;
import com.microservices.cacheAndJPArepo.CountryRepository;
import com.microservices.entities.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CountryController {
    @Autowired
    CountryRepository countryRepository;

    //save country operation
    @PostMapping("addCountry")
    public ResponseEntity<String> saveEmployee(@RequestBody Country country) {
        countryRepository.save(country);
        CountryCacheMaintain.countryCache.put(country.getCid(), country);
        return new ResponseEntity<String>("Country Save Successfully..", HttpStatus.CREATED);
    }

    //update country operation
    @PutMapping("updateCountry")
    public ResponseEntity<String> updateEmployee(@RequestBody Country country) {

        Optional<Country> oldCid = Optional.ofNullable(CountryCacheMaintain.countryCache.get(country.getCid()));
        if (oldCid.isPresent()) {
            countryRepository.save(country);
            CountryCacheMaintain.countryCache.put(country.getCid(), country);
            return new ResponseEntity<String>("CountryId " + country.getCid() + " Update Successfully..", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("CountryId " + country.getCid() + " Not Present..", HttpStatus.OK);
    }

    //getAll operation
    @GetMapping("getAllCountries")
    public ResponseEntity<List<Country>> getAllCountries() {
        return new ResponseEntity<List<Country>>(CountryCacheMaintain.countryCache
                .values().stream().collect(Collectors.toList()),HttpStatus.OK);
    }
    //get countryById operation
    @GetMapping("getCountryById/{id}")
    public ResponseEntity<?> getCountryById(@PathVariable Integer id) {

        Optional<Country> country = Optional.ofNullable(CountryCacheMaintain.countryCache.get(id));
        if (country.isPresent()) {
            return new ResponseEntity<Optional>(country, HttpStatus.OK);
        }
        return new ResponseEntity<String>("CountryId " + id + " Not Present..", HttpStatus.OK);
    }

    //delete countryById operation
    @DeleteMapping("deleteCountryById/{id}")
    public ResponseEntity<?> deleteCountryById(@PathVariable Integer id) {

        Optional<Country> country = Optional.ofNullable(CountryCacheMaintain.countryCache.get(id));
        if (country.isPresent()) {
            countryRepository.deleteById(id);
            CountryCacheMaintain.countryCache.remove(id);
            return new ResponseEntity<String>("CountryId " + id + " Delete Successfully..", HttpStatus.OK);
        }
        return new ResponseEntity<String>("CountryId " + id + " Not Present..", HttpStatus.OK);
    }
}





//update

//        Optional<Country> oldCountry = countryRepository.findById(country.getCid());
//        if (oldCountry.isPresent()) {
//            System.out.println("hello db..");
//            countryRepository.save(country);
//            CountryCacheMaintain.countryCache.put(country.getCid(), country);
//            return new ResponseEntity<String>("CountryId " + country.getCid() + " Update Successfully..", HttpStatus.OK);
//        }


//country by id


//        Optional<Country> countryFromDB = countryRepository.findById(id);
//        if (countryFromDB.isPresent()) {
//            System.out.println("hello getDB..");
//            return new ResponseEntity<Optional>(countryFromDB, HttpStatus.OK);
//        }