package by.gsu.epamlab.ws;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public interface Converter {

    @WebMethod
    double conversionRate(String fromCurrency, String toCurrency);

}
