package com.currency.converter.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.currency.converter.api.CurrencyRatesInMemoryCache;
import com.currency.converter.model.ExchangeQuery;
import com.currency.converter.model.ExchangeQueryForm;
import com.currency.converter.model.ExchangeRate;
import com.currency.converter.security.CustomUserDetails;
import com.currency.converter.service.ExchangeQueryService;

@Controller
public class ExchangeQueryController {

	private final Logger logger = LoggerFactory.getLogger( ExchangeQueryController.class );

	private ExchangeQueryService exchangeQueryService;
	private ExchangeQueryForm exchangeQueryForm;

	private final static long crunchifyTimeToLive = 200;
	private final static long crunchifyTimerInterval = 500;
	private final static CurrencyRatesInMemoryCache< String, HashMap< String, Double > > cache = new CurrencyRatesInMemoryCache< String, HashMap< String, Double > >(
			crunchifyTimeToLive, crunchifyTimerInterval );

	@Autowired
	public void setExchangeQueryService( ExchangeQueryService exchangeQueryService ) {
		this.exchangeQueryService = exchangeQueryService;
	}

	@Autowired
	public void setExchangeQueryForm( ExchangeQueryForm exchangeQueryForm ) {
		this.exchangeQueryForm = exchangeQueryForm;
	}

	// list page queries
	@RequestMapping( value = "/currency_exchange/list", method = RequestMethod.GET )
	public String showList( Model model ) {
		logger.debug( "showLastQueries()" );
		CustomUserDetails customUserDetails = ( CustomUserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute( "list", exchangeQueryService.findAll( customUserDetails.getId() ) );
		return "currency_exchange/list";

	}

	// delete query
	@RequestMapping( value = "/currency_exchange/{id}/delete", method = RequestMethod.POST )
	public String deleteQuery( @PathVariable( "id" ) int id, final RedirectAttributes redirectAttributes ) {

		logger.debug( "deleteQuery() : {}", id );

		exchangeQueryService.delete( id );

		redirectAttributes.addFlashAttribute( "css", "success" );
		redirectAttributes.addFlashAttribute( "msg", "User is deleted!" );

		return "redirect:/currency_exchange/list";
	}

	// add query
	@RequestMapping( value = "/currency_exchange/new", method = RequestMethod.GET )
	public String showNew( Model model ) {
		logger.debug( "showNew()" );

		Double rate = cache.get( this.exchangeQueryForm.getOriginCurrency() ).get(
				this.exchangeQueryForm.getOriginCurrency().concat( this.exchangeQueryForm.getDestinationCurrency() ) );

		ExchangeQuery exchangeQuery = new ExchangeQuery();

		exchangeQuery.setOriginCurrency( this.exchangeQueryForm.getOriginCurrency() );
		exchangeQuery.setDestinationCurrency( this.exchangeQueryForm.getDestinationCurrency() );
		exchangeQuery.setQuantityOrigin( this.exchangeQueryForm.getQuantityOrigin() );
		exchangeQuery.setExchangeRate( this.exchangeQueryForm.getQuantityOrigin() * rate );

		model.addAttribute( "queryForm", exchangeQuery );
		model.addAttribute( "rate", rate );

		populateDefaultModel( model );

		return "currency_exchange/new";
	}

	// add query
	@RequestMapping( value = "/currency_exchange/new", method = RequestMethod.POST )
	public String showNew( @ModelAttribute( "queryForm" ) ExchangeQuery exchangeQuery, @RequestParam( required = false, value = "add" ) String add,
			Model model, final RedirectAttributes redirectAttributes ) {
		logger.debug( "showAddQueryForm()" );

		this.exchangeQueryForm.setOriginCurrency( exchangeQuery.getOriginCurrency() );
		this.exchangeQueryForm.setDestinationCurrency( exchangeQuery.getDestinationCurrency() );
		this.exchangeQueryForm.setQuantityOrigin( exchangeQuery.getQuantityOrigin() );
		this.exchangeQueryForm.setExchangeRate( exchangeQuery.getExchangeRate() );
		exchangeQuery.setUserId( ( ( CustomUserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ).getId() );

		if ( add != null ) {
			redirectAttributes.addFlashAttribute( "css", "success" );
			redirectAttributes.addFlashAttribute( "msg", "Added!!" );

			exchangeQueryService.save( exchangeQuery );
		}

		return "redirect:/currency_exchange/new";
	}

	// current currency exchange
	@RequestMapping( value = "/currency_exchange/current", method = RequestMethod.GET )
	public String currentExchangeRates( Model model ) {
		logger.debug( "currentExchangeRates()" );

		List< ExchangeRate > exchangeRateList = new ArrayList< ExchangeRate >();

		HashMap< String, Double > cacheContentHashMap = cache.get( "USD" );
		for ( Map.Entry< String, Double > entry : cacheContentHashMap.entrySet() ) {
			if ( !entry.getKey().substring( 3 ).equals( "USD" ) ) {
				ExchangeRate exchangeRate = new ExchangeRate( "USD", entry.getKey().substring( 3 ), entry.getValue() );
				exchangeRateList.add( exchangeRate );
			}
		}

		model.addAttribute( "current", exchangeRateList );
		
		populateDefaultModel( model );

		return "currency_exchange/current";
	}

	private void populateDefaultModel( Model model ) {
		Map< String, String > originCurrencyList = new LinkedHashMap< String, String >();
		originCurrencyList.put( "USD", "USD" );
		// originCurrencyList.put( "EUR", "EUR" );
		// originCurrencyList.put( "GBP", "GBP" );
		// originCurrencyList.put( "NZD", "NZD" );
		// originCurrencyList.put( "AUD", "AUD" );
		// originCurrencyList.put( "HUF", "HUF" );
		model.addAttribute( "originCurrencyList", originCurrencyList );

		Map< String, String > destinationCurrenyList = new LinkedHashMap< String, String >();
		destinationCurrenyList.put( "EUR", "EUR" );
		destinationCurrenyList.put( "GBP", "GBP" );
		destinationCurrenyList.put( "NZD", "NZD" );
		destinationCurrenyList.put( "AUD", "AUD" );
		destinationCurrenyList.put( "HUF", "HUF" );
		model.addAttribute( "destinationCurrenyList", destinationCurrenyList );
	}
}