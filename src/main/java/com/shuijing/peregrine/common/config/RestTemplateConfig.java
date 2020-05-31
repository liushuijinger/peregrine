package com.shuijing.peregrine.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2020/05/27
 */
@Configuration
public class RestTemplateConfig {


	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory)
					throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
//		SSLContext sslContext = SSLContexts.custom()
//						.loadTrustMaterial(null, acceptingTrustStrategy)
//						.build();
//
//		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
//
//		CloseableHttpClient httpClient = HttpClients.custom()
//						.setSSLSocketFactory(csf)
//						.build();
//
//		HttpComponentsClientHttpRequestFactory requestFactory =
//						new HttpComponentsClientHttpRequestFactory();
//
//		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(factory);
		restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		return restTemplate;
	}

	@Bean
	public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(5000);
		factory.setConnectTimeout(15000);
		return factory;
	}


	/**
	 * 访问SSL的Template
	 *
	 * @throws Exception
	 */
//	@Bean("sslRestTemplate")
//	public RestTemplate tmsRestTemplate() throws Exception {
//		//新建RestTemplate对象
//		RestTemplate restTemplate = new RestTemplate();
//		//判断证书文件地址是否存在
//		if (StringUtils.isNotEmpty(sslProperties.getKeyfile())) {
//			//在握手期间，如果URL的主机名和服务器的标识主机名不匹配，则验证机制可以回调此接口的实现 程序来确定是否应该允许此连接
//			HostnameVerifier hv = new HostnameVerifier() {
//				@Override
//				public boolean verify(String urlHostName, SSLSession session) {
//					return true;
//				}
//			};
//			HttpsURLConnection.setDefaultHostnameVerifier(hv);
//			//构建SSL-Socket链接工厂
//			SSLConnectionSocketFactory ssLSocketFactory = buildSSLSocketFactory("PKCS12",
//			                                                                    sslProperties.getKeyfile(), sslProperties.getPassword(),
//			                                                                    Lists.newArrayList("TLSv1"), true);
//			//Spring提供HttpComponentsClientHttpRequestFactory指定使用HttpClient作为底层实现创建 HTTP请求
//			HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(
//							HttpClients.custom().setSSLSocketFactory(ssLSocketFactory).build()
//			);
//			//设置传递数据超时时长
//			httpRequestFactory.setReadTimeout(sslProperties.getTimeout());
//			//设置建立连接超时时长
//			httpRequestFactory.setConnectTimeout(sslProperties.getTimeout());
//			//设置获取连接超时时长
//			httpRequestFactory.setConnectionRequestTimeout(tmsProperties.getTimeout());
//
//			restTemplate.setRequestFactory(httpRequestFactory);
//
//			// 返回消息头也是text_html,消息格式是XML,添加新的message converter
//			Jaxb2RootElementHttpMessageConverter messageConverter = new Jaxb2RootElementHttpMessageConverter();
//			//设置message Converter支持的媒体类型
//			List<MediaType> finalMediaTypes = new ArrayList<>();
//			finalMediaTypes.addAll(messageConverter.getSupportedMediaTypes());
//			finalMediaTypes.add(MediaType.TEXT_HTML);
//			messageConverter.setSupportedMediaTypes(finalMediaTypes);
//			restTemplate.setMessageConverters(Lists.newArrayList(messageConverter));
//
//		}
//		return restTemplate;
//	}
//
//	/**
//	 * 构建SSLSocketFactory
//	 *
//	 * @param keyStoreType
//	 * @param keyFilePath
//	 * @param keyPassword
//	 * @param sslProtocols
//	 * @param auth 是否需要client默认相信不安全证书
//	 * @return
//	 * @throws Exception
//	 */
//	private SSLConnectionSocketFactory buildSSLSocketFactory(String keyStoreType, String keyFilePath,
//	                                                         String keyPassword, List<String> sslProtocols, boolean auth) throws Exception {
//		//证书管理器，指定证书及证书类型
//		KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//		//KeyStore用于存放证书，创建对象时 指定交换数字证书的加密标准
//		KeyStore keyStore = KeyStore.getInstance(keyStoreType);
//		InputStream inputStream = resourcePatternResolver.getResource(keyFilePath).getInputStream();
//		try {
//			//添加证书
//			keyStore.load(inputStream, keyPassword.toCharArray());
//		} finally {
//			inputStream.close();
//		}
//		keyManagerFactory.init(keyStore, keyPassword.toCharArray());
//
//		SSLContext sslContext = SSLContext.getInstance("SSL");
//		if (auth) {
//			// 设置信任证书（绕过TrustStore验证）
//			TrustManager[] trustAllCerts = new TrustManager[1];
//			TrustManager trustManager = new AuthX509TrustManager();
//			trustAllCerts[0] = trustManager;
//			sslContext.init(keyManagerFactory.getKeyManagers(), trustAllCerts, null);
//			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
//		} else {
//			//加载证书材料，构建sslContext
//			sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, keyPassword.toCharArray()).build();
//		}
//
//		SSLConnectionSocketFactory sslConnectionSocketFactory =
//						new SSLConnectionSocketFactory(sslContext, sslProtocols.toArray(new String[sslProtocols.size()]),
//						                               null,
//						                               new HostnameVerifier() {
//							                               // 这里不校验hostname
//							                               @Override
//							                               public boolean verify(String urlHostName, SSLSession session) {
//								                               return true;
//							                               }
//						                               });
//
//		return sslConnectionSocketFactory;
//	}
}
