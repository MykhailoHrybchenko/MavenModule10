public class JsonUser {

    private String name;
    private int age;
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    public void setName(String userName) {
        this.name = userName;
    }

    public void setAge(int userAge) {
        this.age = userAge;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "  Age: " + getAge();
    }
}
