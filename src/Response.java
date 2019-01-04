public class Response<T> {
    private T value;

    Response (T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
