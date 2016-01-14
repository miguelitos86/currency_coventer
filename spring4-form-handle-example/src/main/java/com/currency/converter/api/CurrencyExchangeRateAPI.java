/**
 * 
 */
package com.currency.converter.api;

import java.util.HashMap;

/**
 * @author Miguel del Prado Aranda
 * @email m.delpradoaranda@gmail.com
 * @date 9/1/2016
 */

public class CurrencyExchangeRateAPI {

	private String source;
	private HashMap< String, Double > quotes;

	public String getSource() {
		return source;
	}

	public void setSource( String source ) {
		this.source = source;
	}

	public HashMap< String, Double > getQuotes() {
		return quotes;
	}

	public void setQuotes( HashMap< String, Double > quotes ) {
		this.quotes = quotes;
	}
}
