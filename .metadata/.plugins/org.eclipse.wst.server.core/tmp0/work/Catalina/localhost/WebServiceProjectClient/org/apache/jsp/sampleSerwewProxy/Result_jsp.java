/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.92
 * Generated at: 2019-07-04 06:03:17 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.sampleSerwewProxy;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Result_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
 request.setCharacterEncoding("UTF-8"); 
      out.write("\r\n");
      out.write("<HTML>\r\n");
      out.write("<HEAD>\r\n");
      out.write("<TITLE>Result</TITLE>\r\n");
      out.write("</HEAD>\r\n");
      out.write("<BODY>\r\n");
      out.write("<H1>Result</H1>\r\n");
      out.write("\r\n");
      consumo_sw.SerwewProxy sampleSerwewProxyid = null;
      synchronized (session) {
        sampleSerwewProxyid = (consumo_sw.SerwewProxy) _jspx_page_context.getAttribute("sampleSerwewProxyid", javax.servlet.jsp.PageContext.SESSION_SCOPE);
        if (sampleSerwewProxyid == null){
          sampleSerwewProxyid = new consumo_sw.SerwewProxy();
          _jspx_page_context.setAttribute("sampleSerwewProxyid", sampleSerwewProxyid, javax.servlet.jsp.PageContext.SESSION_SCOPE);
        }
      }
      out.write('\r');
      out.write('\n');

if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleSerwewProxyid.setEndpoint(request.getParameter("endpoint"));

      out.write("\r\n");
      out.write("\r\n");

String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        java.lang.String getEndpoint2mtemp = sampleSerwewProxyid.getEndpoint();
if(getEndpoint2mtemp == null){

      out.write('\r');
      out.write('\n');
      out.print(getEndpoint2mtemp );
      out.write('\r');
      out.write('\n');

}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEndpoint2mtemp));
        
      out.write("\r\n");
      out.write("        ");
      out.print( tempResultreturnp3 );
      out.write("\r\n");
      out.write("        ");

}
break;
case 5:
        gotMethod = true;
        String endpoint_0id=  request.getParameter("endpoint8");
            java.lang.String endpoint_0idTemp = null;
        if(!endpoint_0id.equals("")){
         endpoint_0idTemp  = endpoint_0id;
        }
        sampleSerwewProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        consumo_sw.Serwew getSerwew10mtemp = sampleSerwewProxyid.getSerwew();
if(getSerwew10mtemp == null){

      out.write('\r');
      out.write('\n');
      out.print(getSerwew10mtemp );
      out.write('\r');
      out.write('\n');

}else{
        if(getSerwew10mtemp!= null){
        String tempreturnp11 = getSerwew10mtemp.toString();
        
      out.write("\r\n");
      out.write("        ");
      out.print(tempreturnp11);
      out.write("\r\n");
      out.write("        ");

        }}
break;
case 13:
        gotMethod = true;
        String qr_1id=  request.getParameter("qr16");
            java.lang.String qr_1idTemp = null;
        if(!qr_1id.equals("")){
         qr_1idTemp  = qr_1id;
        }
        String idCol_2id=  request.getParameter("idCol18");
            java.lang.String idCol_2idTemp = null;
        if(!idCol_2id.equals("")){
         idCol_2idTemp  = idCol_2id;
        }
        int regresaId13mtemp = sampleSerwewProxyid.regresaId(qr_1idTemp,idCol_2idTemp);
        String tempResultreturnp14 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(regresaId13mtemp));
        
      out.write("\r\n");
      out.write("        ");
      out.print( tempResultreturnp14 );
      out.write("\r\n");
      out.write("        ");

