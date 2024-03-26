package com.growmore;

import java.text.DecimalFormat;
import java.util.Scanner;

public class CurrencyConverter {
	public static void main(String[] args) {
		double rupee,dollar,euro,code,pound,dirham;
		DecimalFormat f=new DecimalFormat("##.####");
		Scanner s=new Scanner(System.in);
		System.out.println("Welcome to Real-Time Currency Converter!\nEnter the currencycode\n1:Rupees\n2:Dollars\n3:Euros\n4:Pounds\n5:UAE Dirham");
		code=s.nextInt();
		
		if(code==1) {
			System.out.println("Enter the amount in Rupees:");
			rupee=s.nextDouble();
			dollar=rupee/82.90;
			System.out.println("Dollars:"+f.format(dollar));
			euro=rupee/89.81;
			System.out.println("Euros:"+f.format(euro));
			pound=rupee/104.90;
			System.out.println("Pounds:"+f.format(pound));
			dirham=rupee/22.57;
			System.out.println("UAE Dirham:"+f.format(dirham));
		}
		else if(code==2) {
			System.out.println("Enter the amount in Dollars:");
			dollar=s.nextDouble();
			rupee=dollar/0.012;
			System.out.println("Rupees:"+f.format(rupee));
			euro=dollar/1.08;
			System.out.println("Euros:"+f.format(euro));
			pound=dollar/1.26;
			System.out.println("Pounds:"+f.format(pound));
			dirham=dollar/0.27;
			System.out.println("UAE Dirham:"+f.format(dirham));
		}
		else if(code==3) {
			System.out.println("Enter the amount in Euros:");
			euro=s.nextDouble();
			rupee=euro/0.011;
			System.out.println("Rupees;"+f.format(rupee));
			dollar=euro/0.92;
			System.out.println("Dollars:"+f.format(dollar));
			pound=euro/1.17;
			System.out.println("Pounds:"+f.format(pound));
			dirham=euro/0.25;
			System.out.println("UAE Dirham:"+f.format(dirham));
		}
		else if(code==4) {
			System.out.println("Enter the amount in Pounds:");
			pound=s.nextDouble();
			rupee=pound/0.0095;
			System.out.println("Rupees:"+f.format(rupee));
			dollar=pound/1.27;
			System.out.println("Euros:"+f.format(dollar));
			euro=pound/0.86;
			System.out.println("Pounds:"+f.format(euro));
			dirham=pound/0.22;
			System.out.println("UAE Dirham:"+f.format(dirham));
		}
		else if(code==5) {
			System.out.println("Enter the amount in UAE Dirham:");
			dirham=s.nextDouble();
			rupee=dirham/0.044;
			System.out.println("Rupees:"+f.format(rupee));
			dollar=dirham/3.67;
			System.out.println("Euros:"+f.format(dollar));
			euro=dirham/3.98;
			System.out.println("Pounds:"+f.format(euro));
			pound=dirham/4.65;
			System.out.println("UAE Dirham:"+f.format(pound));
		}
		else {
			System.out.println("Invalid Code");
		}
	}
}