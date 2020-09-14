package digital.transformation.bookmarkurlshortner.api;

public interface BookMarkUrlEmailApi {
    void sendSimpleMessage(String to, String subject, String text);
}
