import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

class Account {
    private String username;
    private Email email;
    private Password password;

    public Account(String username, String emailAddress, String password) {
        this.username = username;
        this.email = new Email(emailAddress);
        this.password = new Password(password);
    }

    public void changeEmail(String newEmailAddress) {
        this.email.setEmailAddress(newEmailAddress);
    }

    public void changePassword(String newPassword) {
        this.password.setPassword(newPassword);
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", email=" + email +
                ", password=" + password +
                '}';
    }
}

class Email {
    private String emailAddress;

    public Email(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setEmailAddress(String newEmailAddress) {
        this.emailAddress = newEmailAddress;
    }

    @Override
    public String toString() {
        return "Email{" +
                "emailAddress='" + emailAddress + '\'' +
                '}';
    }
}

class Password {
    private String hashedPassword;

    public Password(String plainPassword) {
        this.hashedPassword = hashPassword(plainPassword);
    }

    public void setPassword(String newPassword) {
        this.hashedPassword = hashPassword(newPassword);
    }

    private String hashPassword(String plainPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(plainPassword.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Password{" +
                "hashedPassword='" + hashedPassword + '\'' +
                '}';
    }
}

public class CyberSecurity {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char ulang;

        System.out.println("============KEAMANAN & INFO PENGGUNA============");
        System.out.print("Masukkan Username Anda: ");
        String username = scanner.nextLine();

        System.out.print("Masukkan Alamat email Anda: ");
        String emailAddress = scanner.nextLine();

        System.out.print("Masukkan Kata Sandi Anda: ");
        String password = scanner.nextLine();

        // Membuat akun berdasarkan input pengguna
        Account userAccount = new Account(username, emailAddress, password);

        System.out.println("\nDetail Akun Pengguna:");
        System.out.println(userAccount);

        do {

            // Simulasi modifikasi akun
            System.out.print("\nMasukkan Alamat email Anda yang Baru: ");
            String newEmailAddress = scanner.nextLine();
            userAccount.changeEmail(newEmailAddress);

            System.out.print("Masukkan Kata Sandi yang Baru: ");
            String newPassword = scanner.nextLine();
            userAccount.changePassword(newPassword);

            System.out.println("\nKeamanan Diperbarui:");
            System.out.println(userAccount);

            // Konfirmasi Pembaruan Akun
            System.out.println("\nKonfirmasi Pembaruan Akun? (iya: Y atau tidak: N): ");
            ulang = scanner.next().charAt(0);
            scanner.nextLine();

        } while (ulang == 'n' || ulang == 'N');

        System.out.println("Well Done!!! Akun Berhasil Diperbarui!!");
        scanner.close();
    }
}