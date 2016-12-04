/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.tonytech.ticketingsystem.client.webportal.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import org.apache.wicket.protocol.http.WicketFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import zw.co.tonytech.ticketingsystem.business.config.PersistanceConfiguration;

/**
 *
 * @author tndangana
 */

@WebFilter(value = "/*", initParams = {
   @WebInitParam(name = "applicationClassName", value = "zw.co.tonytech.ticketingsystem.client.webportal.configuration.SupportApplication"),
   @WebInitParam(name = "filterMappingUrlPattern", value = "/*")})
public class TicketingSystemWebInitializer extends WicketFilter implements WebApplicationInitializer {

   @Override
   public void onStartup(ServletContext servletContext) throws ServletException {
       AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
       servletContext.addListener(new ContextLoaderListener(ctx));
       ctx.register(PersistanceConfiguration.class);

   }
}