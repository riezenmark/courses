package stream;

public class Profile {

    private Address address;

    public static class Address {
        private String city;
        private String street;
        private int home;
        private int apartment;

        public Address(String city, String street, int home, int apartment) {
            this.city = city;
            this.street = street;
            this.home = home;
            this.apartment = apartment;
        }
    }

    public Profile(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}

