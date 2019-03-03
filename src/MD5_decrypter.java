import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class MD5_decrypter {

		public MD5_decrypter(File encrypted_info, File dicitonary) throws IOException, NoSuchAlgorithmException {
	
			BufferedReader br_encrypted = new BufferedReader(new FileReader(encrypted_info));
			BufferedReader br_dictionary = new BufferedReader(new FileReader(dicitonary));
			
			ArrayList<String> encrypted_info_list = new ArrayList<String>();
			ArrayList<String> dictionary_list = new ArrayList<String>();
			
			System.out.println("Encrypted Passwords:");
			
			String temp1;
			while ((temp1 = br_encrypted.readLine()) != null) {
				System.out.println(" - "+temp1);
				encrypted_info_list.add(temp1);
			}
			
			// Begin tracking time for dictionary attack
			long begin = System.currentTimeMillis();
			
			String temp2;
			while ((temp2 = br_dictionary.readLine()) != null) {
				System.out.println("Processing... "+temp2);
				dictionary_list.add(temp2);
			}
			
			ArrayList<String> found = new ArrayList<String>();
			
			// Using a dictionary
			for (String string : dictionary_list) {
				System.out.println("Trying... "+string);
				String crpt = encryptToMD5(string);
				for (String s : encrypted_info_list) {
					if(crpt.equalsIgnoreCase(s)) {
						found.add("MATCH FOUND: "+s+":"+string+" in "+(System.currentTimeMillis()-begin)+" milliseconds!");
						System.out.println("MATCH FOUND: "+s+":"+string);
					}
				}
			}
			
			// Prints out the found passwords
			for (String string : found) {
				System.out.println(found);
			}
			
			/*
			// Works for passwords consisting of only 6 digits
			for(int i = 0; i < 1000001; i++) {
				String test = encryptToMD5(""+i);
				for (String s : encrypted_info_list) {
					if(test.equals(s)) {
						System.out.println("MATCH FOUND: "+s+":"+i+" in "+(System.currentTimeMillis()-begin)+" milliseconds");
					}
				}
			}*/
	
			System.out.println("END");
			
		}
		
		// Converts any number under 6 digits to a 6 digit String
		private String numToString(int i) {
			
			String result = "";
			
			int val = i;
			int digits = 0;
			while(i > 0) {
				digits++;
				i = i/10;
			}
			
			if(digits != 6) {
				for(int iter = digits; iter < 6; iter++) {
					result+=0;
				}
				result+=val;
				return result;
			}
			else
				return ""+val;
		}

		// Encrypts a String with MD5
		public String encryptToMD5(String password) throws NoSuchAlgorithmException {
	        MessageDigest md5Encryptor = MessageDigest.getInstance("MD5");
	        md5Encryptor.update(StandardCharsets.UTF_8.encode(password));
	        return String.format("%032x", new BigInteger(1, md5Encryptor.digest()));
		}
}
