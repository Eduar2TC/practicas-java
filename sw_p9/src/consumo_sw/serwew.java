package consumo_sw;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.rmi.RemoteException;
import java.sql.*;

import buap_api.RespDetail;
import buap_api.RespProd;
import buap_api.WebServicesBUAPPortTypeProxy;
import buap_api1.RespuestaXML;
import buap_api1.WebServicesBUAP1PortTypeProxy; 

public class serwew {
	private WebServicesBUAPPortTypeProxy client;
	public  boolean xmlP,xmlD; 
	public  String  strP,strD; 
	private Statement stmt;
    private ResultSet rs;
    private Connection con;
    private String url,username,password,query;
    
	public serwew(){
		//stmt=con.createStatement();
		//rs=stmt.executeQuery(query);
		con = null;
		url = "jdbc:mysql://localhost:3306/productos";
		username = "root";
		password = "ACM1PT";
		query="";	
		client = new WebServicesBUAPPortTypeProxy();
		xmlP=false;
		xmlD=false;
		strP="";
		strD="";
	}
	// Retorna un hash a partir de un tipo y un texto */
    public static String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static String md5(String txt) {
        return getHash(txt, "MD5");
    }
    public static String sha1(String txt) {
        return getHash(txt, "SHA1");
    }
    public String consultar() {
		Connection con = null;
        String respuesta="";
		try {
            con =  DriverManager.getConnection(url, username, password);
            if (con != null) {
            	stmt=con.createStatement();
                query="select * from cata_usuarios";
                rs=stmt.executeQuery(query);
                while(rs.next()) {
                	respuesta=respuesta + " "+ rs.getString(2);
                }    
                rs.close();
                stmt.close();
                con.close();  
            }
        } catch (SQLException ex) {
            //System.out
             //       .println("An error occurred while connecting MySQL databse");
            //ex.printStackTrace();
        }
		return respuesta;
		//return md5("respuesta");
	}
    
    
    //metodos de la practica
    public int contar(String qr) {
		Connection con = null;
        int respuesta=0;
		try {
            con =  DriverManager.getConnection(url, username, password);
            if (con != null) {
            	stmt=con.createStatement();
                query=qr;
                rs=stmt.executeQuery(query);
                while (rs.next()) {
                	respuesta=rs.getInt("count(*)");
                }
                rs.close();
                stmt.close();
                con.close();  
            }
        } catch (SQLException ex) { }
		return respuesta;
	}
    public int ultimo(String tabla,String columna) {
		Connection con = null;
        int respuesta=0;
		try {
            con =  DriverManager.getConnection(url, username, password);
            if (con != null) {
            	stmt=con.createStatement();
                query="select "+columna+" from "+tabla+" order by "+columna+" desc limit 1";
                rs=stmt.executeQuery(query);
                while (rs.next()) {
                	respuesta=rs.getInt(""+columna+"");
                }
                rs.close();
                stmt.close();
                con.close();  
            }
        } catch (SQLException ex) { }
		return respuesta;
	}
    public int regresaId(String qr,String idCol) {
		Connection con = null;
        int respuesta=0;
		try {
            con =  DriverManager.getConnection(url, username, password);
            if (con != null) {
            	stmt=con.createStatement();
                query=qr;
                rs=stmt.executeQuery(query);
                while (rs.next()) {
                	respuesta=rs.getInt(""+idCol+"");
                }
                rs.close();
                stmt.close();
                con.close();  
            }
        } catch (SQLException ex) { }
		return respuesta;
	}
    
    public String registrarUsuario(String noma,String pasa,String nom,String pas,int niv) {
		Connection con = null;
        String respuesta="";
        pas=md5(pas);
        pasa=md5(pasa);
        int nuser=contar("select count(*) from cata_usuarios where nombre_user='"+noma+"' and password_user='"+pasa+"' and eliminado_user=0 and nivel_user=1");
        if (nuser>0) {
		    try {
	            con =  DriverManager.getConnection(url, username, password);
	            if (con != null) {
	            	stmt=con.createStatement();
	                query="insert into cata_usuarios(nombre_user,password_user,eliminado_user,nivel_user) VALUES ('";
	                query=query+nom+"','"+pas+"',0,"+niv+")";
	                stmt.executeUpdate(query);
	                respuesta="Usario creado exitosamente, codigo 200";
	                stmt.close();
	                con.close();  
	            }
	        } catch (SQLException ex) {
	        	respuesta=ex.toString();
	        	//respuesta ="Error al crear el usuario. codigo 301";
	        }
        }else {
      		respuesta="El usuario no esta autorizado para procesar esta acci??n, codigo 203";
       	}
        return respuesta;
	}
	
