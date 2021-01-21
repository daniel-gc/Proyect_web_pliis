package mx.pliis.afiliacion;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import mx.openpay.client.core.OpenpayAPI;

@SpringBootApplication
public class AfiliacionApplication {
	
	@Value("${openpay.url.api:}")
	private String url;
	
	@Value("${openpay.merchant:}")
	private String merchant;
	
	@Value("${openpay.private.key:}")
	private String key;

	public static void main(String[] args) {
		SpringApplication.run(AfiliacionApplication.class, args);
	}
	
	@Bean
	public OpenpayAPI openpayAPI() {
		if(StringUtils.isBlank(url) || StringUtils.isBlank(key) || StringUtils.isBlank(merchant) ) {
			throw new BeanCreationException("OpenpayAPI", "Failed to create a OpenpayAPI");
		}
		return new OpenpayAPI(url, key, merchant);
	}

}
