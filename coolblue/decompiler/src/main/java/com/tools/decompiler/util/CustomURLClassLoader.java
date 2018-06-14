package com.tools.decompiler.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

public class CustomURLClassLoader extends URLClassLoader{

	public CustomURLClassLoader(URL[] arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	public Class loadClass(String name, boolean resolve) throws ClassNotFoundException
	{
		 // respect the java.* packages.
	    if( name.startsWith("java.")) {
	      return super.loadClass(name, resolve);
	    }
	    else {
	      // see if we have already loaded the class.
	      Class<?> c = findLoadedClass(name);
	      if( c != null ) return c;

	      // the class is not loaded yet.  Since the parent class loader has all of the
	      // definitions that we need, we can use it as our source for classes.
	      System.out.println(name);
	      //System.out.println(name.replaceAll("\\.", "/"));
	      InputStream in = null;
	      try {
	        // get the input stream, throwing ClassNotFound if there is no resource.
	        in = new FileInputStream("/home/ec2-user/temp/" + name.replaceAll("\\.", "/") +".class");
	        if( in == null ) throw new ClassNotFoundException("Could not find "+name);
	        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

	        int nRead;
	        byte[] data = new byte[16384];

	        while ((nRead = in.read(data, 0, data.length)) != -1) {
	          buffer.write(data, 0, nRead);
	        }

	        buffer.flush();
	        // read all of the bytes and define the class.
	        byte[] cBytes = buffer.toByteArray();
	        c = defineClass(name, cBytes, 0, cBytes.length);
	        System.out.println("Class loaded " + c.getName());
	        if( resolve ) resolveClass(c);
	        return c;
	      } catch (Exception e) {
	        throw new ClassNotFoundException("Could not load "+name, e);
	      }
	      finally {
	    	  try
	    	  {
	        in.close();
	    	  }
	    	  catch (Exception exc)
	    	  {
	    		  exc.printStackTrace();
	    	  }
	      }
	    }
	  }
	}


