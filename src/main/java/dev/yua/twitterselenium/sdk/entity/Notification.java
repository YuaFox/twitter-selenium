package dev.yua.twitterselenium.sdk.entity;

public class Notification {

    public NotificationType type;
    public String handle;
    public String text;

    public static Notification parseNotification(String string){
        Notification notification = new Notification();
        String[] lines = string.split("\\n");
        if(lines.length < 2) return notification;
        if(lines[1].startsWith("@")){
            notification.type = NotificationType.REPLY;
            notification.handle = lines[1];
            notification.text = lines[6];
        }
        return notification;
    }

    public Notification(){
        this.type = NotificationType.UNSUPPORTED;
    }


}
