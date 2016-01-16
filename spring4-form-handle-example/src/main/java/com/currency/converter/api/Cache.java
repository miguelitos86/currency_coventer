package com.currency.converter.api;

import java.util.ArrayList;

import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.map.LRUMap;

/**
 * @author Miguel del Prado Aranda
 * @email m.delpradoaranda@gmail.com
 * @date 9/1/2016
 */

public class Cache< K, V > {

	private long timeToLive;
	private LRUMap cache;
	private CurrencyRatesAPI response = new CurrencyRatesAPI();

	protected class CacheObject {
		public long lastAccessed = System.currentTimeMillis();
		public V value;

		protected CacheObject( V value ) {
			this.value = value;
		}
	}

	public Cache( long crunchifyTimeToLive, final long crunchifyTimerInterval ) {
		this.timeToLive = crunchifyTimeToLive * 1000;

		cache = new LRUMap();

		if ( timeToLive > 0 && crunchifyTimerInterval > 0 ) {

			Thread t = new Thread( new Runnable() {
				public void run() {
					while ( true ) {
						try {
							Thread.sleep( crunchifyTimerInterval * 1000 );
						} catch ( InterruptedException ex ) {
						}
						cleanup();
					}
				}
			} );
			t.setDaemon( true );
			t.start();
		}
	}

	public void put( K key, V value ) {
		synchronized (cache) {
			cache.put( key, new CacheObject( value ) );
		}
	}

	@SuppressWarnings( "unchecked" )
	public V get( K key ) {
		synchronized (cache) {
			CacheObject c = ( CacheObject ) cache.get( key );

			if ( c == null ) {
				CurrencyExchangeRateAPI currencyExchangeRate = response.sendRequest( ( String ) key );

				put( ( K ) key, ( V ) currencyExchangeRate.getQuotes() );

				c = ( CacheObject ) cache.get( key );
			}
			c.lastAccessed = System.currentTimeMillis();

			return c.value;
		}
	}

	public void remove( K key ) {
		synchronized (cache) {
			cache.remove( key );
		}
	}

	public int size() {
		synchronized (cache) {
			return cache.size();
		}
	}

	@SuppressWarnings( "unchecked" )
	public void cleanup() {

		long now = System.currentTimeMillis();
		ArrayList< K > deleteKey = null;

		synchronized (cache) {
			MapIterator itr = cache.mapIterator();

			deleteKey = new ArrayList< K >( ( cache.size() / 2 ) + 1 );
			K key = null;
			CacheObject c = null;

			while ( itr.hasNext() ) {
				key = ( K ) itr.next();
				c = ( CacheObject ) itr.getValue();

				if ( c != null && ( now > ( timeToLive + c.lastAccessed ) ) ) {
					deleteKey.add( key );
				}
			}
		}

		for ( K key : deleteKey ) {
			synchronized (cache) {
				cache.remove( key );
			}
			Thread.yield();
		}
	}
}
