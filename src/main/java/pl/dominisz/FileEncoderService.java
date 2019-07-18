package pl.dominisz;

public class FileEncoderService {

    private static final Logger log = LoggerFactory.getLogger(FileEncoderService.class);

    private final EmailClient emailClient;
    private final FileEncoder fileEncoder;

    public FileEncoderService(EmailClient emailClient, FileEncoder fileEncoder) {
        this.emailClient = emailClient;
        this.fileEncoder = fileEncoder;
    }

    public Result encode(String sourceFilename, String destinationFilename) {

        log.info("Start encoding " + sourceFilename);

        Result res = new Result();

        fileEncoder.encode(sourceFilename, destinationFilename, res);

        //If file encoding took too long then send email message
        if (res.getInf().get(0).getCode() != -1) {
            log.info("Stop encoding file " + sourceFilename);
        } else {
            emailClient.sendFailedMessage(sourceFilename);
        }

        return res;
    }

}