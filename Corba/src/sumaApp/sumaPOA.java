package sumaApp;


/**
* sumaApp/sumaPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from suma.idl
* viernes 20 de abril de 2018 04:14:20 PM CDT
*/

public abstract class sumaPOA extends org.omg.PortableServer.Servant
 implements sumaApp.sumaOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("sumar", new java.lang.Integer (0));
    _methods.put ("shutdown", new java.lang.Integer (1));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // sumaApp/suma/sumar
       {
         int operador1 = in.read_long ();
         int operador2 = in.read_long ();
         int $result = (int)0;
         $result = this.sumar (operador1, operador2);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 1:  // sumaApp/suma/shutdown
       {
         this.shutdown ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:sumaApp/suma:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public suma _this() 
  {
    return sumaHelper.narrow(
    super._this_object());
  }

  public suma _this(org.omg.CORBA.ORB orb) 
  {
    return sumaHelper.narrow(
    super._this_object(orb));
  }


} // class sumaPOA
