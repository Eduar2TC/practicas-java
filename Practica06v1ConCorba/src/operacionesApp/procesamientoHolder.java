package operacionesApp;

/**
* operacionesApp/procesamientoHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from operaciones.idl
* s?bado 28 de abril de 2018 02:57:31 AM CDT
*/

public final class procesamientoHolder implements org.omg.CORBA.portable.Streamable
{
  public operacionesApp.procesamiento value = null;

  public procesamientoHolder ()
  {
  }

  public procesamientoHolder (operacionesApp.procesamiento initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = operacionesApp.procesamientoHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    operacionesApp.procesamientoHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return operacionesApp.procesamientoHelper.type ();
  }

}
