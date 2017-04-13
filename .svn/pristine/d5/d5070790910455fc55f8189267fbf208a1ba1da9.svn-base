package com.tencent.common;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.tencent.service.IServiceRequest;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 14:36
 */
public class HttpsRequest implements IServiceRequest{

    public interface ResultListener {


        public void onConnectionPoolTimeoutError();

    }

 //   private static Log log = new Log(LoggerFactory.getLogger(HttpsRequest.class));

    //表示请求器是否已经做了初始化工作
    private boolean hasInit = false;
    private Log logger = LogFactory.getLog(this.getClass()); 
    //连接超时时间，默认10秒
    private int socketTimeout = 10000;

    //传输超时时间，默认30秒
    private int connectTimeout = 30000;

    //请求器的配置
    private RequestConfig requestConfig;

    //HTTP请求器
    private CloseableHttpClient httpClient;

    public HttpsRequest() throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
        init();
    }

   /* private void init() throws IOException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException {

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(Configure.getCertLocalPath()));//加载本地的证书进行https加密传输
        try {
            keyStore.load(instream, Configure.getCertPassword().toCharArray());//设置证书密码
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, Configure.getCertPassword().toCharArray()).build();
        // Allow TLSv1 protocol only
        try {
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"},null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

       
			httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //根据默认超时限制初始化requestConfig
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();

        hasInit = true;
    }
    
*/
    public void init() { 
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }}).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory( sslContext);
            httpClient =HttpClients.custom().setSSLSocketFactory(sslsf).build();
            hasInit = true;
           // return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
      //  httpClient =HttpClients.createDefault();
       // return  HttpClients.createDefault();
    }
    /**
     * 通过Https往API post xml数据
     *
     * @param url    API地址 微信统一下单接口
     * @param xmlObj 要提交的XML数据对象
     * @return API回包的实际数据
     * @throws IOException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */

    public String sendPost(String url, Object xmlObj) throws IOException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException {
    	logger.info("进入https sendPost->url="+url+" \n   xmlObj="+xmlObj);
        if (!hasInit) {
            init();
        }

        String result = null;

        HttpPost httpPost = new HttpPost(url);

        //解决XStream对出现双下划线的bug
        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
        logger.info("API，POST数据检测：");
        //将要提交给API的数据对象转换成XML格式数据Post给API
        String postDataXML = xStreamForRequestPostData.toXML(xmlObj);
        logger.info("API，POST过去的数据是：");
      
        logger.info(postDataXML);

        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);

        //设置请求器的配置
        httpPost.setConfig(requestConfig);

        logger.info("executing request" + httpPost.getRequestLine());

        try {
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, "UTF-8");
            logger.info("返回结果 : result:"+result);
            int i=0;

        } /*catch (ConnectionPoolTimeoutException e) {
            log.e("http get throw ConnectionPoolTimeoutException(wait time out)");

        } catch (ConnectTimeoutException e) {
            log.e("http get throw ConnectTimeoutException");

        } catch (SocketTimeoutException e) {
            log.e("http get throw SocketTimeoutException");

        } catch (Exception e) {
            log.e("http get throw Exception");

        }*/ finally {
            httpPost.abort();
        }

        return result;
    }
    //https公用接口，所有https可以从这里调用 
    public String sendHttpsWapPost(String url, Object xmlObj)  {
    	logger.info("进入https sendHttpsWapPost->url="+url+" \n   xmlObj="+xmlObj);
        if (!hasInit) {
            init();
        }

        String result = null;
        logger.info("API，POST数据检测1：");
        HttpPost httpPost = new HttpPost(url);
        logger.info("API，POST数据检测2：");
        //解决XStream对出现双下划线的bug
        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
        logger.info("API，POST数据检测3：");
        //将要提交给API的数据对象转换成XML格式数据Post给API
        String postDataXML = xStreamForRequestPostData.toXML(xmlObj);
        logger.info("API，POST过去的数据是：");
      
        logger.info(postDataXML);

        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);

        //设置请求器的配置
        httpPost.setConfig(requestConfig);

        logger.info("executing request" + httpPost.getRequestLine());

        try {
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, "UTF-8");
            logger.info("返回结果 : result:"+result);
            int i=0;

        } catch (Exception e) {
        	logger.info("http get throw ConnectionPoolTimeoutException(wait time out)");

        } /*catch (ConnectTimeoutException e) {
            log.e("http get throw ConnectTimeoutException");

        } catch (SocketTimeoutException e) {
            log.e("http get throw SocketTimeoutException");

        } catch (Exception e) {
            log.e("http get throw Exception");

        }*/ finally {
            httpPost.abort();
        }

        return result;
    }

    /**
     * 设置连接超时时间
     *
     * @param socketTimeout 连接时长，默认10秒
     */
    public void setSocketTimeout(int socketTimeout) {
        socketTimeout = socketTimeout;
        resetRequestConfig();
    }

    /**
     * 设置传输超时时间
     *
     * @param connectTimeout 传输时长，默认30秒
     */
    public void setConnectTimeout(int connectTimeout) {
        connectTimeout = connectTimeout;
        resetRequestConfig();
    }

    private void resetRequestConfig(){
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
    }

    /**
     * 允许商户自己做更高级更复杂的请求器配置
     *
     * @param requestConfig 设置HttpsRequest的请求器配置
     */
    public void setRequestConfig(RequestConfig requestConfig) {
        requestConfig = requestConfig;
    }
}
