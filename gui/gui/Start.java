package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

// @SuppressWarnings("serial")
public class Start extends JPanel {

	private JButton next = new JButton("NEXT -->");
	public Color c5 = new Color(0, 0, 0);
	public Color c6 = new Color(255, 215,0 );

	public Start(JFrame jFrame, int delay, int s) {
		Plot drawPanel = new Plot(jFrame, s);
		setSize(1500,700);
		setBackground(Color.BLACK);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel title[] = { new JLabel(" "), new JLabel("OOM Project"), new JLabel("Interactive polygon area calculator"),
				new JLabel(" ") };
		JLabel members[] = { new JLabel("YUVRAJ JINDAL - IIT2021161"), new JLabel("ADITYA SINGH TOMAR - IIT2021208"),
				new JLabel("MILAN PARMAR - IIT2021121"), new JLabel(" "), new JLabel(" ") };
		for (int i = 0; i < 2; i++) {
			title[i].setAlignmentX(CENTER_ALIGNMENT);
			title[i].setAlignmentY(CENTER_ALIGNMENT);
			title[i].setFont(new Font("Sans-serif", Font.BOLD, 80));
			title[i].setForeground(drawPanel.c6);
			add(title[i]);
		}

		for (int i = 2; i < 4; i++) {
			title[i].setAlignmentX(CENTER_ALIGNMENT);
			title[i].setAlignmentY(CENTER_ALIGNMENT);
			title[i].setFont(new Font("Sans-serif", Font.BOLD, 40));
			title[i].setForeground(drawPanel.c6);
			add(title[i]);
		}

		for (int i = 0; i < 4; i++) {
			members[i].setAlignmentX(CENTER_ALIGNMENT);
			members[i].setFont(new Font("Sans-serif", Font.BOLD, 30));
			members[i].setAlignmentY(CENTER_ALIGNMENT);
			members[i].setForeground(drawPanel.c6);
			add(members[i]);
		}

		next.setFont(new Font("Sans-serif", Font.BOLD, 30));
		next.setForeground(c5);
		next.setBackground(c6);
		next.setSize(200, 50);
		add(next,BorderLayout.EAST);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jFrame.getContentPane().removeAll();
				jFrame.setSize(drawPanel.getSize());
				jFrame.getContentPane().add(drawPanel);
				jFrame.revalidate();
				jFrame.repaint();
			}
		});

	

	int delayTime = delay*100000000;
		ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		         jFrame.getContentPane().removeAll();
		         jFrame.setSize(drawPanel.getSize());
		         jFrame.getContentPane().add(drawPanel);
		         jFrame.revalidate();
		         jFrame.repaint();
		      }
		};
		Timer timer = new Timer(delayTime, taskPerformer);
		timer.start();
		timer.setRepeats(false);
	}
	public static void main(String[] args) {

		JFrame jFrame = new JFrame();
		jFrame.setTitle("Area Calculator");

		Start info = new Start(jFrame, 4, 250);
		jFrame.setSize(info.getSize());
		// jFrame.setResizable(false);

		Container cPane = jFrame.getContentPane();
		cPane.add(info);
		jFrame.setVisible(true);
		jFrame.setLocationRelativeTo(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
