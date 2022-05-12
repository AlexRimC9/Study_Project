package com.example.study_project.Service.Currencies;

import com.example.study_project.Rep.CurrenciesRep;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.study_project.Entity.Currencies;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Configuration
@EnableScheduling
@Slf4j
@Service
public class CurrenciesService {

    private final CurrenciesRep currenciesRep;

    public CurrenciesService(CurrenciesRep currenciesRep) {
        this.currenciesRep = currenciesRep;
    }


    public void additionalInformation() {
        Currencies currencies = new Currencies();
        currencies.setName("RUB/JPY");
        currencies.setBid(currenciesRep.findByName("USD/RUB").getBid() / currenciesRep.findByName("USD/JPY").getBid());
        Date date = new Date();
        currencies.setDateOfOperation(date.toString().substring(4));
        currenciesRep.save(currencies);
        currencies.setName("BTC/RUB");
        currencies.setBid(currenciesRep.findByName("USD/RUB").getBid() * currenciesRep.findByName("BTC/USD").getBid());
        currencies.setDateOfOperation(date.toString().substring(4));
        currenciesRep.save(currencies);
        currencies.setName("USD/EUR");
        currencies.setBid(currenciesRep.findByName("USD/RUB").getBid() / currenciesRep.findByName("EUR/RUB").getBid());
        currencies.setDateOfOperation(date.toString().substring(4));
        currenciesRep.save(currencies);
    }
    @Scheduled(fixedRate = 1000*30)
    public void gettingInformation() throws IOException {
        Document doc = Jsoup.connect("https://ru.investing.com/currencies/live-currency-cross-rates").get();
        Elements element = doc.body().getElementsByClass("liveCurrencyBox");
        for (int i = 0; i < element.size(); i++) {
            String name = element.get(i).getElementsByAttribute("title").get(0).text();
            if (name.equals("USD/RUB") || name.equals("EUR/RUB")
                    || name.equals("USD/JPY") || name.equals("BTC/USD")) {
                Currencies currencies = new Currencies();
                currencies.setName(name);
                String elementsBid = element.get(i).getElementsByClass("innerContainerWrap first").text().substring(4);
                elementsBid = elementsBid.replace(".", "");
                elementsBid = elementsBid.replace(",", ".");
                double value = Double.parseDouble(elementsBid);
                currencies.setBid(value);
                Date date = new Date();
                currencies.setDateOfOperation(date.toString().substring(4));
                currenciesRep.save(currencies);
            }
        }
        additionalInformation();
    }

    public String conversion(String entranceCurrency, String outputCurrency , double total) {
        String name = entranceCurrency + "/" + outputCurrency;
        DecimalFormat df = new DecimalFormat("#.##");
        if (currenciesRep.findByName(name) != null) {
            double currencies = currenciesRep.findByName(name).getBid() * total;
            log.info("За {} {} вы приобрете {} {}",total,entranceCurrency,outputCurrency, df.format(currencies));
            return df.format(currencies);
        }else if(currenciesRep.findByName(outputCurrency + "/" + entranceCurrency) != null){
            double currencies = 1 / currenciesRep.findByName(outputCurrency + "/" + entranceCurrency).getBid() * total;
            log.info("За {} {} вы приобрете {} {}",total,entranceCurrency,outputCurrency, df.format(currencies));
            return df.format(currencies);
        }else {
            return "Данных по отношениям этих валют нет в базе";
        }
    }
    public List<?> course(){
        return currenciesRep.findAll();
    }
}
















