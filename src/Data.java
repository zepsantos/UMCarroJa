/**
 * Class to hold all data relevant for runtime.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Data implements  Serializable ,IData
{
    private static final long serialVersionUID = 123456789L;
    private Map<String, GeneralUser> users; // HashMap que contém todos os users, tendo o email como chave
    private Map<String,Vehicle> allVehicles; // contem todos os carros, tendo a matricula como key
    private GeneralUser loggedInUser = null;

    public boolean isLoggedIn () {
        return (loggedInUser != null);
    }


    public Data() {
        users = new HashMap<>();
        allVehicles = new HashMap<>();
    }

    public void logout() {
        loggedInUser = null;
    }

    public boolean loginOn (String username, String pass) {
        GeneralUser generalUser = null;
        boolean login = false;
        if(users.containsKey(username)){
            generalUser = users.get(username);
            login = (generalUser.getEmail().equals(username) && generalUser.getPassword().equals(pass));
        }
        if(login) {
            loggedInUser = generalUser;
        }
        return login;
    }
    public GeneralUser getLoggedInUser() {
        return this.loggedInUser.clone();
    }
    public void addUser (GeneralUser generalUser) {
        String key = generalUser.getEmail();
        users.put(key,generalUser);
    }

    public void populateData ( ) {

    }

    public void saveState ( ) {
        try {
            FileOutputStream fos = new FileOutputStream("data.tmp");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            System.out.println("Dados Gravados");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean addCar(Vehicle mVehicle) {
        Owner _own = (Owner) loggedInUser;
        boolean isSuccess = _own.addVehicle(mVehicle.getMatricula(),mVehicle);
        if(isSuccess)
        allVehicles.put(mVehicle.getMatricula(),mVehicle);
        return isSuccess;
    }

    public List<Vehicle> getListOfCarType(Vehicle a){
        return this.allVehicles.values().stream().filter(l-> l.getClass() == a.getClass()).map(Vehicle::clone).collect(Collectors.toList());
    }

}
