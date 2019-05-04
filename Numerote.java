import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Numerote {
	private boolean signo;
	private byte[] numerote;
	
	public Numerote() {
		this.signo=true;
		this.numerote=new byte[1];
		this.numerote[0]=0;
	}
	public Numerote(String a) {
		char valor=a.charAt(0);
		String signo=String.valueOf(valor);
		char valor1; 
		int digito; 
		String convertChar; 
		String invertido = "";
		byte numero;
		if(signo.equals("-")) {
			this.signo=false;
			this.numerote=new byte[a.length()-1];
			for(int i=a.length()-1;i>=1;i--) {
				invertido+=a.charAt(i);
			}
			for(int i=this.numerote.length-1;i>=0;i--) { 
				valor1=invertido.charAt(i); 
				convertChar=String.valueOf(valor1); 
				digito=Integer.parseInt(convertChar);
				numero=(byte)digito;
				this.numerote[i]=numero;
			}
		}else {
			this.signo=true;
			this.numerote=new byte[a.length()];
			for(int i=a.length()-1;i>=0;i--) {
				invertido+=a.charAt(i);
			}
			for(int i=this.numerote.length-1;i>=0;i--) { 
				valor1=invertido.charAt(i); 
				convertChar=String.valueOf(valor1); 
				digito=Integer.parseInt(convertChar);
				numero=(byte)digito;
				this.numerote[i]=numero;
			}
		}	
	}
	public Numerote suma(Numerote a) {
		int acumuladorA=0;
		int acumuladorB=0;
		int baseA=1;
		int baseB=1;
		int resultado=0;
		for(int i=0;i<a.numerote.length;i++) {
			acumuladorA+=baseA*a.numerote[i];
			baseA*=10;
		}
		for(int i=0;i<this.numerote.length;i++) {
			acumuladorB+=baseB*this.numerote[i];
			baseB*=10;
		}
		resultado=acumuladorB+acumuladorA;
		return new Numerote(""+resultado);
	}
	public Numerote resta(Numerote a) {
		int acumuladorA=0;
		int acumuladorB=0;
		int baseA=1;
		int baseB=1;
		int resultado=0;
		for(int i=0;i<a.numerote.length;i++) {
			acumuladorA+=baseA*a.numerote[i];
			baseA*=10;
		}
		for(int i=0;i<this.numerote.length;i++) {
			acumuladorB+=baseB*this.numerote[i];
			baseB*=10;
		}
		resultado=acumuladorB-acumuladorA;
		return new Numerote(""+resultado);
	}
	public Numerote multiplica(Numerote a) {
		int acumuladorA=0;
		int acumuladorB=0;
		int baseA=1;
		int baseB=1;
		int resultado=0;
		for(int i=0;i<a.numerote.length;i++) {
			acumuladorA+=baseA*a.numerote[i];
			baseA*=10;
		}
		for(int i=0;i<this.numerote.length;i++) {
			acumuladorB+=baseB*this.numerote[i];
			baseB*=10;
		}
		resultado=acumuladorB*acumuladorA;
		return new Numerote(""+resultado);
	}
	public String toString() {
		String numero="";
		for(int i=numerote.length-1;i>=0;i--) {
			numero+=numerote[i];	
		}
		if(this.signo==true) {
			return ""+numero;
		}else {
			return "-"+numero;
		}
	}
	public static void ejecutaArchivo(String entrada, String salida) {
		try {
			String linea,
				   numero1,
				   numero2,
				   operacion;
			StringTokenizer st;
			BufferedReader bf=new BufferedReader(new FileReader(entrada));
			PrintWriter pw=new PrintWriter(new FileWriter(salida));
			while((linea=bf.readLine())!=null) {
				st=new StringTokenizer(linea);
				numero1=st.nextToken();
				numero2=st.nextToken();
				operacion=st.nextToken();
				if(operacion.equals("s")) {
					Numerote c=new Numerote(numero1);
					Numerote d=new Numerote(numero2);
					pw.println(c.suma(d));
				}else if(operacion.equals("r")){
					Numerote c=new Numerote(numero1);
					Numerote d=new Numerote(numero2);
					pw.println(c.resta(d));
				}else {
					Numerote c=new Numerote(numero1);
					Numerote d=new Numerote(numero2);
					pw.println(c.multiplica(d));
				}
				
			}
			pw.close();
			bf.close();
			System.out.println("Fin");
		}catch(FileNotFoundException ex){
			System.out.println("No se encontró el archivo");
		}catch(IOException ex){
			System.out.println("No se pudo leer el archivo");
		}
	}
	public static void main(String[] args) {
		ejecutaArchivo("entrada.txt", "salida.txt");
	}
}