	public String cambiarPwd(String noma,String pasa,String nom,String pas,String npas) {
		Connection con = null;
        String respuesta="";
        pas=md5(pas);
        npas=md5(npas);
        pasa=md5(pasa);
        int nusera=contar("select count(*) from cata_usuarios where nombre_user='"+noma+"' and password_user='"+pasa+"' and eliminado_user=0 and nivel_user=1");
        if (nusera>0) {
        	int nuser=contar("select count(*) from cata_usuarios where nombre_user='"+nom+"' and password_user='"+pas+"' and elminado_user=0");
	        if (nuser>0) {
		        try {
		            con =  DriverManager.getConnection(url, username, password);
		            if (con != null) {
		            	stmt=con.createStatement();
		                query="update cata_usuarios set password_user='"+npas+"' where nombre_user='"+nom+"' and password_user='"+pas+"'";
		                stmt.executeUpdate(query);
		                respuesta="La contrase??a se cambio exitosamente, codigo 200";
		                stmt.close();
		                con.close();  
		            }
		        } catch (SQLException ex) {
		        	respuesta ="Error al acrualizar el usuario. codigo 301";
		        }
	       	}else {
	       		respuesta="El usuario no existe en la base de datos, codigo 203";
	       	}
        }else {
      		respuesta="El usuario no esta autorizado para procesar esta acci??n, codigo 203";
       	}
        return respuesta;
	}

	public String eliminarUsuario(String noma,String pasa,String nom,String pas) {
		Connection con = null;
        String respuesta="";
        pas=md5(pas);
        pasa=md5(pasa);
        int nusera=contar("select count(*) from cata_usuarios where nombre_user='"+noma+"' and password_user='"+pasa+"' and eliminado_user=0 and nivel_user=1");
        if (nusera>0) {
	        int nuser=contar("select count(*) from cata_usuarios where nombre_user='"+nom+"' and password_user='"+pas+"' and eliminado_user=0");
	        if (nuser>0) {
		        try {
		            con =  DriverManager.getConnection(url, username, password);
		            if (con != null) {
		            	stmt=con.createStatement();
		                query="update cata_usuarios set eliminado_user=1 where nombre_user='"+nom+"' and password_user='"+pas+"'";
		                stmt.executeUpdate(query);
		                respuesta="El usuario se ha eliminado exitosamente, codigo 200";
		                stmt.close();
		                con.close();  
		            }
		        } catch (SQLException ex) {
		        	respuesta ="Error al elminiar el usuario. codigo 301";
		        }
	       	}else {
	       		respuesta="El usuario no existe en la base de datos, codigo 203";
	       	}
      	}else {
      		respuesta="El usuario no esta autorizado para procesar esta acci??n, codigo 203";
       	}
        return respuesta;
	}
	
