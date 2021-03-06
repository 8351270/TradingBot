package com.tradingbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.ShortBufferException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class TradingBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradingBotApplication.class, args);
	}
}
