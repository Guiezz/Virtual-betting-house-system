import java.util.UUID;

public abstract class User implements PasswordHasher {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;

    public User(String firstName, String lastName, String email, String password) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = hash(password);
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo");
        }

        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("O primeiro nome não pode ser nulo ou vazio");
        }

        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("O sobrenome não pode ser nulo ou vazio");
        }

        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("O e-mail não pode ser nulo ou vazio");
        }

        this.email = email;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser nula ou vazia");
        }

        this.passwordHash = hash(password);
    }

    public void authenticate(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser nula ou vazia");
        }

        if (!compare(password, this.passwordHash)) {
            throw new IllegalArgumentException("Senha incorreta");
        }
    }

    public String hash(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser nula ou vazia");
        }

        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar o hash da senha", e);
        }
    }

    public boolean compare(String password, String hashedPassword) {
        if (password == null || password.isEmpty() || hashedPassword == null || hashedPassword.isEmpty()) {
            throw new IllegalArgumentException("A senha ou o hash da senha não podem ser nulos ou vazios");
        }

        return hash(password).equals(hashedPassword);
    }
}
