package br.com.leonardo.resource;

import br.com.leonardo.model.LinkUrl;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@SpringBootApplication
@RestController
@CrossOrigin
@RequestMapping("/")
public class Resource {

    HashMap<String, String> urlList = new HashMap<>();

    @Value("${base_url}")
    String baseUrl;

    
    @RequestMapping(value = "/urlEncurtada", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Object> urlEncurtada(@RequestBody  LinkUrl link) {
        String charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String key = RandomStringUtils.random(4, charset.toCharArray());
        urlList.put(key, link.getUrl());
        String urlEncurtada = baseUrl + "/" + key;
        HashMap<String, String> response = new HashMap<>();
        response.put("url_encurtada", urlEncurtada);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{key}")
    public ResponseEntity<Object> post(@PathVariable("key") String key) {
        HashMap<String, String> response = new HashMap<>();
        response.put("url_original", urlList.get(key));
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

}
