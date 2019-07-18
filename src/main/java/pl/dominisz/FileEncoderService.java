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

    Result res = fileEncoder.encode(sourceFilename, destinationFilename);

    // If file encoding took too long then send email message
    if (res.isSuccessful()) {
      log.info("Stop encoding file " + sourceFilename);
    } else {
      emailClient.sendFailedMessage(sourceFilename);
    }

    return res;
  }
}
