/**
 * Write a description of class Client here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Client extends GeneralUser
{
    private static final long serialVersionUID = 1934567219L;
    // instance variables - replace the example below with your own
    private Posicao pos;


    public Client(String _email, String _name, String _password, String _morada, LocalDate _birthDate,String _nif)
    {
        super(_email,_name,_password,_morada,_birthDate,_nif);
        this.pos = new Posicao();
    }

    public Client(Client clt)
    {
        super(clt);
        this.pos = clt.getPos().clone();
    }
    
    public Posicao getPos() {
        return this.pos.clone();
    }
    
    public Client clone() {
        return new Client(this);
    }

    
}
