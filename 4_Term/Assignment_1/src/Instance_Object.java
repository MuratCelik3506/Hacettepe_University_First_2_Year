public class Instance_Object {
    public int number;
    public char word;

    public Instance_Object(int number, char word) {
        this.number = number;
        this.word = word;
    }

    @Override
    public String toString() {
        return "(" + number +","+ word + ')';
    }
}