break;
case 20:
        gotMethod = true;
        String noma_3id=  request.getParameter("noma23");
            java.lang.String noma_3idTemp = null;
        if(!noma_3id.equals("")){
         noma_3idTemp  = noma_3id;
        }
        String pasa_4id=  request.getParameter("pasa25");
            java.lang.String pasa_4idTemp = null;
        if(!pasa_4id.equals("")){
         pasa_4idTemp  = pasa_4id;
        }
        String nom_5id=  request.getParameter("nom27");
            java.lang.String nom_5idTemp = null;
        if(!nom_5id.equals("")){
         nom_5idTemp  = nom_5id;
        }
        String pas_6id=  request.getParameter("pas29");
            java.lang.String pas_6idTemp = null;
        if(!pas_6id.equals("")){
         pas_6idTemp  = pas_6id;
        }
        String niv_7id=  request.getParameter("niv31");
        int niv_7idTemp  = Integer.parseInt(niv_7id);
        java.lang.String registrarUsuario20mtemp = sampleSerwewProxyid.registrarUsuario(noma_3idTemp,pasa_4idTemp,nom_5idTemp,pas_6idTemp,niv_7idTemp);
if(registrarUsuario20mtemp == null){

      out.write('\r');
      out.write('\n');
      out.print(registrarUsuario20mtemp );
      out.write('\r');
      out.write('\n');

}else{
        String tempResultreturnp21 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(registrarUsuario20mtemp));
        
      out.write("\r\n");
      out.write("        ");
      out.print( tempResultreturnp21 );
      out.write("\r\n");
      out.write("        ");

}
break;
case 33:
        gotMethod = true;
        String noma_8id=  request.getParameter("noma36");
            java.lang.String noma_8idTemp = null;
        if(!noma_8id.equals("")){
         noma_8idTemp  = noma_8id;
        }
        String pasa_9id=  request.getParameter("pasa38");
            java.lang.String pasa_9idTemp = null;
        if(!pasa_9id.equals("")){
         pasa_9idTemp  = pasa_9id;
        }
        String nom_10id=  request.getParameter("nom40");
            java.lang.String nom_10idTemp = null;
        if(!nom_10id.equals("")){
         nom_10idTemp  = nom_10id;
        }
        String pas_11id=  request.getParameter("pas42");
            java.lang.String pas_11idTemp = null;
        if(!pas_11id.equals("")){
         pas_11idTemp  = pas_11id;
        }
        String npas_12id=  request.getParameter("npas44");
            java.lang.String npas_12idTemp = null;
        if(!npas_12id.equals("")){
         npas_12idTemp  = npas_12id;
        }
        java.lang.String cambiarPwd33mtemp = sampleSerwewProxyid.cambiarPwd(noma_8idTemp,pasa_9idTemp,nom_10idTemp,pas_11idTemp,npas_12idTemp);
if(cambiarPwd33mtemp == null){

      out.write('\r');
      out.write('\n');
      out.print(cambiarPwd33mtemp );
      out.write('\r');
      out.write('\n');

}else{
        String tempResultreturnp34 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(cambiarPwd33mtemp));
        
      out.write("\r\n");
      out.write("        ");
      out.print( tempResultreturnp34 );
      out.write("\r\n");
      out.write("        ");

}
break;
case 46:
        gotMethod = true;
        String noma_13id=  request.getParameter("noma49");
            java.lang.String noma_13idTemp = null;
        if(!noma_13id.equals("")){
         noma_13idTemp  = noma_13id;
        }
        String pasa_14id=  request.getParameter("pasa51");
            java.lang.String pasa_14idTemp = null;
        if(!pasa_14id.equals("")){
         pasa_14idTemp  = pasa_14id;
        }
        String nom_15id=  request.getParameter("nom53");
            java.lang.String nom_15idTemp = null;
        if(!nom_15id.equals("")){
         nom_15idTemp  = nom_15id;
        }
        String pas_16id=  request.getParameter("pas55");
            java.lang.String pas_16idTemp = null;
        if(!pas_16id.equals("")){
         pas_16idTemp  = pas_16id;
        }
        java.lang.String eliminarUsuario46mtemp = sampleSerwewProxyid.eliminarUsuario(noma_13idTemp,pasa_14idTemp,nom_15idTemp,pas_16idTemp);
