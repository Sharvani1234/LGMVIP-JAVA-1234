package com.growmore;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;

public class TicTacToe extends JFrame implements ActionListener{
	private JButton[] buttons;
	private JLabel label;
	private boolean player1Turn=true;
	private boolean gameEnded=false;
	
	public TicTacToe(){
		setTitle("Tic Tac Toe");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,300);
		setLayout(new BorderLayout());
		
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(3,3));
		
		buttons=new JButton[9];
		for(int i=0;i<9;i++) {
			buttons[i]=new JButton();
			buttons[i].setFont(new Font(Font.SANS_SERIF,Font.BOLD,50));
			buttons[i].addActionListener(this);
			panel.add(buttons[i]);
		}
		
		label=new JLabel("Player 1's turn");
		add(panel,BorderLayout.CENTER);
		add(label,BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(gameEnded)
			return;
		
		JButton buttonClicked=(JButton)e.getSource();
		if(!buttonClicked.getText().equals(""))
			return;
		
		if(player1Turn)
			buttonClicked.setText("X");
		else
			buttonClicked.setText("O");
		
		if(checkForWin()) {
			label.setText((player1Turn?" Player1 ":" Player2 ")+" wins!");
			gameEnded=true;
		}
		else if(isBoardFull()) {
			label.setText("It's a draw!");
			gameEnded=true;
		}
		else {
			player1Turn=!player1Turn;
			label.setText((player1Turn?" Player1 ":" Player2 ")+" 's turn");
		}
	}
	
	private boolean checkForWin() {
		String[] board=new String[9];
		for(int i=0;i<9;i++) {
			board[i]=buttons[i].getText();
		}
		
		for(int i=0;i<3;i++) {
			if(!board[i*3].equals("") && board[i*3].equals(board[i*3+1]) && board[i*3].equals(board[i*3+2]))
				return true;
			if(!board[i].equals("") && board[i].equals(board[i+3]) && board[i].equals(board[i+6]))
				return true;
		}
		
		if(!board[0].equals("") && board[0].equals(board[4]) && board[0].equals(board[8]))
			return true;
		if(!board[2].equals("") && board[2].equals(board[4]) && board[2].equals(board[6]))
			return true;
		
		return false;
	}
	
	private boolean isBoardFull() {
		for(int i=0;i<9;i++) {
			if(buttons[i].getText().equals(""))
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		new TicTacToe();
	}
}