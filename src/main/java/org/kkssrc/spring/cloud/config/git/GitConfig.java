package org.kkssrc.spring.cloud.config.git;

import org.kkssrc.spring.cloud.config.util.InstallCert;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import java.net.InetAddress;
import java.net.MalformedURLException;

@Component
public class GitConfig implements ApplicationListener<ApplicationPreparedEvent>{

	private static boolean init = false;
	

	@Override
	public void onApplicationEvent(ApplicationPreparedEvent event) {
		if(!init){
			String uristring  = event.getApplicationContext().getEnvironment().getProperty("spring.cloud.config.server.git.uri");
			if(uristring != null){
				System.out.println("Git Client Configuration Init");
				try {
					URL uri = new URL(uristring);
					String[] args = {uri.getHost() + (uri.getPort() == -1 ? "": ":"+uri.getPort())};
					InetAddress add = InetAddress.getByName(uri.getHost());
					HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> hostname.equals(add.getHostAddress()));
					InstallCert.main(args);
					init = true;
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
