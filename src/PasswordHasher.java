public interface PasswordHasher {
    String hash(String password);

    boolean compare(String password, String hashedPassword);
}
