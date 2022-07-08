package com.microservices.cacheAndJPArepo;

import com.microservices.entities.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Component
public class CountryCacheMaintain {
    @Autowired
    CountryRepository countryRepository;
    public static HashMap<Integer, Country> countryCache = new HashMap<>();
    public List<Country> countryList;

    @PostConstruct
    @Scheduled(cron = "0 0 */12 ? * *")//once in every 12hr
//    @Scheduled(cron = "0 */2 * ? * *")//once in 2 min
//    @Scheduled(cron = "* * * ? * *")//once in every sec
    public void loadCache() {
        System.out.println("countryCacheStart");
        countryList = countryRepository.findAll();
        if (!countryList.isEmpty()) {
            countryList.forEach(country -> countryCache.put(country.getCid(), country));
        }
        System.out.println("countryCacheEnd");
    }
}
