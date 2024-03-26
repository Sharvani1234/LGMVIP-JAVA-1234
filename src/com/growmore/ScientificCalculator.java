package com.growmore;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ScientificCalculator extends JFrame implements ActionListener{
	private JTextField displayField;
	
	public ScientificCalculator() {
		setTitle("Scientific Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,400);
		setLayout(new BorderLayout());
		
		displayField=new JTextField();
		displayField.setEditable(false);
		add(displayField,BorderLayout.NORTH);
		
		JPanel buttonPanel=new JPanel(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(5,5,5,5);
		
		String[] buttonLabels= {
				"7","8","9","/",
				"4","5","6","*",
				"1","2","3","-",
				"0",".","=","+",
				"sin","cos","tan","log",
				"\u232b","C"
		};
		
		int gridx=0;
		int gridy=0;
		
		for(String label:buttonLabels) {
			JButton button=new JButton(label);
			button.addActionListener(this);
			gbc.gridx=gridx;
			gbc.gridy=gridy;
			buttonPanel.add(button,gbc);
			gridx++;
			if(gridx>3) {
				gridx=0;
				gridy++;
			}
		}
		
		add(buttonPanel,BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if(command.equals("=")) {
			try {
				String result=evaluateExpression(displayField.getText());
				displayField.setText(result);
			}
			catch(ArithmeticException ex) {
				displayField.setText("Error:"+ex.getMessage());
			}
		}
		else if(command.equals("\u232b")) {
			String currentText=displayField.getText();
			if(!currentText.isEmpty()) {
				displayField.setText(currentText.substring(0,currentText.length()-1));
			}
		}
		else if(command.equals("C")) {
			displayField.setText("");
		}
		else {
			displayField.setText(displayField.getText()+command);
		}
	}
	
	private String evaluateExpression(String expression) {
		try {
			if(expression.contains("sin")) {
				double value=Double.parseDouble(expression.replace("sin", ""));
				return String.valueOf(Math.sin(Math.toRadians(value)));
			}
			else if(expression.contains("cos")) {
				double value=Double.parseDouble(expression.replace("cos", ""));
				return String.valueOf(Math.cos(Math.toRadians(value)));
			}
			else if(expression.contains("tan")) {
				double value=Double.parseDouble(expression.replace("tan", ""));
				return String.valueOf(Math.tan(Math.toRadians(value)));
			}
			else if(expression.contains("log")) {
				double value=Double.parseDouble(expression.replace("log", ""));
				return String.valueOf(Math.log10(value));
			}
			else {
				return String.valueOf(evalArithmeticExpression(expression));
			}
		}
		catch(NumberFormatException e) {
			return "Error:Invalid expression";
		}
	}
	
	private double evalArithmeticExpression(String expression) {
		String[] parts=expression.split("(?<=[-+*/])|(?=[-+*/])");
		double result=Double.parseDouble(parts[0]);
		for(int i=1;i<parts.length;i+=2) {
			String operator=parts[i];
			double operand=Double.parseDouble(parts[i+1]);
			switch(operator) {
			case "+":
				result+=operand;
				break;
			case "-":
				result-=operand;
				break;
			case "*":
				result*=operand;
				break;
			case "/":
				if(operand==0)throw new ArithmeticException("Division by zero");
				result/=operand;
				break;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(ScientificCalculator::new);
	}
}