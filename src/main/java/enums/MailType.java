package enums;

public enum MailType {
    HOTMAIL("@hotmail.com"),
    GMAIL("@gmail.com"),
    YAHOO("@yahoo.com"),
    OUTLOOK("@outlook.com");

    public final String mailType;

    MailType(String mailType) {
        this.mailType = mailType;
    }
}

//direk valuelari(Mailtype.HOTMAIL) constructorun icine basar
//this class in kendi degiskeni