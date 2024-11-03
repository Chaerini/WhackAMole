import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;






public class Ddg extends JFrame implements ActionListener,Runnable{
	class MYP extends JPanel{
		BufferedImage img = null;
		
		public MYP() {
		setBackground(Color.yellow);
		try {
		img = ImageIO.read(new File("dig.png"));
		}catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
		public void paintComponent(Graphics g) {
			
			
				super.paintComponent(g);
				g.drawImage(img,0,0,this.getWidth(),this.getHeight(),this);
			
		}
		
	}
	 JButton start = new JButton("시작");
	 JButton end = new JButton("종료");
	private JButton btn [] =  new  JButton [9];
	private JLabel JS = new JLabel("점수 : 0");
	private JLabel T = new JLabel("시간 => 0:20");
	
	ImageIcon ic= new ImageIcon("dig.png");
	ImageIcon ic2= new ImageIcon("null.png");
	
	JPanel p1= new JPanel();
	JPanel p2= new JPanel();
	JPanel p3= new JPanel();
	int j=0,j2=0,j3=0;
	int CNT = -1;
	int n =20;
	
	
	
	
	

	public Ddg() {			
		
		
		setTitle("두더지 잡기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		
		
		p1 = new JPanel();
		add(p1,BorderLayout.CENTER);
		add(T,BorderLayout.NORTH);
		p1.setLayout(new GridLayout(3,3));
		for(int i =0;i<btn.length;i++)
		{
			btn[i] = new JButton("");
			p1.add(btn[i],BorderLayout.CENTER);
			btn[i].setIcon(ic2);
			btn[i].setBorderPainted(false);
			btn[i].setFocusPainted(false);
			btn[i].setBackground(Color.white);
		
		}
		add(p2,BorderLayout.SOUTH);
		p2.setLayout(new GridLayout(1,2));
		p2.add(JS);
		p2.add(p3);
		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p3.add(start);
		p3.add(end);
		
		p1.setBackground(Color.DARK_GRAY);
		p2.setBackground(Color.LIGHT_GRAY);
		p3.setBackground(Color.blue);
		start();
				
				
				
		setSize(500,500);
		setVisible(true);
	
		
		
		
		
		
		
	}
	
	
	public void start() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start.addActionListener(this);
		end.addActionListener(this);
		for(int i =0;i<btn.length;i++) {
			btn[i].setText("");
			btn[i].addActionListener(this);
		}	
		
		
		
	}

	public static void main(String[] args) {
		new Ddg();
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==start) {
			Thread th = new Thread(this);
			th.start();
			on_button();
			
			
			random(0);
		}else if (e.getSource() == end){
			System.exit(0);
		}
		
		for(int i=0;i<btn.length;i++) {
			if(e.getSource()==btn[i])
				random(i);
		}
	}

	


	private void random(int i) {
		if (i != j) return;
		CNT++;
		btn[j].setIcon(ic2);
		j = (int)(Math.random() * 9);
		btn[j].setIcon(ic);
		JS.setText("점수 : " + CNT);
		
	}

	public void run() {
		 n=20;
		while(true) {
			try {
				Thread.sleep(1000);
			}catch (InterruptedException e) {};
			n--;
			if (n == 0) {
				T.setText("game over");
								
				off_button();
				break;
			}
			T.setText("시간 => 0:"+n);
		}
			
		
	}
	private void on_button() {
		for(int i=0; i<9; ++i){ 
			btn[i].setEnabled(true);
		}
	}

	private void off_button() {
		for(int i=0;i<btn.length;i++)
			btn[i].setEnabled(false);
		
		
		//T.setText("" + n);
		
		
		
	}

	
}
