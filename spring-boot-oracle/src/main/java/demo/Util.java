package demo;

import spwrap.CallException;

import java.sql.SQLException;

class Util {

    static int getOrclResultCode(CallException ex){
        return ((SQLException) ex.getCause()).getErrorCode();
    }
}
