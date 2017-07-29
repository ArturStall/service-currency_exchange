package by.gsu.epamlab.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import net.webservicex.Currency;
import net.webservicex.CurrencyConvertor;
import net.webservicex.CurrencyConvertorSoap;

@WebService(endpointInterface = "by.gsu.epamlab.ws.Converter")
public class ConverterImpl implements Converter {

    public ConverterImpl() {
        super();
    }

    @Override
    @WebMethod(operationName = "ConversionCurrencyRate")
    public double conversionRate(@WebParam(name = "fromCurrency") String fromCurrency, @WebParam(name = "toCurrency") String toCurrency) {
        CurrencyConvertor convertor = new CurrencyConvertor();
        CurrencyConvertorSoap service = convertor.getCurrencyConvertorSoap();
        CurrencyLocal fromLocal;
        CurrencyLocal toLocal;

        try {
            fromLocal = CurrencyLocal.valueOf(fromCurrency);
            toLocal = CurrencyLocal.valueOf(toCurrency);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("\n\nUnrealized currency, only USD, EUR and RUB\n\n");
        }

        Currency from = Currency.valueOf(fromLocal.toString());
        Currency to = Currency.valueOf(toLocal.toString());

        return service.conversionRate(from, to);
    }

}
