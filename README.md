## Opis aplikacji

W repozytorium znajduje się zarys aplikacji służącej do kodowania plików. 

* podstawową klasą aplikacji jest `FileEncoderService`
* kodowanie wykonywane jest po wywołaniu metody `encode` i podaniu nazw plików jako parametry
* metoda zwraca informacje o procesie kodowania jako obiekt klasy `Result`
* metoda dodatkowo loguje pewne informacje
* w przypadku problemów z kodowaniem wysyłane jest powiadomienie mailowe 

## Zadanie

Celem zadania jest dokonanie refaktoryzacji klasy `FileEncoderService` (i innych).

* uzupełnić brakujący kod klas i metod, tak aby aplikację dało się uruchomić
* dodać testy jednostkowe
* znaleźć code smells oraz złe praktyki obecne w kodzie
* zreafaktoryzować klasy

```
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
```

