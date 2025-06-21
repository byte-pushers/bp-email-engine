package software.bytepushers.email.dto;

public class MailRequest {
    private String emailSender;
    private String emailSubject;
    private String emailBody;

    public String getTo(){
        return emailSender;
    }
    public void setTo(String to){
        this.emailSender = to;
    }
    public String getSubject(){
        return emailSubject;
    }
    public void setSubject(String subject){
        this.emailSubject = subject;
    }
    public String getBody(){
        return emailBody;
    }
    public void setBody(String body){
        this.emailBody = body;
    }
}
