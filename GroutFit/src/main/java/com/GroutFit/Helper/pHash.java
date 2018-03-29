import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class pHash {
	public static void main(String[] args) {
		try {
			byte[] salt = getSalt();
			String pw = getPass("password", salt);
			System.out.println(checkPass("password", pw, salt));
		} catch(NoSuchAlgorithmException e) {
			System.out.println("ERROR main");
		}
	}

	public static String getPass(String ogPassword, byte[] salt) {
		String securePass = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt);
			byte[] pwByte = md.digest(ogPassword.getBytes());
			// convert hash to hex
			StringBuilder sb = new StringBuilder();
			for(int i=0; i < pwByte.length; i++)
			    sb.append(Integer.toString((pwByte[i] & 0xff) + 0x100, 16).substring(1));
			securePass = sb.toString();
		} catch(NoSuchAlgorithmException e) {
			System.out.println("ERROR getpass");
		}
		return securePass;
	}

  public static byte[] getSalt() throws NoSuchAlgorithmException {
      SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
      sr.setSeed(System.nanoTime());
      byte[] salt = new byte[16];
      sr.nextBytes(salt);
      return salt;
  }

  public static boolean checkPass(String pw, String hashedPW, byte[] salt) {
  	try {
  		MessageDigest md = MessageDigest.getInstance("SHA-256");
  		md.update(salt);
  		byte[] pwByte = md.digest(pw.getBytes());
  		// convert hash to hex
  		StringBuilder sb = new StringBuilder();
  		for(int i=0; i < pwByte.length; i++)
  		    sb.append(Integer.toString((pwByte[i] & 0xff) + 0x100, 16).substring(1));
  		String h = sb.toString();
  		if(sb.toString().equals(hashedPW))
  			return true;
  		else
  			return false;
  	} catch(NoSuchAlgorithmException e) {
			System.out.println("ERROR checkpass");
			return false;
		}
  }
}