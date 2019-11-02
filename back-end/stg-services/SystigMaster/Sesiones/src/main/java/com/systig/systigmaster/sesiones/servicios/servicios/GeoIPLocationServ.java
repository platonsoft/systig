package com.systig.systigmaster.sesiones.servicios.servicios;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import com.systig.systigmaster.sesiones.repositorios.modelos.GeoIP;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Service
public class GeoIPLocationServ {
    private DatabaseReader dbReaderCity;
    private DatabaseReader dbReaderCountry;

    public GeoIPLocationServ() throws IOException {
        File database_city = new File("C:\\geoip_city.mmdb");
        File database_country = new File("C:\\geoip_country.mmdb");
        dbReaderCity = new DatabaseReader.Builder(database_city).build();
        dbReaderCountry = new DatabaseReader.Builder(database_country).build();
    }

    public GeoIP getLocation(String ip) throws IOException, GeoIp2Exception {

        InetAddress ipAddress = InetAddress.getByName(ip);

        CityResponse cityResponse = dbReaderCity.city(ipAddress);
        CountryResponse countryResponse = dbReaderCountry.country(ipAddress);

        GeoIP geoIP = new GeoIP();
        geoIP.setIpAddress(ip);
        geoIP.setCity(cityResponse.getCity().getName());
        geoIP.setCountry(countryResponse.getCountry().getName());
        geoIP.setLatitude(cityResponse.getLocation().getLatitude().toString());
        geoIP.setLongitude(cityResponse.getLocation().getLongitude().toString());
        return geoIP;
    }
}
