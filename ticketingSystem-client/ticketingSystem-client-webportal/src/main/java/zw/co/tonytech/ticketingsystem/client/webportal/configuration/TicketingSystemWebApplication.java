/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.tonytech.ticketingsystem.client.webportal.configuration;

import org.apache.wicket.Page;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;



/**
 *
 * @author tndangana
 */
public class TicketingSystemWebApplication  extends org.apache.wicket.protocol.http.WebApplication{

    @Override
    public Class<? extends Page> getHomePage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     @Override
    protected void init() {
        super.init();
        this.getComponentInstantiationListeners().add(new SpringComponentInjector(this));
    }
    
}
