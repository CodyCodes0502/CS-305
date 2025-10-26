package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}

@RestController
class ServerController{
//FIXME:  Add hash function to return the checksum value for the data string that should contain your name.    
    @RequestMapping("/hash")
    public String myHash(){
    	String data = "Cody Smith Check Sum!";
    	String checksumHex = sha256Hex(data);
       
        return "<p>data:"+data + "</p>"
        		+ "<p>Name of Cipher Algorithm Used: AES/GCM</p>"
        		+ "<p>Algorithm (hash): SHA-256</p>"
        		+ "<p>Checksum Value: " + checksumHex + "</p>";
    }
    
    private static String sha256Hex(String input) {
    	try {
    		java.security.MessageDigest messageDigest = java.security.MessageDigest.getInstance("SHA-256");
    		byte[] digest = messageDigest.digest(input.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    		return bytesToHex(digest);
    	}
    	catch (java.security.NoSuchAlgorithmException e) {
    		throw new IllegalStateException("sha-256 is not availible", e);
    	}
    }
    
    private static String bytesToHex(byte[] bytes) {
    	char[] hexArray = "0123456789abcdef".toCharArray();
    	char[] hexChars = new char[bytes.length * 2];
    	
    	for (int j = 0; j < bytes.length; j++) {
    		int v = bytes[j] & 0xFF;
    		hexChars[j * 2] = hexArray[v >>> 4];
    		hexChars[j * 2 + 1] = hexArray[v & 0x0F];
    	}
    	
    	return new String(hexChars);
    }
}


//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";