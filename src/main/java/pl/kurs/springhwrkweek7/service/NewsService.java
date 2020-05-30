package pl.kurs.springhwrkweek7.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.kurs.springhwrkweek7.model.news.News;

@Service
public  class NewsService {
    @Value("${apiKey}")
    private String apiKey;
    @Value("${apiBaseUrl}")
    private String baseUrl;
    @Value("${apiCountry}")
    private String country;


    public News getNewsFromApi() {

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(getApiUrl(), News.class);
    }

    public String getApiUrl() {
        StringBuilder address = new StringBuilder(baseUrl);
        address.append("/v2/top-headlines?country=");
        address.append(country);
        address.append("&apiKey=");
        address.append(apiKey);
        System.out.println(address.toString());
        return address.toString();

    }
}
