package pl.dominisz;

public class FileEncoderService {

    private static final Logger log = LoggerFactory.getLogger(FileEncoderService.class);

    public FileEncoderService() {
    }

    public Result encode(String f1, String f2) {

        log.info("Start encoding");

        Result res = new Result();

        FileEncoder fe = new FileEncoder();

        //Encode file f1 and write result to file f2.
        fe.encode(f1, f2, res);

        //If file encoding took too long then send email message
        if (res.getInf().get(0).getCode() != -1) {
            log.info("Stop encoding file " + f1);
            return res;
        } else {
            EmailClient emailSender = new EmailClient("admin@mail.com", "user", "password");
            emailSender.sendFailedMessage();
            return res;
        }
    }

}