package ma.youcode.batispro.exception;

public class FolderNotFoundException extends RuntimeException{
    public FolderNotFoundException(String message){
        super(message);
    }
}
