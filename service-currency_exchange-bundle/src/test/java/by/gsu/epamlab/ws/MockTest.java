package by.gsu.epamlab.ws;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class MockTest {
    final ConverterImpl testConverter = mock(ConverterImpl.class);
    final String currencyRUB = CurrencyLocal.RUB.name();
    final String currencyUSD = CurrencyLocal.USD.name();
    final String currencyEUR = CurrencyLocal.EUR.name();
    final String notRealizeCurrency = "BYN";
    final RuntimeException exceptionNotCorrectCurrency = new RuntimeException("\n\nUnrealized currency, only USD, EUR and RUB\n\n");
    final double conversionRateRUBtoUSD = new ConverterImpl().conversionRate(currencyRUB, currencyUSD);
    final double conversionRateEURtoUSD = new ConverterImpl().conversionRate(currencyEUR, currencyUSD);
    final double conversionRateRUBtoEUR = new ConverterImpl().conversionRate(currencyRUB, currencyEUR);

    @Test
    public void conversionRate_compareResult_SameResult() {
        when(testConverter.conversionRate(currencyRUB, currencyUSD)).thenReturn(conversionRateRUBtoUSD);
        when(testConverter.conversionRate(currencyEUR, currencyUSD)).thenReturn(conversionRateEURtoUSD);
        when(testConverter.conversionRate(currencyRUB, currencyEUR)).thenReturn(conversionRateRUBtoUSD);
    }

    @Test
    public void conversionRate_compareResult_EqualResult() {
        doReturn(testConverter.conversionRate(currencyRUB, currencyEUR)).when(testConverter).conversionRate(currencyRUB, currencyEUR);
        doReturn(testConverter.conversionRate(currencyUSD, currencyEUR)).when(testConverter).conversionRate(currencyUSD, currencyEUR);
        doReturn(testConverter.conversionRate(currencyUSD, currencyRUB)).when(testConverter).conversionRate(currencyUSD, currencyRUB);
    }

    @Test
    public void conversionRate_ExpectedException_ThrowException() {
        doThrow(exceptionNotCorrectCurrency).when(testConverter).conversionRate(currencyEUR, notRealizeCurrency);
        doThrow(exceptionNotCorrectCurrency).when(testConverter).conversionRate(notRealizeCurrency, currencyRUB);
        doThrow(exceptionNotCorrectCurrency).when(testConverter).conversionRate(notRealizeCurrency, currencyUSD);
    }

}