	public boolean generaXML(String clavp,String nomp,String catp,float precd,String mard,String desd) {
		boolean guardado=false;
		try {
			 
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			// elemento raiz
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("productos");
			doc.appendChild(rootElement);
	 
			Element producto = doc.createElement("producto");
			rootElement.appendChild(producto);

			Attr attr = doc.createAttribute("clave");
			attr.setValue(clavp);
			producto.setAttributeNode(attr);
			Attr attr2 = doc.createAttribute("categoria");
			attr2.setValue(catp);
			producto.setAttributeNode(attr2);
	 
			Element nombre = doc.createElement("nombre");
			nombre.appendChild(doc.createTextNode(nomp));
			producto.appendChild(nombre);
	 
			Element apellidos = doc.createElement("marca");
			apellidos.appendChild(doc.createTextNode(mard));
			producto.appendChild(apellidos);
	 
			Element seccion = doc.createElement("precio");
			seccion.appendChild(doc.createTextNode(Float.toString(precd)));
			producto.appendChild(seccion);
	 
			Element salario = doc.createElement("descripcion");
			salario.appendChild(doc.createTextNode(desd));
			producto.appendChild(salario);
	 
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("C:\\wamp64\\www\\WebServices\\p9\\09_RegistroProductos.xml"));
			
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,"yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(source, result);
			
			guardado=true;
			
			} catch (ParserConfigurationException pce) {
				//pce.printStackTrace();
			} catch (TransformerException tfe) {
				//tfe.printStackTrace();
			}
		return guardado;
	}
	
    public String registrarProd(String nom, String pas,String clavp,String nomp,String catp,float precd,String mard,String desd) {
		Connection con = null;
		int id_prod=0;
        String respuesta="";
        String xmlRes="";
        pas=md5(pas);
        int nuser=contar("select count(*) from cata_usuarios where nombre_user='"+nom+"' and password_user='"+pas+"' and eliminado_user=0 and nivel_user=2");
        if (nuser>0) {
        	generaXML(clavp, nomp, catp, precd, mard, desd);
        	xmlRes=getValidar("09_RegistroProductos");
        	if (xmlRes=="#") {
				try {
		            con =  DriverManager.getConnection(url, username, password);
		            if (con != null) {
		            	stmt=con.createStatement();
		                query="insert into cata_productos(clave_prod,nombre_prod,categoria_prod,stock,eliminado_prod) VALUES ('";
		                query=query+clavp+"','"+nomp+"','"+catp+"',1,0)";
		                stmt.executeUpdate(query);
		                stmt.close();
		                id_prod=ultimo("cata_productos","id_prod_sistema");
		                if (id_prod>0) {
			                stmt=con.createStatement();
			                query="INSERT INTO cata_detalles(id_prod_sistema,precio_prod,marca_prod,descripcion_prod,eliminado_prod) VALUES (";
			                query=query+id_prod+","+precd+",'"+mard+"','"+desd+"',0)";
			                stmt.executeUpdate(query);
			                stmt.close();
				            respuesta="Producto creado exitosamente, codigo 200";
			        	}else {
			        		respuesta="Error al crear el producto, codigo 203";
			        	}
		                con.close();  
		            }
		        } catch (SQLException ex) {
		    		respuesta="Error durante la creaci??n del producto codigo 203";
		        }
        	}else {
        		respuesta=xmlRes;
        	}
        }else {
      		respuesta="El usuario no esta autorizado para procesar esta acci??n, codigo 203";
       	}
        return respuesta;
	}

    public String actualizarProd(String nom,String pas,String clavp,String nclavp,String nomp,String catp,float precd,String mard,String desd) {
    	Connection con = null;
        String respuesta="";
        String xmlRes="";
        int id_prod=0;
        pas=md5(pas);
        int nuser=contar("select count(*) from cata_usuarios where nombre_user='"+nom+"' and password_user='"+pas+"' and eliminado_user=0 and nivel_user=2");
        if (nuser>0) {
			int nprod=contar("select count(*) from cata_productos where clave_prod='"+clavp+"' and eliminado_prod=0");
	        if (nprod>0) {
	        	generaXML(nclavp, nomp, catp, precd, mard, desd);
	        	xmlRes=getValidar("09_RegistroProductos");
	        	if (xmlRes=="#") {
	        		try {
			            con =  DriverManager.getConnection(url, username, password);
			            if (con != null) {
			            	stmt=con.createStatement();
			                query="update cata_productos set ";
			                query=query+"clave_prod='"+nclavp+"',nombre_prod='"+nomp+"',categoria_prod='"+catp+"' where clave_prod='"+clavp+"' and eliminado_prod=0";
			                stmt.executeUpdate(query);
			                stmt.close();
			                id_prod=regresaId("select id_prod_sistema from cata_productos where  clave_prod='"+clavp+"' and eliminado_prod=0","id_prod_sistema");
			                //para detalles
			                stmt=con.createStatement();
			                query="update cata_detalles set ";
			                query=query+"precio_prod="+precd+",marca_prod='"+mard+"',descripcion_prod='"+desd+"' where id_prod_sistema="+id_prod+" and eliminado_prod=0";
			                stmt.executeUpdate(query);
			                stmt.close();
				            respuesta="Producto actualizado exitosamente, codigo 200";
			                con.close();  
			            }
			        } catch (SQLException ex) {
			        	respuesta ="Error al actualizar el producto. codigo 301";
			        }
	        	}else {
	        		respuesta=xmlRes;
	        	}
	       	}else {
	       		respuesta="El producto no existe en la base de datos, codigo 203";
	       	}
       	}else {
      		respuesta="El usuario no esta autorizado para procesar esta acci??n, codigo 203";
       	}  
	 return respuesta;
	}
    
    public String eliminarProd(String nom,String pas,String clavp) {
    	Connection con = null;
        String respuesta="";
        int id_prod=0;
        pas=md5(pas);
        int nuser=contar("select count(*) from cata_usuarios where nombre_user='"+nom+"' and password_user='"+pas+"' and eliminado_user=0 and nivel_user=2");
        if (nuser>0) {
		    int nprod=contar("select count(*) from cata_productos where clave_prod='"+clavp+"' and eliminado_prod=0");
	        if (nprod>0) {
		        try {
		            con =  DriverManager.getConnection(url, username, password);
		            if (con != null) {
		            	id_prod=regresaId("select id_prod_sistema from cata_productos where  clave_prod='"+clavp+"' and eliminado_prod=0","id_prod_sistema");
			            stmt=con.createStatement();
		            	query="update cata_productos set eliminado_prod=1 where clave_prod='"+clavp+"' and eliminado_prod=0";
		                stmt.executeUpdate(query);
		                stmt.close();
		                //para detalles
		                stmt=con.createStatement();
		                query="update cata_detalles set eliminado_prod=1 where id_prod_sistema="+id_prod+" and eliminado_prod=0";
		                stmt.executeUpdate(query);
		                stmt.close();
			            respuesta="Producto eliminado exitosamente, codigo 200";
		                con.close();  
		            }
		        } catch (SQLException ex) {
		        	respuesta ="Error al eliminar el producto. codigo 301";
		        }
	       	}else {
	       		respuesta="El producto no existe en la base de datos, codigo 203";
	       	}
       	}else {
       		respuesta="El usuario no esta autorizado para procesar esta acci??n, codigo 203";
       	}
	    return respuesta;
	}

    
    public String borrarProd(String nom,String pas,String clavp) {
    	Connection con = null;
        String respuesta="";
        int id_prod=0;
        pas=md5(pas);
        int nuser=contar("select count(*) from cata_usuarios where nombre_user='"+nom+"' and password_user='"+pas+"' and eliminado_user=0 and nivel_user=1");
        if (nuser>0) {
	        int nprod=contar("select count(*) from cata_productos where clave_prod='"+clavp+"' and eliminado_prod=0");
	        if (nprod>0) {
		        try {
		            con =  DriverManager.getConnection(url, username, password);
		            if (con != null) {
		            	id_prod=regresaId("select id_prod_sistema from cata_productos where  clave_prod='"+clavp+"' and eliminado_prod=0","id_prod_sistema");
			            stmt=con.createStatement();
		            	query="delete from cata_productos where clave_prod='"+clavp+"'";
		                stmt.executeUpdate(query);
		                stmt.close();
		                //para detalles
		                stmt=con.createStatement();
		                query="delete from cata_detalles where id_prod_sistema="+id_prod;
		                stmt.executeUpdate(query);
		                stmt.close();
			            respuesta="Producto borrado permanentemente, codigo 200";
		                con.close();  
		            }
		        } catch (SQLException ex) {
		        	respuesta ="Error al borrar el producto. codigo 301";
		        }
	       	}else {
	       		respuesta="El producto no existe en la base de datos, codigo 203";
	       	}
       	}else {
       		respuesta="Usuario no autorizado para realizar los cambios, codigo 203";
       	}
	    return respuesta;
	}

    
	public String obtenerProductos(String nom,String pas,String cat) {
		String respuesta="";
        String pasa=md5(pas);
        int nuser=contar("select count(*) from cata_usuarios where nombre_user='"+nom+"' and password_user='"+pasa+"' and eliminado_user=0 and nivel_user!=1");
        if (nuser>0) {
		    try {
				RespProd res = client.getProd(nom,pas,cat); 
				String fecha="fecha: " +res.getFecha();
				String code="codigo: " +res.getCode();
				String message="mensaje: " +res.getMessage();
				String lista=res.getLista();
				if (lista.length()>0) {
					if (lista.contains("<?xml")) {
						xmlP=true;
						strP=lista;
					}
				}
				lista="productos: " + lista;;
				String stock="stock: " +res.getStock();
				respuesta=  fecha+"\r\n"+code+"\r\n"+message+"\r\n"+lista+"\r\n"+stock;
			}catch  (RemoteException e) {}
       	}else {
       		respuesta="Usuario no autorizado para hacer consultas, codigo 203";
       	}
        return respuesta;
	}
	public String obtenerDetalles(String nom,String pas,String clav) {
		String respuesta="";
        String pasa=md5(pas);
        int nuser=contar("select count(*) from cata_usuarios where nombre_user='"+nom+"' and password_user='"+pasa+"' and eliminado_user=0 and nivel_user!=1");
        if (nuser>0) {
			try {
				RespDetail res = client.getDetails(nom,pas,clav); 
				String fecha="fecha: " +res.getFecha();
				String code="codigo: " +res.getCode();
				String message="mensaje: " +res.getMessage();
				String lista=res.getProducto();
				if (lista.length()>0) {
					if (lista.contains("<?xml")) {
						xmlD=true;
						strD=lista;
					}
				}
				lista="producto: " + lista;;
				respuesta =  fecha+"\r\n"+code+"\r\n"+message+"\r\n"+lista;
			}catch  (RemoteException e) {}
    	}else {
       		respuesta="Usuario no autorizado para hacer consultas, codigo 203";
       	}
        return respuesta;
	}	
	
	public String getValidar(String u) {
		String rr="#";
		WebServicesBUAP1PortTypeProxy client2 = new WebServicesBUAP1PortTypeProxy();
		try { 
			RespuestaXML res = client2.validaXML(1); 
			//String fecha="fecha: " +res.getFecha();
			String code="codigo: " +res.getCodigo();
			String message="Error de validaci??n xml: " +res.getMensaje()+code ;
			//System.out.println( fecha);
			//System.out.println( code);
			//System.out.println( message);
			if (res.getCodigo()=="302") {
				rr= message;
			}
		}catch  (RemoteException e) {}
		return rr;
	}

	
	
    public static void main(String args[]){  
		 serwew s = new serwew();
		//System.out.print(s.consultar());
		//System.out.print(s.registrarUsuario("Ramiro", "Ramiro", 1));
		 //System.out.print(s.contar("select count(*) from cata_usuarios where nombre_user='Ramiro' and password_user='d67326a22642a324aa1b0745f2f17abb'"));
		//System.out.print(s.cambiarPwd("Ramiro", "ramiro", "ramiro"));
		//System.out.print(serwew.md5("eutiquio"));
		//System.out.print(s.eliminarUsuario("Ramiro", "jorge"));
		//System.out.print(s.registrarProd("libro4","100 a??os de soledad", "libros", 3, 532, "Limusa", "Gabriel Garcia Marquez"));
		//System.out.print(s.registrarProd("libro5","hill", "libros", 1, 50, "Mx_Ed", "ID"));
		//System.out.print(s.registrarProd("jorge","jorge","libro6","hillareu", "libros", 150, "_Ed", "IasdfasdfD"));
		 //System.out.print(s.actualizarProd("libro5", "libro5", "super hill", "libros", 2, 60, "MX_ED_plus", "yo lo escribi jajaja"));
		//System.out.print(s.eliminarProd("libro5"));	
		//System.out.print(s.borrarProd(, pas, clavp);
		 System.out.print(s.obtenerDetalles("marisol_cliente", "cliente", "liob01"));
		 //System.out.print(s.obtenerProductos("jorge", "jorge", "libros"));
		// System.out.println(s.generaXML("clave1", "nombre1", "categoria1", 100, "marca1", "descrp1"));
		 //System.out.println(s.getValidar("09_RegistroProductos"));
    }
}