if(eliminarUsuario46mtemp == null){

      out.write('\r');
      out.write('\n');
      out.print(eliminarUsuario46mtemp );
      out.write('\r');
      out.write('\n');

}else{
        String tempResultreturnp47 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(eliminarUsuario46mtemp));
        
      out.write("\r\n");
      out.write("        ");
      out.print( tempResultreturnp47 );
      out.write("\r\n");
      out.write("        ");

}
break;
case 57:
        gotMethod = true;
        String clavp_17id=  request.getParameter("clavp60");
            java.lang.String clavp_17idTemp = null;
        if(!clavp_17id.equals("")){
         clavp_17idTemp  = clavp_17id;
        }
        String nomp_18id=  request.getParameter("nomp62");
            java.lang.String nomp_18idTemp = null;
        if(!nomp_18id.equals("")){
         nomp_18idTemp  = nomp_18id;
        }
        String catp_19id=  request.getParameter("catp64");
            java.lang.String catp_19idTemp = null;
        if(!catp_19id.equals("")){
         catp_19idTemp  = catp_19id;
        }
        String precd_20id=  request.getParameter("precd66");
        float precd_20idTemp  = Float.parseFloat(precd_20id);
        String mard_21id=  request.getParameter("mard68");
            java.lang.String mard_21idTemp = null;
        if(!mard_21id.equals("")){
         mard_21idTemp  = mard_21id;
        }
        String desd_22id=  request.getParameter("desd70");
            java.lang.String desd_22idTemp = null;
        if(!desd_22id.equals("")){
         desd_22idTemp  = desd_22id;
        }
        boolean generaXML57mtemp = sampleSerwewProxyid.generaXML(clavp_17idTemp,nomp_18idTemp,catp_19idTemp,precd_20idTemp,mard_21idTemp,desd_22idTemp);
        String tempResultreturnp58 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(generaXML57mtemp));
        
      out.write("\r\n");
      out.write("        ");
      out.print( tempResultreturnp58 );
      out.write("\r\n");
      out.write("        ");

break;
case 72:
        gotMethod = true;
        java.lang.String consultar72mtemp = sampleSerwewProxyid.consultar();
if(consultar72mtemp == null){

      out.write('\r');
      out.write('\n');
      out.print(consultar72mtemp );
      out.write('\r');
      out.write('\n');

}else{
        String tempResultreturnp73 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(consultar72mtemp));
        
      out.write("\r\n");
      out.write("        ");
      out.print( tempResultreturnp73 );
      out.write("\r\n");
      out.write("        ");

}
break;
case 75:
        gotMethod = true;
        String nom_23id=  request.getParameter("nom78");
            java.lang.String nom_23idTemp = null;
        if(!nom_23id.equals("")){
         nom_23idTemp  = nom_23id;
        }
        String pas_24id=  request.getParameter("pas80");
            java.lang.String pas_24idTemp = null;
        if(!pas_24id.equals("")){
         pas_24idTemp  = pas_24id;
        }
        String clavp_25id=  request.getParameter("clavp82");
            java.lang.String clavp_25idTemp = null;
        if(!clavp_25id.equals("")){
         clavp_25idTemp  = clavp_25id;
        }
        String nomp_26id=  request.getParameter("nomp84");
            java.lang.String nomp_26idTemp = null;
        if(!nomp_26id.equals("")){
         nomp_26idTemp  = nomp_26id;
        }
        String catp_27id=  request.getParameter("catp86");
            java.lang.String catp_27idTemp = null;
        if(!catp_27id.equals("")){
         catp_27idTemp  = catp_27id;
        }
        String precd_28id=  request.getParameter("precd88");
        float precd_28idTemp  = Float.parseFloat(precd_28id);
        String mard_29id=  request.getParameter("mard90");
            java.lang.String mard_29idTemp = null;
        if(!mard_29id.equals("")){
         mard_29idTemp  = mard_29id;
        }
        String desd_30id=  request.getParameter("desd92");
            java.lang.String desd_30idTemp = null;
        if(!desd_30id.equals("")){
         desd_30idTemp  = desd_30id;
        }
        java.lang.String registrarProd75mtemp = sampleSerwewProxyid.registrarProd(nom_23idTemp,pas_24idTemp,clavp_25idTemp,nomp_26idTemp,catp_27idTemp,precd_28idTemp,mard_29idTemp,desd_30idTemp);
