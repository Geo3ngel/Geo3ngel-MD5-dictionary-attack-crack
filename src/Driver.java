import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		File encrypted_info = new File("dictionary.txt");
		File dictionary = new File("encrypted_passwords.txt");
		
		try {
			MD5_decrypter md5 = new MD5_decrypter(dictionary, encrypted_info);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
