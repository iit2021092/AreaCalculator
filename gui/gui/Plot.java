
package gui;

import ExtraFunctions.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
// import javax.swing.ViewportLayout;

// import java.awt.BasicStroke;
// import java.awt.BorderLayout;
// import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

@SuppressWarnings("serial")
public class Plot extends JPanel implements MouseMotionListener {
	private getAngles polygon = new getAngles(); 		// This object stores all the properties of the polygon drawn on screen
														// This object stores all the properties of the polygon drawn on screen
	private int verticesSize = 22;
	private Rectangle[] vertices = new Rectangle[4]; 		// Vertices are invisible squares having sides of length
															// verticesSize that allow us to drag polygon's vertices
	private Polygon poly;
	
	private int currentVertexIndex = -1; 				// This stores the index of vertex currently being dragged by the user
	public Color c5 = new Color(0, 0, 0);
	public Color c6 = new Color(255, 215,0 );
	private JLabel areaLabel = new JLabel();
	private JTextField t = new JTextField("Enter Required Side Length", 20);
	private JButton reset = new JButton("RESET");
	private JButton GOBack = new JButton("GO BACK");
	private JTextArea angleArea = new JTextArea();
	private int count180 = 0;

	public Plot(JFrame jFrame, int scale) {
		setSize(1500, 700);
		setBackground(c6);
		setLayout(null);

			int xP[] = {500,500,800,800};
			int yP[] = {200,500,500,200};
			polygon.setXs(xP);
			polygon.setYs(yP);
		// }
		polygon.setPoint();
		poly = new java.awt.Polygon(polygon.getXs(), polygon.getYs(), 4);

		areaLabel.setFont(new Font("Sans-serif", Font.BOLD, 30));
		areaLabel.setForeground(c5);
		areaLabel.setBackground(c6);
		areaLabel.setBounds(450,0,900,100);
		add(areaLabel);


		angleArea.setFont(new Font("Sans-serif", Font.BOLD, 30));
		angleArea.setForeground(c6);
		angleArea.setBackground(c5);
		// angleLabel.setLayout();
		angleArea.setEditable(false);
		angleArea.setBounds(1100,0,300,700);
		add(angleArea);

		
		reset.setFont(new Font("Sans-serif", Font.BOLD, 30));
		reset.setForeground(c6);
		reset.setBackground(c5);
		reset.setBounds(20, 400, 200, 50);
		add(reset);

		
		GOBack.setFont(new Font("Sans-serif", Font.BOLD, 30));
		GOBack.setForeground(c6);
		GOBack.setBackground(c5);
		GOBack.setBounds(20,200,200,50);
		add(GOBack);

		for (int i = 0; i < 4; i++) // This forLoop sets the initial locations(x,y) of all the vertices
		{
			Rectangle r = new Rectangle();
			r.setBounds((int) (polygon.getX(i) - verticesSize * 0.5), (int) (polygon.getY(i) - verticesSize * 0.5),
					verticesSize, verticesSize);
			vertices[i] = r;
		}

		areaLabel.setText("<html>Area = " + polygon.getArea() + "</html>");
		angleArea.setText("\n\n\n\n    Angles\n    A= "+ polygon.getAngle(0) + "\n    B= " + polygon.getAngle(1) + "\n    C= "
		        + polygon.getAngle(2) + "\n    D= "+ polygon.getAngle(3));

		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int val = 250;
				try {
					String name = t.getText();
					val = Integer.parseInt(name);
				} catch (NumberFormatException ex) {

				}
				jFrame.getContentPane().removeAll();
				Start info = new Start(jFrame, 0, val);
				jFrame.getContentPane().add(info);

			}

		});

		GOBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jFrame.getContentPane().removeAll();
				Start info = new Start(jFrame, 1, 250);
				jFrame.getContentPane().add(info);
				jFrame.revalidate();
				jFrame.repaint();
			}

		});

		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent me) { // This method implements the change of CursorStyle when mouse is
				// pressed over a vertex
				int x = me.getX();
				int y = me.getY();
				currentVertexIndex = getVertexIndex(x, y);

				if (getVertexIndex(x, y) >= 0) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				} else {
					setCursor(Cursor.getDefaultCursor());
				}
			}
		});

		addMouseMotionListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) { // Draws initial polygon and all of it's properties such as area,
		// lengths and angles on the Frame

		super.paintComponent(g);
		g.setColor(c6);

		for (int i = 0; i < 4; i++) {
			((Graphics2D) g).draw(vertices[i]);
		}

		g.setColor(c5);
		// ((Graphics2D) g).setStroke(new BasicStroke(polygonWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		((Graphics2D) g).draw(poly);
		((Graphics2D) g).setFont(new Font("courier", Font.BOLD, 15));
		((Graphics2D) g).drawString("A", (int) polygon.getX(0) - 15,
				(int) polygon.getY(0) - 5);
		((Graphics2D) g).drawString("B", (int) polygon.getX(1) + 5,
				(int) polygon.getY(1) - 5);
		((Graphics2D) g).drawString("C", (int) polygon.getX(2) + 5,
				(int) polygon.getY(2) + 15);
		((Graphics2D) g).drawString("D", (int) polygon.getX(3) - 15,
				(int) polygon.getY(3) + 15);
		((Graphics2D) g).drawString(polygon.GetLength(0) + " units", (int) polygon.getXMid(0) - 50,
				(int) polygon.getYMid(0) - 10);
		((Graphics2D) g).drawString(polygon.GetLength(1) + " units", (int) polygon.getXMid(1) + 10,
				(int) polygon.getYMid(1) + 5);
		((Graphics2D) g).drawString(polygon.GetLength(2) + " units", (int) polygon.getXMid(2) - 50,
				(int) polygon.getYMid(2) + 20);
		((Graphics2D) g).drawString(polygon.GetLength(3) + " units", (int) polygon.getXMid(3) - 110,
				(int) polygon.getYMid(3) + 5);
		((Graphics2D) g).setFont(new Font("courier", Font.BOLD, 25));
		ConvexAreaCheck();
		angleArea.setText("\n\n\n\n    Angles\n    A= "+ polygon.getAngle(0) + "\n    B= " + polygon.getAngle(1) + "\n    C= "
		        + polygon.getAngle(2) + "\n    D= "+ polygon.getAngle(3));
	}

	private int getVertexIndex(int x, int y) { // This functions returns the index of vertexSquare that contains (x,y)
		// else it returns -1
		for (int i = 0; i < 4; i++) {
			if (vertices[i].contains(x, y)) {
				return i;
			}
		}

		return -1;
	}

	public void mouseMoved(MouseEvent me) { // This method implements the change of CursorStyle when mouse is moved over
											// a vertex

		int x = me.getX();
		int y = me.getY();

		if (getVertexIndex(x, y) >= 0) {
			setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		} else
			setCursor(Cursor.getDefaultCursor());
	}

	public void mouseDragged(MouseEvent me) { // When a vertex is dragged, this method gets rid of initial polygon and
		// draws a polygon updated with its new properties and location
		int x = me.getX();
		int y = me.getY();

		if (getBounds().contains(x, y)) {
			if (currentVertexIndex >= 0) {

				Graphics g = getGraphics();
				// ((Graphics2D) g)
						// .setStroke(new BasicStroke(polygonWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				g.setColor(c6);
				((Graphics2D) g).setFont(new Font("courier", Font.BOLD, 15));
				((Graphics2D) g).draw(poly);
				((Graphics2D) g).drawString("A", (int) polygon.getX(0) - 15,
						(int) polygon.getY(0) - 5);
				((Graphics2D) g).drawString("B", (int) polygon.getX(1) + 5,
						(int) polygon.getY(1) - 5);
				((Graphics2D) g).drawString("C", (int) polygon.getX(2) + 5,
						(int) polygon.getY(2) + 15);
				((Graphics2D) g).drawString("D", (int) polygon.getX(3) - 15,
						(int) polygon.getY(3) + 15);
				((Graphics2D) g).drawString(polygon.GetLength(0) + " units", (int) polygon.getXMid(0) - 50,
						(int) polygon.getYMid(0) - 10);
				((Graphics2D) g).drawString(polygon.GetLength(1) + " units", (int) polygon.getXMid(1) + 10,
						(int) polygon.getYMid(1) + 5);
				((Graphics2D) g).drawString(polygon.GetLength(2) + " units", (int) polygon.getXMid(2) - 50,
						(int) polygon.getYMid(2) + 20);
				((Graphics2D) g).drawString(polygon.GetLength(3) + " units", (int) polygon.getXMid(3) - 110,
						(int) polygon.getYMid(3) + 5);

				((Graphics2D) g).setFont(new Font("courier", Font.BOLD, 25));
				ConvexAreaCheck();
				angleArea.setText("\n\n\n\n    Angles\n    A= "+ polygon.getAngle(0) + "\n    B= " + polygon.getAngle(1) + "\n    C= "
		        	+ polygon.getAngle(2) + "\n    D= "+ polygon.getAngle(3));

				polygon.setPoint(x, y, currentVertexIndex);
				vertices[currentVertexIndex].x = (int) (polygon.getX(currentVertexIndex) - verticesSize * 0.5);
				vertices[currentVertexIndex].y = (int) (polygon.getY(currentVertexIndex) - verticesSize * 0.5);
				poly = new java.awt.Polygon(polygon.getXs(), polygon.getYs(), 4);
				((Graphics2D) g).draw(vertices[currentVertexIndex]);

				g.setColor(c5);
				((Graphics2D) g).setFont(new Font("courier", Font.BOLD, 25));
				ConvexAreaCheck();
				angleArea.setText("\n\n\n\n    Angles\n    A= "+ polygon.getAngle(0) + "\n    B= " + polygon.getAngle(1) + "\n    C= "
		        	+ polygon.getAngle(2) + "\n    D= "+ polygon.getAngle(3));
				// ((Graphics2D) g)
				// 		.setStroke(new BasicStroke(polygonWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				((Graphics2D) g).draw(poly);
				((Graphics2D) g).setFont(new Font("courier", Font.BOLD, 15));
				((Graphics2D) g).drawString("A", (int) polygon.getX(0) - 15,
						(int) polygon.getY(0) - 5);
				((Graphics2D) g).drawString("B", (int) polygon.getX(1) + 5,
						(int) polygon.getY(1) - 5);
				((Graphics2D) g).drawString("C", (int) polygon.getX(2) + 5,
						(int) polygon.getY(2) + 15);
				((Graphics2D) g).drawString("D", (int) polygon.getX(3) - 15,
						(int) polygon.getY(3) + 15);
				((Graphics2D) g).drawString(polygon.GetLength(0) + " units", (int) polygon.getXMid(0) - 50,
						(int) polygon.getYMid(0) - 10);
				((Graphics2D) g).drawString(polygon.GetLength(1) + " units", (int) polygon.getXMid(1) + 10,
						(int) polygon.getYMid(1) + 5);
				((Graphics2D) g).drawString(polygon.GetLength(2) + " units", (int) polygon.getXMid(2) - 50,
						(int) polygon.getYMid(2) + 20);
				((Graphics2D) g).drawString(polygon.GetLength(3) + " units", (int) polygon.getXMid(3) - 110,
						(int) polygon.getYMid(3) + 5);
				g.dispose();
			}
		}
	}

	public void ConvexAreaCheck(){

		count180 = 0;
		double[] ANGLE = polygon.getangles();
		for(int j=0;j<4;j++){
			if((int)ANGLE[j]> 180){
				count180++;
			}
		}

		if(count180==2){
			areaLabel.setText("<html>Area = " + "!!!Convex!!!" + "</html>");
			// areaText.setText("!!!Convex!!!");
		} else {
			areaLabel.setText("<html>Area = " + polygon.getArea() + "</html>");
		}
	}
}