if(registrarProd75mtemp == null){

      out.write('\r');
      out.write('\n');
      out.print(registrarProd75mtemp );
      out.write('\r');
      out.write('\n');

}else{
        String tempResultreturnp76 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(registrarProd75mtemp));
        
      out.write("\r\n");
      out.write("        ");
      out.print( tempResultreturnp76 );
      out.write("\r\n");
      out.write("        ");

}
break;
case 94:
        gotMethod = true;
        String nom_31id=  request.getParameter("nom97");
            java.lang.String nom_31idTemp = null;
        if(!nom_31id.equals("")){
         nom_31idTemp  = nom_31id;
        }
        String pas_32id=  request.getParameter("pas99");
            java.lang.String pas_32idTemp = null;
        if(!pas_32id.equals("")){
         pas_32idTemp  = pas_32id;
        }
        String clavp_33id=  request.getParameter("clavp101");
            java.lang.String clavp_33idTemp = null;
        if(!clavp_33id.equals("")){
         clavp_33idTemp  = clavp_33id;
        }
        java.lang.String borrarProd94mtemp = sampleSerwewProxyid.borrarProd(nom_31idTemp,pas_32idTemp,clavp_33idTemp);
if(borrarProd94mtemp == null){

      out.write('\r');
      out.write('\n');
      out.print(borrarProd94mtemp );
      out.write('\r');
      out.write('\n');

}else{
        String tempResultreturnp95 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(borrarProd94mtemp));
        
      out.write("\r\n");
      out.write("        ");
      out.print( tempResultreturnp95 );
      out.write("\r\n");
      out.write("        ");

}
break;
case 103:
        gotMethod = true;
        String nom_34id=  request.getParameter("nom106");
            java.lang.String nom_34idTemp = null;
        if(!nom_34id.equals("")){
         nom_34idTemp  = nom_34id;
        }
        String pas_35id=  request.getParameter("pas108");
            java.lang.String pas_35idTemp = null;
        if(!pas_35id.equals("")){
         pas_35idTemp  = pas_35id;
        }
        String clavp_36id=  request.getParameter("clavp110");
            java.lang.String clavp_36idTemp = null;
        if(!clavp_36id.equals("")){
         clavp_36idTemp  = clavp_36id;
        }
        String nclavp_37id=  request.getParameter("nclavp112");
            java.lang.String nclavp_37idTemp = null;
        if(!nclavp_37id.equals("")){
         nclavp_37idTemp  = nclavp_37id;
        }
        String nomp_38id=  request.getParameter("nomp114");
            java.lang.String nomp_38idTemp = null;
        if(!nomp_38id.equals("")){
         nomp_38idTemp  = nomp_38id;
        }
        String catp_39id=  request.getParameter("catp116");
            java.lang.String catp_39idTemp = null;
        if(!catp_39id.equals("")){
         catp_39idTemp  = catp_39id;
        }
        String precd_40id=  request.getParameter("precd118");
        float precd_40idTemp  = Float.parseFloat(precd_40id);
        String mard_41id=  request.getParameter("mard120");
            java.lang.String mard_41idTemp = null;
        if(!mard_41id.equals("")){
         mard_41idTemp  = mard_41id;
        }
        String desd_42id=  request.getParameter("desd122");
            java.lang.String desd_42idTemp = null;
        if(!desd_42id.equals("")){
         desd_42idTemp  = desd_42id;
        }
        java.lang.String actualizarProd103mtemp = sampleSerwewProxyid.actualizarProd(nom_34idTemp,pas_35idTemp,clavp_36idTemp,nclavp_37idTemp,nomp_38idTemp,catp_39idTemp,precd_40idTemp,mard_41idTemp,desd_42idTemp);
