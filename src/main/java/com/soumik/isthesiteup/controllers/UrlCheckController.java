package com.soumik.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {

    private final String SITE_IS_UP = "Site is up";
    private final String SITE_IS_DOWN = "Site is Down";
    private final String INCORRECT_URL = "URL is incorrect";

    @GetMapping("/check")
    public String getUrlStatusMsg(@RequestParam String url)
    {
        String returnMessage = "";

        try {
            URL urlObj = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlObj.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            int responsecode = httpURLConnection.getResponseCode() /100;
            if(responsecode != 2 || responsecode != 3)
            {
                returnMessage = SITE_IS_DOWN;
            }
            else{
                returnMessage = SITE_IS_UP;
            }
        } catch (MalformedURLException e) {
            returnMessage = INCORRECT_URL;
        } catch (IOException e) {
            returnMessage = SITE_IS_DOWN;
        }
        return returnMessage;
    }

    @GetMapping("/healthcheck")
    public String getHealthCheckMsg()
    {
        String returnMessage = "I am alive";

       
        return returnMessage;
    }
}
