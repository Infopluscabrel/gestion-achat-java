package com.gestionachat4.utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.Basic;
import javax.persistence.Column;

public class MyResponseAchat {

	  
	  private short codeproduit ;
	  
	 private  int qte ; 
	 
	 private String label;
	    
	 private BigDecimal pu ;

		public MyResponseAchat() {
		
		}


		public MyResponseAchat(short codeproduit,String label,BigDecimal pu , int qte ) {
			// TODO Auto-generated constructor stub
			
			this.codeproduit = codeproduit ;
			this.pu = pu ;
			this.qte =qte;
			this.label = label;
			
		}



		@Override
		public String toString() {
			return "MyResponseAchat [codeproduit=" + codeproduit + ", qte=" + qte + ", label=" + label + ", pu=" + pu
					+ "]";
		}


		public int getCodeachat() {
			
			 Random rand = new Random(); 
		      // Setting the upper bound to generate the
		      // random numbers in specific range
		      int upperbound = 150;
		      // Generating random values from 0 - 24 
		      // using nextInt()
		      int int_random = rand.nextInt(upperbound); 
			return int_random;
		}

		
		public short getCodeproduit() {
			return codeproduit;
		}

		public void setCodeproduit(short codeproduit) {
			this.codeproduit = codeproduit;
		}

		public int getQte() {
			return qte;
		}

		public void setQte(int qte) {
			this.qte = qte;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

	
		public List<MyResponseAchat> getElements() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getAlias() {
			// TODO Auto-generated method stub
			return null;
		}
	    
	    
	 
}