if(actualizarProd103mtemp == null){

      out.write('\r');
      out.write('\n');
      out.print(actualizarProd103mtemp );
      out.write('\r');
      out.write('\n');

}else{
        String tempResultreturnp104 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(actualizarProd103mtemp));
        
      out.write("\r\n");
      out.write("        ");
      out.print( tempResultreturnp104 );
      out.write("\r\n");
      out.write("        ");

}
break;
case 124:
        gotMethod = true;
        String nom_43id=  request.getParameter("nom127");
            java.lang.String nom_43idTemp = null;
        if(!nom_43id.equals("")){
         nom_43idTemp  = nom_43id;
        }
        String pas_44id=  request.getParameter("pas129");
            java.lang.String pas_44idTemp = null;
        if(!pas_44id.equals("")){
         pas_44idTemp  = pas_44id;
        }
        String clavp_45id=  request.getParameter("clavp131");
            java.lang.String clavp_45idTemp = null;
        if(!clavp_45id.equals("")){
         clavp_45idTemp  = clavp_45id;
        }
        java.lang.String eliminarProd124mtemp = sampleSerwewProxyid.eliminarProd(nom_43idTemp,pas_44idTemp,clavp_45idTemp);
if(eliminarProd124mtemp == null){

      out.write('\r');
      out.write('\n');
      out.print(eliminarProd124mtemp );
      out.write('\r');
      out.write('\n');

}else{
        String tempResultreturnp125 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(eliminarProd124mtemp));
        
      out.write("\r\n");
      out.write("        ");
      out.print( tempResultreturnp125 );
      out.write("\r\n");
      out.write("        ");

}
break;
case 133:
        gotMethod = true;
        String nom_46id=  request.getParameter("nom136");
            java.lang.String nom_46idTemp = null;
        if(!nom_46id.equals("")){
         nom_46idTemp  = nom_46id;
        }
        String pas_47id=  request.getParameter("pas138");
            java.lang.String pas_47idTemp = null;
        if(!pas_47id.equals("")){
         pas_47idTemp  = pas_47id;
        }
        String cat_48id=  request.getParameter("cat140");
            java.lang.String cat_48idTemp = null;
        if(!cat_48id.equals("")){
         cat_48idTemp  = cat_48id;
        }
        java.lang.String obtenerProductos133mtemp = sampleSerwewProxyid.obtenerProductos(nom_46idTemp,pas_47idTemp,cat_48idTemp);
if(obtenerProductos133mtemp == null){

      out.write('\r');
      out.write('\n');
      out.print(obtenerProductos133mtemp );
      out.write('\r');
      out.write('\n');

}else{
        String tempResultreturnp134 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(obtenerProductos133mtemp));
        
      out.write("\r\n");
      out.write("        ");
      out.print( tempResultreturnp134 );
      out.write("\r\n");
      out.write("        ");

}
break;
case 142:
        gotMethod = true;
        String nom_49id=  request.getParameter("nom145");
            java.lang.String nom_49idTemp = null;
        if(!nom_49id.equals("")){
         nom_49idTemp  = nom_49id;
        }
        String pas_50id=  request.getParameter("pas147");
            java.lang.String pas_50idTemp = null;
        if(!pas_50id.equals("")){
         pas_50idTemp  = pas_50id;
        }
        String clav_51id=  request.getParameter("clav149");
            java.lang.String clav_51idTemp = null;
        if(!clav_51id.equals("")){
         clav_51idTemp  = clav_51id;
        }
        java.lang.String obtenerDetalles142mtemp = sampleSerwewProxyid.obtenerDetalles(nom_49idTemp,pas_50idTemp,clav_51idTemp);
if(obtenerDetalles142mtemp == null){

      out.write('\r');
      out.write('\n');
      out.print(obtenerDetalles142mtemp );
      out.write('\r');
      out.write('\n');

}else{
        String tempResultreturnp143 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(obtenerDetalles142mtemp));
        
      out.write("\r\n");
      out.write("        ");
      out.print( tempResultreturnp143 );
      out.write("\r\n");
      out.write("        ");

}
break;
}
} catch (Exception e) { 

      out.write("\r\n");
      out.write("Exception: ");
      out.print( org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) );
      out.write("\r\n");
      out.write("Message: ");
      out.print( org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) );
      out.write('\r');
      out.write('\n');

return;
}
if(!gotMethod){

      out.write("\r\n");
      out.write("result: N/A\r\n");

}

      out.write("\r\n");
      out.write("</BODY>\r\n");
      out.write("</HTML>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
