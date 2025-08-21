package com.simpleexchange.services;

import com.simpleexchange.models.ExchangeOffice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeOfficeService {
    private final List<ExchangeOffice> offices = new ArrayList<>();

    public ExchangeOfficeService() {
        ExchangeOffice o1 = new ExchangeOffice();
        o1.setId(1L);
        o1.setName("Menjačnica 1");
        o1.setAddress("Adresa 1, Niš");
        o1.setDefault(true);

        ExchangeOffice o2 = new ExchangeOffice();
        o2.setId(2L);
        o2.setName("Menjačnica 2");
        o2.setAddress("Adresa 2, Niš");
        o2.setDefault(false);

        offices.add(o1);
        offices.add(o2);
    }

    public List<ExchangeOffice> getAllOffices() {
        return offices;
    }

    public Optional<ExchangeOffice> getById(Long id) {
        return offices.stream().filter(o -> o.getId().equals(id)).findFirst();
    }

    public void setDefaultOffice(Long id) {
        offices.forEach(o -> o.setDefault(o.getId().equals(id)));
    }

    public Optional<ExchangeOffice> getDefaultOffice() {
        return offices.stream().filter(ExchangeOffice::isDefault).findFirst();
    }
}
