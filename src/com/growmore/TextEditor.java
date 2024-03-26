package com.growmore;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextEditor extends JFrame implements ActionListener{
	private JTextArea textArea;
	private JFileChooser fileChooser;
	
	public TextEditor() {
		setTitle("Java Text Editor");
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		textArea=new JTextArea();
		JScrollPane scrollPane=new JScrollPane(textArea);
		add(scrollPane,BorderLayout.CENTER);
		
		JMenuBar menuBar=new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMenu=new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem openMenuItem=new JMenuItem("Open");
		openMenuItem.addActionListener(this);
		fileMenu.add(openMenuItem);
		
		JMenuItem saveMenuItem=new JMenuItem("Save");
		saveMenuItem.addActionListener(this);
		fileMenu.add(saveMenuItem);
		
		JMenuItem printMenuItem=new JMenuItem("Print");
		printMenuItem.addActionListener(this);
		fileMenu.add(printMenuItem);
		
		JMenuItem closeMenuItem=new JMenuItem("Close");
		closeMenuItem.addActionListener(this);
		fileMenu.add(closeMenuItem);
		
		JMenu editMenu=new JMenu("Edit");
		menuBar.add(editMenu);
		JMenuItem cutMenuItem=new JMenuItem("Cut");
		cutMenuItem.addActionListener(this);
		editMenu.add(cutMenuItem);
		
		JMenuItem copyMenuItem=new JMenuItem("Copy");
		copyMenuItem.addActionListener(this);
		editMenu.add(copyMenuItem);
		
		JMenuItem pasteMenuItem=new JMenuItem("Paste");
		pasteMenuItem.addActionListener(this);
		editMenu.add(pasteMenuItem);
		
		JButton saveAndSubmitButton=new JButton("Save and Submit");
		saveAndSubmitButton.addActionListener(this);
		add(saveAndSubmitButton,BorderLayout.SOUTH);
		
		fileChooser=new JFileChooser();
		
		setVisible(true);
		}
	
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if(command.equals("Open")) {
			int returnValue=fileChooser.showOpenDialog(this);
			if(returnValue==JFileChooser.APPROVE_OPTION) {
				File selectedFile=fileChooser.getSelectedFile();
				readFile(selectedFile);
			}
		}
		else if(command.equals("Save")) {
			int returnValue=fileChooser.showSaveDialog(this);
			if(returnValue==JFileChooser.APPROVE_OPTION) {
				File selectedFile=fileChooser.getSelectedFile();
				saveFile(selectedFile);
			}
		}
		else if(command.equals("Print")) {
			try {
				textArea.print();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		else if(command.equals("Close")) {
			dispose();
		}
		else if(command.equals("Cut")) {
			textArea.cut();
		}
		else if(command.equals("Copy")) {
			textArea.copy();
		}
		else if(command.equals("Paste")) {
			textArea.paste();
		}
		else if(command.equals("Save and Submit")) {
			int returnValue=fileChooser.showSaveDialog(this);
			if(returnValue==JFileChooser.APPROVE_OPTION) {
				File selectedFile=fileChooser.getSelectedFile();
				saveFile(selectedFile);
				dispose();
			}
		}
	}
	
	private void readFile(File file) {
		try {
			FileReader reader=new FileReader(file);
			BufferedReader bufferedReader=new BufferedReader(reader);
			String line;
			textArea.setText("");
			while((line=bufferedReader.readLine())!=null) {
				textArea.append(line+"\n");
			}
			bufferedReader.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void saveFile(File file) {
		try {
			FileWriter writer=new FileWriter(file);
			writer.write(textArea.getText());
			writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new TextEditor();
	}
}