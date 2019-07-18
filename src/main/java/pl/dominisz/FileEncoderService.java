package pl.dominisz;

public class FileEncoderService {

    private static final Logger log = LoggerFactory.getLogger(FileEncoderService.class);

    private final EmailClient emailClient;

    public FileEncoderService(EmailClient emailClient) {
        this.emailClient = emailClient;
    }

    public Result encode(String sourceFilename, String destinationFilename) {

        log.info("Start encoding " + sourceFilename);

        Result res = new Result();

        FileEncoder fe = new FileEncoder();

        //Encode file f1 and write result to file f2.
        fe.encode(sourceFilename, destinationFilename, res);

        //If file encoding took too long then send email message
        if (res.getInf().get(0).getCode() != -1) {
            log.info("Stop encoding file " + sourceFilename);
        } else {
            emailClient.sendFailedMessage(sourceFilename);
        }

        return res;
    }

}