package zw.co.tonytech.ticketingsystem.business.domain;

/**
 * Created by tndangana on 12/6/16.
 */
public class NameBaseEntity extends IdBaseEntity {

    private String name ;


    public String getName(){
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }
}
